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

    public Tile(int type, float x, float y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }
}
