package org.alexdev.kepler.game.games.snowstorm.objects;

import org.alexdev.kepler.game.games.GameObject;
import org.alexdev.kepler.game.games.enums.GameObjectType;
import org.alexdev.kepler.game.games.snowstorm.SnowwarGame;
import org.alexdev.kepler.game.games.snowstorm.util.SnowwarObject;
import org.alexdev.kepler.game.games.snowstorm.util.SnowwarSyncValues;
import org.alexdev.kepler.server.netty.streams.NettyResponse;

import java.util.concurrent.atomic.AtomicInteger;

public class SnowwarMachineObject extends SnowwarObject {
    private final int objectId;
    private final int X;
    private final int Y;
    private AtomicInteger snowballCount;
    private long lastRefillTime;

    public SnowwarMachineObject(int objectId, int X, int Y, int snowballCount) {
        super(objectId, GameObjectType.SNOWWAR_SNOWMACHINE_OBJECT);
        this.objectId = objectId;
        this.X = X;
        this.Y = Y;
        this.snowballCount = new AtomicInteger(snowballCount);
        this.lastRefillTime = 0;

        this.setSyncValue(SnowwarSyncValues.X, SnowwarGame.convertToWorldCoordinate(this.X));
        this.setSyncValue(SnowwarSyncValues.Y, SnowwarGame.convertToWorldCoordinate(this.Y));
        this.setSyncValue(SnowwarSyncValues.SNOWBALL_COUNT, this.snowballCount.get());
    }
    
    @Override
    public void serialiseObject(NettyResponse response) {
        response.writeInt(GameObjectType.SNOWWAR_SNOWMACHINE_OBJECT.getObjectId());
        response.writeInt(this.objectId);
        response.writeInt(SnowwarGame.convertToWorldCoordinate(this.X));
        response.writeInt(SnowwarGame.convertToWorldCoordinate(this.Y));
        response.writeInt(this.snowballCount.get());
    }
}
