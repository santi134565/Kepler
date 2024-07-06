package org.alexdev.kepler.game.games.snowstorm.util;

public enum SnowwarEventType {
    WALK(0),
    CREATE_SNOWBALL(3),
    THROW_SNOWBALL_AT_LOCATION(2),
    THROW_SNOWBALL_AT_PERSON(1);

    private final int eventId;

    SnowwarEventType(int eventId) {
        this.eventId = eventId;
    }

    public static SnowwarEventType getEvent(int eventId) {
        for (var event : values()) {
            if (event.eventId == eventId)
                return event;
        }
        return null;
    }

    public int getEventId() {
        return this.eventId;
    }
}
