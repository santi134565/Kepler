package org.alexdev.kepler.game.games.snowstorm.events;

import org.alexdev.kepler.game.games.GameObject;
import org.alexdev.kepler.game.games.enums.GameObjectType;
import org.alexdev.kepler.server.netty.streams.NettyResponse;

public class SnowwarAvatarMoveEvent extends GameObject {
    private final int objectId;

    private int X;

    private int Y;

    public SnowwarAvatarMoveEvent(int objectId, int x, int y) {
        super(objectId, GameObjectType.SNOWWAR_AVATAR_MOVE_EVENT);
        this.objectId = objectId;
        this.X = x;
        this.Y = y;
    }

    @Override
    public void serialiseObject(NettyResponse response) {
        response.writeInt(Integer.valueOf(GameObjectType.SNOWWAR_AVATAR_MOVE_EVENT.getObjectId()));
        response.writeInt(Integer.valueOf(this.objectId));
        response.writeInt(Integer.valueOf(this.X));
        response.writeInt(Integer.valueOf(this.Y));
    }
}