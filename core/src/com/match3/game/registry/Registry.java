package com.match3.game.registry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.match3.game.draw.Draw;
import com.match3.game.entities.Tile;
import com.match3.game.logic.Logic;

/**
 * Created by bondoki on 27.08.17.
 */

public class Registry {

    // Window Information
    public String windowName = "Match 3 Game";
    public int windowWidth = 600;
    public int windowHeight = 800;
    public OrthographicCamera camera;
    //public StretchViewport viewport;
    public FitViewport viewport;

    // Tiles
    public Tile[][] tiles;
    public float tilesXOffset = 44;//250;
    public float tilesYOffset = 44;//250;

    // draw
    public Draw draw;
    // logic
    public Logic logic;

    public SpriteBatch batch;
    public Texture img;

    // Active Tile
    public boolean tileIsActive = false;
    public int activeX = 0;
    public int activeY = 0;

    public Registry(){

        // Window Information
        Gdx.graphics.setTitle(windowName);
        Gdx.graphics.setWindowedMode(windowWidth,windowHeight);
        camera = new OrthographicCamera(windowWidth,windowHeight);
        camera.setToOrtho(false, windowWidth, windowHeight);
        //viewport = new StretchViewport(windowWidth,windowHeight,camera);
        viewport = new FitViewport(windowWidth,windowHeight,camera);
        viewport.apply();

        draw = new Draw();
        draw.reg = this;

        logic = new Logic();
        logic.reg = this;

        batch = new SpriteBatch();
        img = new Texture("background.png");

        // Setup Tiles
        tiles = new Tile[8][8];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                Tile newTile = new Tile(MathUtils.random(0,5), (i * 64) + tilesXOffset, (j * 64) + tilesYOffset);
                tiles[i][j] = newTile;
                System.out.println("Tiles: " + i + " " + j + " with type "+ tiles[i][j].type);
            }
        }
    }

    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
