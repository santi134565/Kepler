package org.alexdev.kepler.game.games.snowstorm;

import org.alexdev.kepler.game.games.player.GamePlayer;
import org.alexdev.kepler.game.games.snowstorm.messages.SnowwarWalkMessage;
import org.alexdev.kepler.game.games.snowstorm.util.SnowwarEvent;
import org.alexdev.kepler.game.games.snowstorm.util.SnowwarEventType;
import org.alexdev.kepler.game.games.snowstorm.util.SnowwarMessage;
import org.alexdev.kepler.server.netty.streams.NettyRequest;

import java.util.HashMap;

public class SnowStormMessageHandler {
    private static SnowStormMessageHandler instance;

    private final HashMap<SnowwarEventType, SnowwarMessage> events;

    public SnowStormMessageHandler() {
        this.events = new HashMap<>();
        this.events.put(SnowwarEventType.WALK, new SnowwarWalkMessage());
    }

    public void handleMessage(int messageId, NettyRequest request, SnowwarGame snowStormGame, GamePlayer player) throws Exception {
        var event = SnowwarEventType.getEvent(messageId);

        if (this.events.containsKey(event))
            this.events.get(event).handle(request, snowStormGame, player);
    }

    public static SnowStormMessageHandler getInstance() {
        if (instance == null)
            instance = new SnowStormMessageHandler();
        return instance;
    }
}
