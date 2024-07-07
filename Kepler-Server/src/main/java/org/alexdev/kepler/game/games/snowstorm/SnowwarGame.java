package org.alexdev.kepler.game.games.snowstorm;

import org.alexdev.kepler.dao.mysql.CurrencyDao;
import org.alexdev.kepler.game.games.*;
import org.alexdev.kepler.game.games.enums.GameObjectType;
import org.alexdev.kepler.game.games.enums.GameState;
import org.alexdev.kepler.game.games.enums.GameType;
import org.alexdev.kepler.game.games.player.GamePlayer;
import org.alexdev.kepler.game.games.player.GameTeam;
import org.alexdev.kepler.game.games.snowstorm.mapping.SnowwarMap;
import org.alexdev.kepler.game.games.snowstorm.objects.SnowwarAvatarObject;
import org.alexdev.kepler.game.games.snowstorm.objects.SnowwarMachineObject;
import org.alexdev.kepler.game.games.snowstorm.tasks.SnowwarGameTask;
import org.alexdev.kepler.game.games.snowstorm.util.SnowwarActivityState;
import org.alexdev.kepler.game.games.snowstorm.util.SnowwarSpawn;
import org.alexdev.kepler.game.games.snowstorm.util.SnowwarSyncValues;
import org.alexdev.kepler.game.pathfinder.Position;
import org.alexdev.kepler.game.player.Player;
import org.alexdev.kepler.game.room.models.RoomModel;
import org.alexdev.kepler.log.Log;
import org.alexdev.kepler.util.DateUtil;
import org.alexdev.kepler.util.config.GameConfiguration;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class SnowwarGame extends Game {
    public static final int MAX_QUICK_THROW_DISTANCE = 22;
    private int gameLengthChoice;
    private BlockingQueue<GameObject> executingEvents;
    private long gameStarted;
    private BlockingQueue<GameEvent> eventsQueue;

    public SnowwarGame(int id, int mapId, String name, int teamAmount, Player gameCreator, int gameLengthChoice, boolean privateGame) {
        super(id, mapId, GameType.SNOWSTORM, name, teamAmount, gameCreator);
        this.gameLengthChoice = gameLengthChoice;
        this.executingEvents = new LinkedBlockingQueue<>();
    }

    @Override
    public boolean hasEnoughPlayers() {
        if (this.getTeamAmount() == 1) {
            return this.getActivePlayers().size() > 0;
        } else {
            int activeTeamCount = 0;

            for (int i = 0; i < this.getTeamAmount(); i++) {
                if (this.getTeams().get(i).getActivePlayers().size() > 0) {
                    activeTeamCount++;
                }
            }

            return activeTeamCount > 0;
        }
    }

    @Override
    public void initialise() {
        var model = new RoomModel("snowwar_arena_0", "snowwar_arena_0", 0, 0, 0, 0,
                SnowwarMapManager.getInstance().getHeightMap(this.getMapId()), null);

        int seconds = 0;

        if (this.gameLengthChoice == 1) {
            seconds = (int) TimeUnit.MINUTES.toSeconds(2);
        }

        if (this.gameLengthChoice == 2) {
            seconds = (int) TimeUnit.MINUTES.toSeconds(3);
        }

        if (this.gameLengthChoice == 3) {
            seconds = (int) TimeUnit.MINUTES.toSeconds(5);
        }

        if (GameManager.getInstance().getLifetimeSeconds(this.getGameType()) > 0) {
            seconds = GameManager.getInstance().getLifetimeSeconds(this.getGameType());
        }

        super.initialise(seconds, "SnowStorm Arena", model);
        this.gameStarted = DateUtil.getCurrentTimeSeconds();

        for (var snowballItem : this.getMap().getItems()) {
            if (snowballItem.isSnowballMachine()) {
             this.getObjects().add(new SnowwarMachineObject(this, this.createObjectId(), snowballItem.getX(), snowballItem.getY(), 0));
            }
        }
        //this.getTotalSecondsLeft().set(seconds); // Override with game length choice
    }

    @Override
    public void gamePrepare() {
        super.gamePrepare();

        int ticketCharge = GameConfiguration.getInstance().getInteger("snowstorm.ticket.charge");

        if (ticketCharge > 0) {
            for (GamePlayer gamePlayer : this.getActivePlayers()) {
                CurrencyDao.decreaseTickets(gamePlayer.getPlayer().getDetails(), 2); // BattleBall costs 2 tickets
            }
        }
    }

    @Override
    public void beginGame() {
        this.getUpdateTask().resetTurns();

        super.beginGame();
    }

    @Override
    public void finishGame() {
        for (GamePlayer p : this.getActivePlayers()) {
            p.setScore(p.getSnowwarObject().getSyncValue(SnowwarSyncValues.SCORE));//p.getSnowStormAttributes().getScore().get());
        }

        for (GameTeam team : this.getTeams().values()) {
           //  team.setScore(team.getPlayers().stream().mapToInt(GamePlayer::getScore).sum());
            team.calculateScore();
        }

        super.finishGame();
    }

    @Override
    public void assignSpawnPoints() {
        this.getRoom().getMapping().regenerateCollisionMap();

        for (GameTeam team : this.getTeams().values()) {
            for (GamePlayer p : team.getPlayers()) {
                p.setAssignedSpawn(false);
            }
        }

        for (GameTeam team : this.getTeams().values()) {
            for (GamePlayer p : team.getPlayers()) {
                generateSpawn(p);

                p.setObjectId(this.createObjectId());
                p.setGameObject(new SnowwarAvatarObject(this, p));
                p.setScore(0);

                p.getSnowwarObject().setSyncValue(SnowwarSyncValues.TYPE, GameObjectType.SNOWWAR_AVATAR_OBJECT.getObjectId());
                p.getSnowwarObject().setSyncValue(SnowwarSyncValues.INT_ID, p.getObjectId());

                p.getSnowwarObject().setSyncValue(SnowwarSyncValues.X, p.getSpawnPosition().getX());
                p.getSnowwarObject().setSyncValue(SnowwarSyncValues.Y, p.getSpawnPosition().getY());

                p.getSnowwarObject().setSyncValue(SnowwarSyncValues.BODY_DIRECTION, ThreadLocalRandom.current().nextInt(0, 7));
                p.getSnowwarObject().setSyncValue(SnowwarSyncValues.HIT_POINTS, 4);
                p.getSnowwarObject().setSyncValue(SnowwarSyncValues.SNOWBALL_COUNT, 5);
                p.getSnowwarObject().setSyncValue(SnowwarSyncValues.IS_BOT, 0);

                p.getSnowwarObject().setSyncValue(SnowwarSyncValues.ACTIVITY_TIMER, 0);
                p.getSnowwarObject().setSyncValue(SnowwarSyncValues.ACTIVITY_STATE, SnowwarActivityState.ACTIVITY_STATE_NORMAL.getStateId());

                p.getSnowwarObject().setSyncValue(SnowwarSyncValues.NEXT_TILE_X, p.getSnowwarObject().getSyncValue(SnowwarSyncValues.X));
                p.getSnowwarObject().setSyncValue(SnowwarSyncValues.NEXT_TILE_Y, p.getSnowwarObject().getSyncValue(SnowwarSyncValues.Y));

                p.getSnowwarObject().setSyncValue(SnowwarSyncValues.MOVE_TARGET_X, p.getSnowwarObject().getSyncValue(SnowwarSyncValues.X));
                p.getSnowwarObject().setSyncValue(SnowwarSyncValues.MOVE_TARGET_Y, p.getSnowwarObject().getSyncValue(SnowwarSyncValues.Y));

                p.getSnowwarObject().setSyncValue(SnowwarSyncValues.SCORE, 0);
                p.getSnowwarObject().setSyncValue(SnowwarSyncValues.PLAYER_ID, p.getPlayer().getDetails().getId());

                p.getSnowwarObject().setSyncValue(SnowwarSyncValues.TEAM_ID, p.getTeamId());
                p.getSnowwarObject().setSyncValue(SnowwarSyncValues.ROOM_INDEX, p.getObjectId());

                this.getObjects().add(p.getGameObject());

            }
        }
    }

    private void generateSpawn(GamePlayer p) {
        if (this.getMap().getSpawnClusters().length == 0) {
            p.getSpawnPosition().setX(15);
            p.getSpawnPosition().setY(18);
            p.setAssignedSpawn(true);
            return;
        }

        try {
            SnowwarSpawn spawn = this.getMap().getSpawnClusters()[ThreadLocalRandom.current().nextInt(this.getMap().getSpawnClusters().length)];

            List<Position> potentialPositions = spawn.getPosition().getCircle(spawn.getRadius());
            Collections.shuffle(potentialPositions);

            Position candidate = potentialPositions.get(ThreadLocalRandom.current().nextInt(0, potentialPositions.size() - 1));

            for (GamePlayer gamePlayer : this.getActivePlayers()) {
                if (!gamePlayer.isAssignedSpawn()) {
                    continue;
                }

                int distance = gamePlayer.getSpawnPosition().getDistanceSquared(candidate);

                if (distance < spawn.getMinDistance()) {
                    generateSpawn(p);
                    return;
                }
            }

            if (this.getMap().getTile(candidate) == null || !this.getMap().getTile(candidate).isWalkable()) {
                generateSpawn(p);
                return;
            }

            p.getSpawnPosition().setX(candidate.getX());
            p.getSpawnPosition().setY(candidate.getY());
            p.setAssignedSpawn(true);
        } catch (Exception ex) {
            Log.getErrorLogger().error("Exception when assigning spawn point on map {}:", this.getMapId(), ex);

            p.getSpawnPosition().setX(15);
            p.getSpawnPosition().setY(18);
            p.setAssignedSpawn(true);
        }
    }

    public int getGameLength() {
        if (this.getGameState() == GameState.WAITING || this.getGameState() == GameState.ENDED) {
            if (this.gameLengthChoice == 1) {
                return (int) TimeUnit.MINUTES.toSeconds(2);
            }

            if (this.gameLengthChoice == 2) {
                return (int) TimeUnit.MINUTES.toSeconds(3);
            }

            if (this.gameLengthChoice == 3) {
                return (int) TimeUnit.MINUTES.toSeconds(5);
            }
        }

        return this.getTotalSecondsLeft().get();
    }

    public static int convertToGameCoordinate(int num) {
        int pAccuracyFactor = 100;
        int pTileSize = 32;
        int tMultiplier = pTileSize * pAccuracyFactor;

        return num / tMultiplier;
    }

    public static int convertToWorldCoordinate(int num) {
        int pAccuracyFactor = 100;
        int pTileSize = 32;
        int tMultiplier = pTileSize * pAccuracyFactor;

        return num * tMultiplier;
    }

    public boolean isOppositionPlayer(GamePlayer gamePlayer, GamePlayer player) {
        if (gamePlayer.getPlayer().getDetails().getId() == player.getPlayer().getDetails().getId()) {
            return false;
        }

        if (this.getTeamAmount() == 1) {
            return true;
        }

        return gamePlayer.getTeamId() != player.getTeamId();
    }

    public SnowwarGameTask getUpdateTask() {
        return (SnowwarGameTask) this.getRoom().getTaskManager().getTask("UpdateTask");
    }

    @Override
    public void gameTick() { }

    @Override
    public boolean canTimerContinue() { return true; }

    @Override
    public GameTile[][] getTileMap() {
        return new GameTile[0][];
    }

    @Override
    public void buildMap() { }

    public SnowwarMap getMap() {
        return SnowwarMapManager.getInstance().getMap(this.getMapId());
    }

    public int getGameLengthChoice() {
        return gameLengthChoice;
    }

    public BlockingQueue<GameObject> getExecutingEvents() {
        return executingEvents;
    }

    public boolean isTileAvailable(Position tNextTile) {
        var tTile = this.getMap().getTile(tNextTile);

        if (tTile == null) {
            return false;
        }

        return tTile.isWalkable() &&
                this.getActivePlayers()
                        .stream()
                        .noneMatch(x ->
                                x.getSnowwarObject().getCurrentPosition().equals(tNextTile));
    }
}
