package com.match3.game.entities;

import com.match3.game.utility.TileType;

/**
 * Created by bondoki on 27.08.17.
 */

public class Tile {

    public TileType type = TileType.NONE;
    public float x = 0;
    public float y = 0;
    public boolean isActivated = false;
    public boolean toDestroy = false;
    public int sizeX = 48; //! tile sizeX in px
    public int sizeY = 48; //! tile sizeY in px

    public Tile(TileType type, float x, float y, int sizeX, int sizeY) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
}
