package org.alexdev.kepler.messages.outgoing.games;

import org.alexdev.kepler.game.games.Game;
import org.alexdev.kepler.game.games.GameManager;
import org.alexdev.kepler.game.games.battleball.BattleBallTile;
import org.alexdev.kepler.game.games.enums.GameState;
import org.alexdev.kepler.game.games.enums.GameType;
import org.alexdev.kepler.game.games.snowstorm.SnowwarGame;
import org.alexdev.kepler.game.games.snowstorm.tasks.SnowwarGameTask;
import org.alexdev.kepler.messages.types.MessageComposer;
import org.alexdev.kepler.server.netty.streams.NettyResponse;

public class FULLGAMESTATUS extends MessageComposer {
    private final Game game;

    public FULLGAMESTATUS(Game game) {
        this.game = game;
    }

    @Override
    public void compose(NettyResponse response) {
        if (this.game.getGameType() == GameType.BATTLEBALL) {
            response.writeInt(GameState.STARTED.getStateId());
            response.writeInt(this.game.getPreparingGameSecondsLeft().get());
            response.writeInt(GameManager.getInstance().getPreparingSeconds(game.getGameType()));
            response.writeInt(this.game.getObjects().size()); // TODO: Objects here

            if (this.game.getGameType() == GameType.BATTLEBALL) {
                for (var gameObject : this.game.getObjects()) {
                    response.writeInt(gameObject.getGameObjectType().getObjectId()); // type, 0 = player
                    gameObject.serialiseObject(response);
                }

                response.writeInt(this.game.getRoomModel().getMapSizeY());
                response.writeInt(this.game.getRoomModel().getMapSizeX());

                for (int y = 0; y < this.game.getRoomModel().getMapSizeY(); y++) {
                    for (int x = 0; x < this.game.getRoomModel().getMapSizeX(); x++) {
                        BattleBallTile tile = (BattleBallTile) this.game.getTile(x, y);

                        if (tile == null) {
                            response.writeInt(-1);
                            response.writeInt(0);
                        } else {
                            response.writeInt(tile.getColour().getColourId());
                            response.writeInt(tile.getState().getTileStateId());
                        }
                    }
                }

                response.writeInt(1);
                response.writeInt(0); // TODO: Show events on game load
            }
        }
        else {
            SnowwarGameTask snowStormTask = ((SnowwarGame)this.game).getUpdateTask();

            var objects = this.game.getObjects();

            response.writeInt(this.game.getGameState().getStateId());
            response.writeInt(this.game.getPreparingGameSecondsLeft().get());
            response.writeInt(GameManager.getInstance().getPreparingSeconds(game.getGameType()));
            response.writeInt(this.game.getObjects().size()); // TODO: Objects here

            for (var obj : objects) {
                obj.serialiseObject(response);
            }

            response.writeBool(false);
            response.writeInt(this.game.getTeamAmount());

            /*
            response.writeInt(1);
            response.writeInt(1);
            response.writeInt(0);
            */

            (new SNOWSTORM_GAMESTATUS(snowStormTask.getTurn(), snowStormTask.getChecksum())).compose(response);
            /*
            response.writeInt(this.turns.size() == 0 ? 1 : this.turns.size());

            for (var turn : this.turns) {
                response.writeInt(turn.getSubTurns().size());

                for (var gameObject : turn.getSubTurns()) {
                    gameObject.serialiseObject(response);
                }
            }*/
        }
    }

    @Override
    public short getHeader() {
        return 243; // "Cs"
    }
}

