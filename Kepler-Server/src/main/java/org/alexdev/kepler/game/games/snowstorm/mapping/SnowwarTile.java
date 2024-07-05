package org.alexdev.kepler.game.games.snowstorm.mapping;

import org.alexdev.kepler.game.games.snowstorm.objects.SnowwarSnowbballObject;

import java.util.Comparator;
import java.util.List;

public class SnowwarTile {
    private int X;
    private int Y;
    private List<SnowwarMapItem> items;
    private SnowwarMapItem highestItem;
    private boolean isBlocked;

    public SnowwarTile(int x, int y, boolean isBlocked, List<SnowwarMapItem> items) {
        this.X = x;
        this.Y = y;
        this.isBlocked = isBlocked;

        this.items = items;
        this.items.sort(Comparator.comparingDouble((SnowwarMapItem item) -> item.getPosition().getZ()));

        for (var item : this.items) {
            if ((this.highestItem == null) || (item.getPosition().getZ() > this.highestItem.getPosition().getZ())) {
                this.highestItem = item;
            }
        }
    }

    public boolean isWalkable() {
        if (this.isBlocked) {
            return false;
        }

        if (this.highestItem != null) {
            return false;
        }

        return true;
    }

    public SnowwarMapItem getHighestItem() {
        return highestItem;
    }

    public List<SnowwarMapItem> getItems() {
        return items;
    }

    public boolean isHeightBlocking(SnowwarSnowbballObject.SnowballTrajectory trajectory) {
        if (this.highestItem == null) {
            return false;
        }

        if (trajectory == SnowwarSnowbballObject.SnowballTrajectory.LONG_TRAJECTORY) {
            return false;
        }

        if (trajectory == SnowwarSnowbballObject.SnowballTrajectory.SHORT_TRAJECTORY) {
            return this.highestItem.getHeight() > 1;
        }

        if (trajectory == SnowwarSnowbballObject.SnowballTrajectory.QUICK_THROW) {
            return this.highestItem.getHeight() > 0;
        }

        return false;
    }
}
