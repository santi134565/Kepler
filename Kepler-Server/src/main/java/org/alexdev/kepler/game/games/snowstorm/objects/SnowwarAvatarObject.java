package org.alexdev.kepler.game.games.snowstorm.objects;

import org.alexdev.kepler.game.games.enums.GameObjectType;
import org.alexdev.kepler.game.games.player.GamePlayer;
import org.alexdev.kepler.game.games.snowstorm.util.SnowwarObject;
import org.alexdev.kepler.game.games.snowstorm.util.SnowwarSyncValues;
import org.alexdev.kepler.server.netty.streams.NettyResponse;

public class SnowwarAvatarObject extends SnowwarObject {
    private final GamePlayer p;
    public SnowwarAvatarObject(GamePlayer gamePlayer) {
        super(gamePlayer.getObjectId(), GameObjectType.SNOWWAR_AVATAR_OBJECT);
        this.p = gamePlayer;
    }
    
    @Override
    public void serialiseObject(NettyResponse response) {
        response.writeInt(GameObjectType.SNOWWAR_AVATAR_OBJECT.getObjectId()); // type id
        response.writeInt(this.p.getSnowwarObject().getSyncValue(SnowwarSyncValues.ROOM_INDEX)); // type id

        for (var syncValue : this.getSyncValues().values()) {
            response.writeInt(syncValue);
        }

        response.writeString(p.getPlayer().getDetails().getName());
        response.writeString(p.getPlayer().getDetails().getMotto());
        response.writeString(p.getPlayer().getDetails().getFigure());
        response.writeString(p.getPlayer().getDetails().getSex());

        /*var nextGoal = this.p.getSnowStormAttributes().getNextGoal();
        response.writeInt(GameObjectType.SNOWWAR_AVATAR_OBJECT.getObjectId()); // type id
        response.writeInt(this.p.getObjectId()); // int id
        response.writeInt(SnowStormGame.convertToWorldCoordinate(this.p.getSnowStormAttributes().getCurrentPosition().getX()));
        response.writeInt(SnowStormGame.convertToWorldCoordinate(this.p.getSnowStormAttributes().getCurrentPosition().getY()));
        response.writeInt(this.p.getSnowStormAttributes().getRotation()); // body direction
        response.writeInt(this.p.getSnowStormAttributes().getHealth().get()); // hit points
        response.writeInt(this.p.getSnowStormAttributes().getSnowballs().get()); // snowball count
        response.writeInt(0); // is bot
        response.writeInt(this.p.getSnowStormAttributes().getActivityTimer()); // activity timer
        response.writeInt(this.p.getSnowStormAttributes().getActivityState().getStateId()); // activity state
        response.writeInt(nextGoal != null ? nextGoal.getX() : this.p.getSnowStormAttributes().getCurrentPosition().getX()); // move target x
        response.writeInt(nextGoal != null ? nextGoal.getY() : this.p.getSnowStormAttributes().getCurrentPosition().getY()); // move target y
        response.writeInt(SnowStormGame.convertToWorldCoordinate(this.p.getSnowStormAttributes().isWalking() ? this.p.getSnowStormAttributes().getWalkGoal().getX() : this.p.getSnowStormAttributes().getCurrentPosition().getX())); // move target x
        response.writeInt(SnowStormGame.convertToWorldCoordinate(this.p.getSnowStormAttributes().isWalking() ? this.p.getSnowStormAttributes().getWalkGoal().getY() : this.p.getSnowStormAttributes().getCurrentPosition().getY())); // move target y
        response.writeInt(this.p.getSnowStormAttributes().getScore().get()); // score
        response.writeInt(p.getPlayer().getDetails().getId()); // player id
        response.writeInt(p.getTeamId()); // team id
        response.writeInt(p.getObjectId()); // room index
        response.writeString(p.getPlayer().getDetails().getName());
        response.writeString(p.getPlayer().getDetails().getMotto());
        response.writeString(p.getPlayer().getDetails().getFigure());
        response.writeString(p.getPlayer().getDetails().getSex());// Actually room user id/instance id*/
    }
}
