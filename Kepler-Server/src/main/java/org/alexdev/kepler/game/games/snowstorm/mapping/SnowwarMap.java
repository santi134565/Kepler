package org.alexdev.kepler.game.games.snowstorm.mapping;

import org.alexdev.kepler.game.pathfinder.Position;
import org.alexdev.kepler.game.games.snowstorm.util.SnowwarSpawn;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SnowwarMap {
    private final int mapId;
    private final ArrayList<SnowwarMapItem> itemList;
    private final ArrayList<SnowwarSpawn> spawnClusters;
    private final String compiledItems;
    private String heightMap;
    private int mapSizeY;
    private int mapSizeX;
    private SnowwarTile[][] tiles;

    public SnowwarMap(int mapId, String compiledItems, ArrayList<SnowwarMapItem> itemList, String heightMap, ArrayList<SnowwarSpawn> spawnClusters) {
        this.mapId = mapId;
        this.compiledItems = compiledItems;
        this.itemList = itemList;
        this.heightMap = heightMap;
        this.spawnClusters = spawnClusters;
        this.parseHeightMap();
    }

    public void parseHeightMap() {
        String[] lines = this.heightMap.split(Pattern.quote("|"));

        this.mapSizeY = lines.length;
        this.mapSizeX = lines[0].length();

        this.tiles = new SnowwarTile[this.mapSizeX][this.mapSizeY];

        StringBuilder temporaryHeightmap = new StringBuilder();

        for (int y = 0; y < this.mapSizeY; y++) {
            String line = lines[y];

            for (int x = 0; x < this.mapSizeX; x++) {
                String tile = Character.toString(line.charAt(x));

                var position = new Position(x, y);
                var snowStormTile = new SnowwarTile(x, y,
                        tile.equalsIgnoreCase("X"),
                        this.itemList.stream().filter(item ->
                                item.getX() == position.getX()
                                && item.getY() == position.getY())
                                .collect(Collectors.toList()));

                this.tiles[x][y] = snowStormTile;

                if (!snowStormTile.isWalkable()) {
                    tile = "x";
                } else {
                    tile = "0";
                }

                temporaryHeightmap.append(tile);
            }

            temporaryHeightmap.append("\r");
        }

        this.heightMap = temporaryHeightmap.toString();
    }

    public SnowwarTile getTile(Position position) {
        if (position.getX() < 0 || position.getY() < 0) {
            return null;
        }

        if (position.getX() >= this.mapSizeX || position.getY() >= this.mapSizeY) {
            return null;
        }

        return this.tiles[position.getX()][position.getY()];
    }

    public int getMapSizeX() {
        return mapSizeX;
    }

    public int getMapSizeY() {
        return mapSizeY;
    }

    public int getMapId() {
        return mapId;
    }

    public String getHeightMap() {
        return heightMap;
    }

    public ArrayList<SnowwarMapItem> getItems() {
        return this.itemList;
    }

    public SnowwarSpawn[] getSpawnClusters() {
        return spawnClusters.toArray(new SnowwarSpawn[0]);
    }

    public String getCompiledItems() {
        return compiledItems;
    }
}
