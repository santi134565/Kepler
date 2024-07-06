package org.alexdev.kepler.game.games.snowstorm.util;

import org.alexdev.kepler.game.games.player.GamePlayer;
import org.alexdev.kepler.game.games.snowstorm.SnowwarGame;
import org.alexdev.kepler.server.netty.streams.NettyRequest;

public interface SnowwarMessage {
    void handle(NettyRequest paramNettyRequest, SnowwarGame paramSnowStormGame, GamePlayer paramGamePlayer) throws Exception;
}
