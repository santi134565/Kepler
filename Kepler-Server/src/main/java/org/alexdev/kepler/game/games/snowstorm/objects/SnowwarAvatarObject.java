package org.alexdev.kepler.game.games.snowstorm.objects;

import org.alexdev.kepler.game.games.enums.GameObjectType;
import org.alexdev.kepler.game.games.player.GamePlayer;
import org.alexdev.kepler.game.games.snowstorm.SnowwarMaths;
import org.alexdev.kepler.game.games.snowstorm.util.SnowwarActivityState;
import org.alexdev.kepler.game.games.snowstorm.util.SnowwarObject;
import org.alexdev.kepler.game.games.snowstorm.util.SnowwarSyncValues;
import org.alexdev.kepler.game.pathfinder.Position;
import org.alexdev.kepler.server.netty.streams.NettyResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class SnowwarAvatarObject extends SnowwarObject {
    private final GamePlayer p;

    public SnowwarAvatarObject(GamePlayer gamePlayer) {
        super(gamePlayer.getObjectId(), GameObjectType.SNOWWAR_AVATAR_OBJECT);
        this.p = gamePlayer;
    }

    @Override
    public void serialiseObject(NettyResponse response) {
        // response.writeInt(GameObjectType.SNOWWAR_AVATAR_OBJECT.getObjectId()); // type id
        // response.writeInt(this.p.getObjectId()); // type id

        response.writeInt(this.getSyncValue(SnowwarSyncValues.TYPE));
        response.writeInt(this.getSyncValue(SnowwarSyncValues.INT_ID));
        response.writeInt(SnowwarMaths.convertToWorldCoordinate(this.getSyncValue(SnowwarSyncValues.X)));
        response.writeInt(SnowwarMaths.convertToWorldCoordinate(this.getSyncValue(SnowwarSyncValues.Y)));
        response.writeInt(this.getSyncValue(SnowwarSyncValues.BODY_DIRECTION));
        response.writeInt(this.getSyncValue(SnowwarSyncValues.HIT_POINTS));
        response.writeInt(this.getSyncValue(SnowwarSyncValues.SNOWBALL_COUNT));
        response.writeInt(this.getSyncValue(SnowwarSyncValues.IS_BOT));
        response.writeInt(this.getSyncValue(SnowwarSyncValues.ACTIVITY_TIMER));
        response.writeInt(this.getSyncValue(SnowwarSyncValues.ACTIVITY_STATE));
        response.writeInt(this.getSyncValue(SnowwarSyncValues.NEXT_TILE_X));
        response.writeInt(this.getSyncValue(SnowwarSyncValues.NEXT_TILE_Y));
        response.writeInt(SnowwarMaths.convertToWorldCoordinate(this.getSyncValue(SnowwarSyncValues.MOVE_TARGET_X)));
        response.writeInt(SnowwarMaths.convertToWorldCoordinate(this.getSyncValue(SnowwarSyncValues.MOVE_TARGET_Y)));
        response.writeInt(this.getSyncValue(SnowwarSyncValues.SCORE));
        response.writeInt(this.getSyncValue(SnowwarSyncValues.PLAYER_ID));
        response.writeInt(this.getSyncValue(SnowwarSyncValues.TEAM_ID));
        response.writeInt(this.getSyncValue(SnowwarSyncValues.ROOM_INDEX));


        response.writeString(p.getPlayer().getDetails().getName());
        response.writeString(p.getPlayer().getDetails().getMotto());
        response.writeString(p.getPlayer().getDetails().getFigure());
        response.writeString(p.getPlayer().getDetails().getSex());
    }

    @Override
    public int calculateObjectChecksum() {
        List<Map.Entry<SnowwarSyncValues, Integer>> checksumList = new ArrayList<>();

        checksumList.add(Map.entry(SnowwarSyncValues.TYPE, this.getSyncValue(SnowwarSyncValues.TYPE)));
        checksumList.add(Map.entry(SnowwarSyncValues.INT_ID, this.getSyncValue(SnowwarSyncValues.INT_ID)));

        checksumList.add(Map.entry(SnowwarSyncValues.X, SnowwarMaths.TileToWorld(this.getSyncValue(SnowwarSyncValues.X))));
        checksumList.add(Map.entry(SnowwarSyncValues.Y, SnowwarMaths.TileToWorld(this.getSyncValue(SnowwarSyncValues.Y))));

        checksumList.add(Map.entry(SnowwarSyncValues.BODY_DIRECTION, this.getSyncValue(SnowwarSyncValues.BODY_DIRECTION)));
        checksumList.add(Map.entry(SnowwarSyncValues.HIT_POINTS, this.getSyncValue(SnowwarSyncValues.HIT_POINTS)));
        checksumList.add(Map.entry(SnowwarSyncValues.SNOWBALL_COUNT, this.getSyncValue(SnowwarSyncValues.SNOWBALL_COUNT)));
        checksumList.add(Map.entry(SnowwarSyncValues.IS_BOT, this.getSyncValue(SnowwarSyncValues.IS_BOT)));

        checksumList.add(Map.entry(SnowwarSyncValues.ACTIVITY_TIMER, this.getSyncValue(SnowwarSyncValues.ACTIVITY_TIMER)));
        checksumList.add(Map.entry(SnowwarSyncValues.ACTIVITY_STATE, this.getSyncValue(SnowwarSyncValues.ACTIVITY_STATE)));

        checksumList.add(Map.entry(SnowwarSyncValues.NEXT_TILE_X, this.getSyncValue(SnowwarSyncValues.NEXT_TILE_X)));
        checksumList.add(Map.entry(SnowwarSyncValues.NEXT_TILE_Y, this.getSyncValue(SnowwarSyncValues.NEXT_TILE_Y)));

        checksumList.add(Map.entry(SnowwarSyncValues.MOVE_TARGET_X, SnowwarMaths.TileToWorld(this.getSyncValue(SnowwarSyncValues.MOVE_TARGET_X))));
        checksumList.add(Map.entry(SnowwarSyncValues.MOVE_TARGET_Y, SnowwarMaths.TileToWorld(this.getSyncValue(SnowwarSyncValues.MOVE_TARGET_Y))));

        checksumList.add(Map.entry(SnowwarSyncValues.SCORE, this.getSyncValue(SnowwarSyncValues.SCORE)));
        checksumList.add(Map.entry(SnowwarSyncValues.PLAYER_ID, this.getSyncValue(SnowwarSyncValues.PLAYER_ID)));
        checksumList.add(Map.entry(SnowwarSyncValues.TEAM_ID, this.getSyncValue(SnowwarSyncValues.TEAM_ID)));
        checksumList.add(Map.entry(SnowwarSyncValues.ROOM_INDEX, this.getSyncValue(SnowwarSyncValues.ROOM_INDEX)));

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