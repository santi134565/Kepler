package org.alexdev.kepler.game.games.snowstorm.util;

import org.alexdev.kepler.game.games.GameObject;
import org.alexdev.kepler.game.games.enums.GameObjectType;
import org.alexdev.kepler.game.games.snowstorm.SnowwarGame;

import java.util.*;

public abstract class SnowwarObject extends GameObject {
    private final SnowwarGame game;
    private HashMap<SnowwarSyncValues, Integer> syncValues;

    public SnowwarObject(SnowwarGame game, int id, GameObjectType gameObjectType) {
        super(id, gameObjectType);
        this.game = game;
        this.syncValues = new LinkedHashMap<>();
    }

    public Integer getSyncValue(SnowwarSyncValues val) {
        return (Integer) syncValues.get(val);
    }

    public void setSyncValue(SnowwarSyncValues val, Integer obj) {
        syncValues.put(val, obj);
    }

    public abstract void calculateFrameMovement();
    public abstract int calculateObjectChecksum();

    public HashMap<SnowwarSyncValues, Integer> getSyncValues() {
        return this.syncValues;
    }

    public SnowwarGame getGame() {
        return game;
    }
}
