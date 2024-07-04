package org.alexdev.kepler.game.games.snowstorm.tasks;

import org.alexdev.kepler.game.GameScheduler;
import org.alexdev.kepler.game.games.GameObject;
import org.alexdev.kepler.game.games.player.GamePlayer;
import org.alexdev.kepler.game.games.player.GameTeam;
import org.alexdev.kepler.game.games.snowstorm.SnowStormGame;
import org.alexdev.kepler.game.games.snowstorm.SnowStormTurn;
import org.alexdev.kepler.game.games.snowstorm.objects.SnowStormMachineObject;
import org.alexdev.kepler.game.games.snowstorm.util.SnowStormFuture;
import org.alexdev.kepler.game.pathfinder.Position;
import org.alexdev.kepler.game.pathfinder.Rotation;
import org.alexdev.kepler.game.player.Player;
import org.alexdev.kepler.game.room.Room;
import org.alexdev.kepler.log.Log;
import org.alexdev.kepler.messages.outgoing.games.SNOWSTORM_GAMESTATUS;
import org.alexdev.kepler.util.schedule.FutureRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class SnowStormGameTask implements Runnable {
    private final Room room;
    private final SnowStormGame game;
    private List<SnowStormTurn> snowStormTurnList;
    private List<SnowStormFuture> futureEvents;
    private int maxGameTurns = 5;

    public SnowStormGameTask(Room room, SnowStormGame game) {
        this.room = room;
        this.game = game;
        this.resetTurns();
        this.futureEvents = new CopyOnWriteArrayList<>();
    }

    private void resetTurns() {
        this.snowStormTurnList = new CopyOnWriteArrayList<>();

        for (int i = 0; i < maxGameTurns; i++) {
            this.snowStormTurnList.add(new SnowStormTurn());
        }
    }

    @Override
    public void run() {
        try {
            if (this.game.getActivePlayers().isEmpty()) {
                return; // Don't send any packets or do any logic checks during when the game is finished
            }

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

        } catch (Exception ex) {
            Log.getErrorLogger().error("SnowstormTask crashed: ", ex);

        }
    }

    private void processEntity(GamePlayer gamePlayer, SnowStormGame game) {

    }

}
