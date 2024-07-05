package org.alexdev.kepler.game.games.snowstorm.util;

public enum SnowwarSyncValues {
    X("#x"),
    Y("#y"),
    Z("#z"),
    MOVEMENT_DIRECTION("#movement_direction"),
    TRAJECTORY("#trajectory"),
    TIME_TO_LIVE("#time_to_live"),
    INT_THROWER_ID("#int_thrower_id"),
    PARABOLA_OFFSET("#parabola_offset"),
    ROOM_INDEX("#room_index"),
    HUMAN_ID("#human_id"),
    BODY_DIRECTION("#body_direction"),
    HIT_POINTS("#hit_points"),
    SNOWBALL_COUNT("#snowball_count"),
    IS_BOT("#is_bot"),
    ACTIVITY_TIMER("#activity_timer"),
    ACTIVITY_STATE("#activity_state"),
    NEXT_TILE_X("#next_tile_x"),
    NEXT_TILE_Y("#next_tile_y"),
    MOVE_TARGET_X("#move_target_x"),
    MOVE_TARGET_Y("#move_target_y"),
    SCORE("#score"),
    PLAYER_ID("#player_id"),
    TEAM_ID("#team_id");

    private final String tag;

    SnowwarSyncValues(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
