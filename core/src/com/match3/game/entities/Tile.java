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
    public float sizeX = 48; //! tile sizeX in px
    public float sizeY = 48; //! tile sizeY in px

    public Tile(TileType type, float x, float y, float sizeX, float sizeY) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
}
