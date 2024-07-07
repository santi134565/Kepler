package org.alexdev.kepler.game.games.snowstorm.objects;

import org.alexdev.kepler.game.games.enums.GameObjectType;
import org.alexdev.kepler.game.games.snowstorm.SnowwarGame;
import org.alexdev.kepler.game.games.snowstorm.SnowwarMaths;
import org.alexdev.kepler.game.games.snowstorm.util.SnowwarObject;
import org.alexdev.kepler.game.games.snowstorm.util.SnowwarSyncValues;
import org.alexdev.kepler.server.netty.streams.NettyResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SnowwarMachineObject extends SnowwarObject {
    private final int objectId;

    public SnowwarMachineObject(SnowwarGame game, int objectId, int X, int Y, int snowballCount) {
        super(game, objectId, GameObjectType.SNOWWAR_SNOWMACHINE_OBJECT);
        this.objectId = objectId;

        this.setSyncValue(SnowwarSyncValues.TYPE, GameObjectType.SNOWWAR_SNOWMACHINE_OBJECT.getObjectId());
        this.setSyncValue(SnowwarSyncValues.INT_ID, objectId);
        this.setSyncValue(SnowwarSyncValues.X, X);
        this.setSyncValue(SnowwarSyncValues.Y, Y);
        this.setSyncValue(SnowwarSyncValues.SNOWBALL_COUNT, snowballCount);
    }
    
    @Override
    public void serialiseObject(NettyResponse response) {
        response.writeInt(this.getSyncValue(SnowwarSyncValues.TYPE));
        response.writeInt(this.getSyncValue(SnowwarSyncValues.INT_ID));
        response.writeInt(SnowwarMaths.convertToWorldCoordinate(this.getSyncValue(SnowwarSyncValues.X)));
        response.writeInt(SnowwarMaths.convertToWorldCoordinate(this.getSyncValue(SnowwarSyncValues.Y)));
        response.writeInt(this.getSyncValue(SnowwarSyncValues.SNOWBALL_COUNT));
    }

    @Override
    public void calculateFrameMovement() {

    }

    @Override
    public int calculateObjectChecksum() {
        List<Map.Entry<SnowwarSyncValues, Integer>> checksumList = new ArrayList<>();

        checksumList.add(Map.entry(SnowwarSyncValues.TYPE, this.getSyncValue(SnowwarSyncValues.TYPE)));
        checksumList.add(Map.entry(SnowwarSyncValues.INT_ID, this.getSyncValue(SnowwarSyncValues.INT_ID)));

        checksumList.add(Map.entry(SnowwarSyncValues.X, SnowwarMaths.TileToWorld(this.getSyncValue(SnowwarSyncValues.X))));
        checksumList.add(Map.entry(SnowwarSyncValues.Y, SnowwarMaths.TileToWorld(this.getSyncValue(SnowwarSyncValues.Y))));

        checksumList.add(Map.entry(SnowwarSyncValues.SNOWBALL_COUNT, this.getSyncValue(SnowwarSyncValues.SNOWBALL_COUNT)));

        StringBuilder tDebug = new StringBuilder();
        int fChecksum = 0;

        for (int i = 0; i < checksumList.size(); i++) {
            tDebug.append("[").append(checksumList.get(i).getKey().getTag()).append(", ").append(checksumList.get(i).getValue()).append("], ");
            fChecksum += (checksumList.get(i).getValue()) * (i + 1);
        }

        System.out.println(tDebug);

        return fChecksum;
    }
}
