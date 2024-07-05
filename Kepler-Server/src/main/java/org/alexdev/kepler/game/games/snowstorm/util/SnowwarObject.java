package org.alexdev.kepler.game.games.snowstorm.util;

import org.alexdev.kepler.game.games.GameObject;
import org.alexdev.kepler.game.games.enums.GameObjectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public abstract class SnowwarObject extends GameObject {
    private HashMap<SnowwarSyncValues, Integer> syncValues;

    public SnowwarObject(int id, GameObjectType gameObjectType) {
        super(id, gameObjectType);
        this.syncValues = new LinkedHashMap<>();
    }

    public int generateChecksum() {
        List<Integer> checksumList = new ArrayList<>();

        checksumList.add(this.getGameObjectType().getObjectId());
        checksumList.add(this.getId());

        for (var val : this.syncValues.values()) {
            checksumList.add(val);
        }

        int fChecksum = 0;

        for (int i = 0; i < checksumList.size(); i++) {
            // DebugOutPut = DebugOutPut + "[" + DebugOutPut + items[i] + "], ";
            fChecksum += (checksumList.get(i)) * (i + 1);
        }

        return fChecksum;

    }

    public Integer getSyncValue(SnowwarSyncValues val) {
        return (Integer) syncValues.get(val);
    }

    public void setSyncValue(SnowwarSyncValues val, Integer obj) {
        syncValues.put(val, obj);
    }

    public HashMap<SnowwarSyncValues, Integer> getSyncValues() {
        return this.syncValues;
    }
}
