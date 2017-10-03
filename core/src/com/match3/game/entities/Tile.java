package com.match3.game.entities;

/**
 * Created by bondoki on 27.08.17.
 */

public class Tile {

    public int type = 0;
    public float x = 0;
    public float y = 0;
    public boolean activated = false;
    public boolean destroy = false;
    public int sizeX = 48; //! tile sizeX in px
    public int sizeY = 48; //! tile sizeY in px

    public Tile(int type, float x, float y, int sizeX, int sizeY) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
}
