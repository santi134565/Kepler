package org.alexdev.kepler.game.games.snowstorm.tasks;

import org.alexdev.kepler.game.games.GameEvent;
import org.alexdev.kepler.game.games.GameObject;
import org.alexdev.kepler.game.games.battleball.BattleBallTile;
import org.alexdev.kepler.game.games.player.GamePlayer;
import org.alexdev.kepler.game.games.player.GameTeam;
import org.alexdev.kepler.game.games.snowstorm.SnowwarGame;
import org.alexdev.kepler.game.games.snowstorm.SnowwarMaths;
import org.alexdev.kepler.game.games.snowstorm.util.SnowwarObject;
import org.alexdev.kepler.game.games.snowstorm.util.SnowwarSyncValues;
import org.alexdev.kepler.game.player.Player;
import org.alexdev.kepler.game.room.Room;
import org.alexdev.kepler.log.Log;
import org.alexdev.kepler.messages.outgoing.games.SNOWSTORM_GAMESTATUS;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class SnowwarGameTask implements Runnable {
    private final Room room;
    private final SnowwarGame game;

    private List<GameObject> currentEvents;

    public int snowWarTurn = 0;
    public int snowWarChecksum = 0;


    public SnowwarGameTask(Room room, SnowwarGame game) {
        this.room = room;
        this.game = game;
        this.currentEvents = new CopyOnWriteArrayList<>();
        this.resetTurns();
    }

    public void resetTurns() {
        this.snowWarTurn = 0;
        this.snowWarChecksum = 0;
        this.currentEvents.clear();
        this.calculateChecksum();
    }

    @Override
    public void run() {
        try {
            if (this.game.getActivePlayers().isEmpty()) {
                return; // Don't send any packets or do any logic checks during when the game is finished
            }

            this.currentEvents.clear(); //= new ArrayList<>();
            this.game.getObjectsQueue().drainTo(this.currentEvents);

            for (GameTeam gameTeam : this.game.getTeams().values()) {
                for (GamePlayer gamePlayer : gameTeam.getPlayers()) {
                    Player player = gamePlayer.getPlayer();

                    if (player != null
                            && player.getRoomUser().getRoom() != null
                            && player.getRoomUser().getRoom() == this.room) {
                        player.getRoomUser().handleSpamTicks();
                        this.processEntity(gamePlayer, this.game);
                    }
                }
            }

        } catch (Exception ex) {
            Log.getErrorLogger().error("SnowstormWalkTask crashed: ", ex);
        }

        try {
            this.game.send(new SNOWSTORM_GAMESTATUS(this.snowWarTurn, this.snowWarChecksum, this.currentEvents));

            this.calculateChecksum();
        } catch (Exception ex) {
            Log.getErrorLogger().error("SnowstormTask crashed: ", ex);

        }
    }

    private void calculateChecksum() {
        this.snowWarTurn++;
        this.snowWarChecksum = SnowwarMaths.IterateSeed(this.snowWarTurn);

        for (var obj : this.game.getObjects()) {
            SnowwarObject snowwarObject = (SnowwarObject) obj;
            snowwarObject.calculateFrameMovement();
        }

        for (var obj : this.game.getObjects()) {
            SnowwarObject snowwarObject = (SnowwarObject) obj;
            this.snowWarChecksum += snowwarObject.calculateObjectChecksum();
        }

        System.out.println("Current turn: " + snowWarTurn + ", checksum: " + this.snowWarChecksum);
    }

    private void processEntity(GamePlayer gamePlayer, SnowwarGame game) {

    }

    public List<GameObject> getCurrentEvents() {
        return currentEvents;
    }

    public int getTurn() {
        return snowWarTurn;
    }

    public int getChecksum() {
        return snowWarChecksum;
    }
}
