package org.alexdev.kepler.game.games.snowstorm.objects;

import org.alexdev.kepler.game.games.enums.GameObjectType;
import org.alexdev.kepler.game.games.player.GamePlayer;
import org.alexdev.kepler.game.games.snowstorm.SnowwarGame;
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
    private static final int SUBTURN_MOVEMENT = 640;
    private final GamePlayer p;

    public SnowwarAvatarObject(SnowwarGame game, GamePlayer gamePlayer) {
        super(game, gamePlayer.getObjectId(), GameObjectType.SNOWWAR_AVATAR_OBJECT);
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

    @Override
    public void calculateFrameMovement() {
        int tActivityTimer = this.getSyncValue(SnowwarSyncValues.ACTIVITY_TIMER);
        if (tActivityTimer > 0) {
            if (tActivityTimer == 1) {
                this.activityTimerTriggered();
            }
            this.setSyncValue(SnowwarSyncValues.ACTIVITY_TIMER, tActivityTimer - 1);
        }

        System.out.println("this.existsFinalTarget() - " + this.existsFinalTarget());

        if (this.existsFinalTarget()) {
            var tNextPosition = this.getNextPosition();
            var tCurrentPosition = this.getCurrentPosition();

            System.out.println("this.getStateAllowsMoving() - " + this.getStateAllowsMoving());

            if (this.getStateAllowsMoving()) {
                boolean tMoving = this.calculateMovement();
                int tDirBody = this.getSyncValue(SnowwarSyncValues.BODY_DIRECTION);

                if (tMoving) {
                    this.setSyncValue(SnowwarSyncValues.X, tNextPosition.getX());
                    this.setSyncValue(SnowwarSyncValues.Y, tNextPosition.getY());

                    this.setSyncValue(SnowwarSyncValues.BODY_DIRECTION, tDirBody);

                    System.out.println("WALKING");
                } else {
                    System.out.println("STOPPED WALKING");
                }
            }
        }

        int tActivityState = this.getSyncValue(SnowwarSyncValues.ACTIVITY_STATE);

        if (tActivityState == SnowwarActivityState.ACTIVITY_STATE_STUNNED.getStateId() ||
            tActivityState == SnowwarActivityState.ACTIVITY_STATE_INVINCIBLE_AFTER_STUN.getStateId()) {
            return;
        }

        this.checkForSnowballCollisions();
    }

    public boolean calculateMovement() {
        var tMoveTarget = this.getGoalPosition();
        var tNextTarget = this.getNextPosition();

        if (tMoveTarget == null || tNextTarget == null) {
            return false;
        }

        int tMoveTargetX = SnowwarMaths.TileToWorld(tMoveTarget.getX());
        int tMoveTargetY = SnowwarMaths.TileToWorld(tMoveTarget.getY());

        int tCurrentX = SnowwarMaths.TileToWorld(this.getCurrentPosition().getX());
        int tCurrentY = SnowwarMaths.TileToWorld(this.getCurrentPosition().getY());

        int tNextTargetX = SnowwarMaths.TileToWorld(tNextTarget.getX());
        int tNextTargetY = SnowwarMaths.TileToWorld(tNextTarget.getY());

        if (tCurrentX == tMoveTargetX && tCurrentY == tMoveTargetY) {
            return false;
        }

        if (tNextTargetX != tCurrentX || tNextTargetY != tCurrentY) {
            int tOldX = tCurrentX;
            int tOldY = tCurrentY;

            int tTargetX = tNextTargetX;
            int tDeltaX = tTargetX - tCurrentX;

            if (tDeltaX < 0) {
                if (tDeltaX > -SUBTURN_MOVEMENT) {
                    tCurrentX = tTargetX;
                } else {
                    tCurrentX = tCurrentX - SUBTURN_MOVEMENT;
                }
            } else {
                if (tDeltaX > 0) {
                    if (tDeltaX < SUBTURN_MOVEMENT) {
                        tCurrentX = tTargetX;
                    } else {
                        tCurrentX = tCurrentX + SUBTURN_MOVEMENT;
                    }
                }
            }

            int tTargetY = tNextTargetY;
            int tDeltaY = tTargetY - tCurrentY;

            if (tDeltaY < 0) {
                if (tDeltaY > -SUBTURN_MOVEMENT) {
                    tCurrentY = tTargetY;
                } else {
                    tCurrentY = tCurrentY - SUBTURN_MOVEMENT;
                }
            } else {
                if (tDeltaY > 0) {
                    if (tDeltaY < SUBTURN_MOVEMENT) {
                        tCurrentY = tTargetY;
                    } else {
                        tCurrentY = tCurrentY + SUBTURN_MOVEMENT;
                    }
                }
            }

            this.setSyncValue(SnowwarSyncValues.X, SnowwarMaths.WorldToTile(tCurrentX));
            this.setSyncValue(SnowwarSyncValues.Y, SnowwarMaths.WorldToTile(tCurrentY));

            if (tCurrentX == tMoveTargetX && tCurrentY == tMoveTargetY) {
                return false;
            }

            return true;
        } else {
            int tTileX = SnowwarMaths.WorldToTile(tCurrentX); //this.getCurrentPosition().getX();
            int tTileY = SnowwarMaths.WorldToTile(tCurrentY); //this.getCurrentPosition().getY();

            int tMoveDirection360 = SnowwarMaths.getAngleFromComponents(tMoveTargetX - tCurrentX, tMoveTargetY - tCurrentY);
            int tNextDir = SnowwarMaths.direction360to8(tMoveDirection360);

            var tNextTile = SnowwarMaths.getTileNeighborInDirection(tTileX, tTileY, tNextDir);
            boolean tNextAvailable = tNextTile != null && this.getGame().isTileAvailable(tNextTile);

            if (!tNextAvailable) {
                tNextDir = SnowwarMaths.direction360to8(SnowwarMaths.rotateDirection45DegreesCCW(tMoveDirection360));
                tNextTile = SnowwarMaths.getTileNeighborInDirection(tTileX, tTileY, tNextDir);
                tNextAvailable = tNextTile != null && this.getGame().isTileAvailable(tNextTile);

                if (!tNextAvailable) {
                    tNextDir = SnowwarMaths.direction360to8(SnowwarMaths.rotateDirection45DegreesCW(tMoveDirection360));
                    tNextTile = SnowwarMaths.getTileNeighborInDirection(tTileX, tTileY, tNextDir);
                    tNextAvailable = tNextTile != null && this.getGame().isTileAvailable(tNextTile);

                    if (!tNextAvailable) {
                        tNextTile = null;
                    }
                }
            }

            if (tNextTile != null) {
                this.setSyncValue(SnowwarSyncValues.NEXT_TILE_X, tNextTile.getX());
                this.setSyncValue(SnowwarSyncValues.NEXT_TILE_Y, tNextTile.getY());

                this.setSyncValue(SnowwarSyncValues.BODY_DIRECTION, tNextDir);

                return this.calculateMovement();
            }
        }

        return false;
    }

    private boolean existsFinalTarget() {
        return !this.getCurrentPosition().equals(this.getGoalPosition());
    }

    private boolean getStateAllowsMoving() {
        /*
        tstate = me.pGameObjectSyncValues[#activity_state]
        tPossibleStates = [getIntVariable("ACTIVITY_STATE_NORMAL"), getIntVariable("ACTIVITY_STATE_CREATING"), getIntVariable("ACTIVITY_STATE_INVINCIBLE_AFTER_STUN")]
        return tPossibleStates.findPos(tstate) > 0
         */
        int tActivityState = this.getSyncValue(SnowwarSyncValues.ACTIVITY_STATE);

        return tActivityState == SnowwarActivityState.ACTIVITY_STATE_STUNNED.getStateId() ||
                tActivityState == SnowwarActivityState.ACTIVITY_STATE_CREATING.getStateId() ||
                tActivityState == SnowwarActivityState.ACTIVITY_STATE_INVINCIBLE_AFTER_STUN.getStateId();
    }

    public Position getCurrentPosition() {
        return new Position(
                this.getSyncValue(SnowwarSyncValues.X),
                this.getSyncValue(SnowwarSyncValues.Y)
        );
    }

    public Position getGoalPosition() {
        return new Position(
                this.getSyncValue(SnowwarSyncValues.MOVE_TARGET_X),
                this.getSyncValue(SnowwarSyncValues.MOVE_TARGET_Y)
        );
    }

    public Position getNextPosition() {
        return new Position(
                this.getSyncValue(SnowwarSyncValues.NEXT_TILE_X),
                this.getSyncValue(SnowwarSyncValues.NEXT_TILE_Y)
        );
    }

    private void checkForSnowballCollisions() {

    }

    private void activityTimerTriggered() {

    }

}