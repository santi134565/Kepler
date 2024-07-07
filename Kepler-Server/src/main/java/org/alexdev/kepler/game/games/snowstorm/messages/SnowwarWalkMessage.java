package org.alexdev.kepler.game.games.snowstorm.messages;

import org.alexdev.kepler.game.games.player.GamePlayer;
import org.alexdev.kepler.game.games.snowstorm.SnowwarGame;
import org.alexdev.kepler.game.games.snowstorm.SnowwarMaths;
import org.alexdev.kepler.game.games.snowstorm.events.SnowwarAvatarMoveEvent;
import org.alexdev.kepler.game.games.snowstorm.util.SnowwarMessage;
import org.alexdev.kepler.game.games.snowstorm.util.SnowwarSyncValues;
import org.alexdev.kepler.server.netty.streams.NettyRequest;

public class SnowwarWalkMessage implements SnowwarMessage {
    @Override
    public void handle(NettyRequest reader, SnowwarGame game, GamePlayer gamePlayer) throws Exception {
        int X = reader.readInt();
        int Y = reader.readInt();

        int syncX = SnowwarMaths.convertToGameCoordinate(X);
        int syncY = SnowwarMaths.convertToGameCoordinate(Y);

        gamePlayer.getSnowwarObject().setSyncValue(SnowwarSyncValues.MOVE_TARGET_X, syncX);
        gamePlayer.getSnowwarObject().setSyncValue(SnowwarSyncValues.MOVE_TARGET_X, syncY);

        game.getExecutingEvents().add(new SnowwarAvatarMoveEvent(gamePlayer.getObjectId(), X, Y));
    }
}
