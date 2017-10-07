package com.match3.game.registry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.match3.game.animation.Animation;
import com.match3.game.animation.AnimationHandler;
import com.match3.game.animation.AnimationSwap;
import com.match3.game.utility.GameState;
import com.match3.game.draw.Draw;
import com.match3.game.entities.Tile;
import com.match3.game.logic.Logic;
import com.match3.game.utility.TileType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bondoki on 27.08.17.
 */

public class Registry extends ScreenAdapter implements AnimationHandler{

    // Window Information
    public String windowName = "Match 3 Game";
    public int windowWidth = 480;
    public int windowHeight = 800;
    public OrthographicCamera camera;
    //public StretchViewport viewport;
    public FitViewport viewport;

    // Tiles
    public Tile[][] tiles;
    public float tilesXOffset = 16;//(windowWidth-8*TILESIZE)/2// ;
    public float tilesYOffset = 48;//250;

    // draw
    public Draw draw;
    // logic
    public Logic logic;

    public SpriteBatch batch;
    public Texture img;

    // Active Tile
    public boolean tileIsActive = false;
    public int activeCol = 0;
    public int activeRow = 0;

    public static final int TILESIZE = 56; // 48, 56, 64, 256

    //! Initial state maybe causes already matches
    public GameState gameState = GameState.FIND_MATCH;//GameState.USERS_TURN;

    public int score = 0;

    public List<Animation> animations = new ArrayList<Animation>();

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
        tiles = new Tile[8][8]; // [row] [column]  - 0 0 is upper left corner
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles.length; col++) {
                //Tile newTile = new Tile(MathUtils.random(0,5), (col * TILESIZE) + tilesXOffset, ((tiles.length -1 -row) * TILESIZE) + tilesYOffset, TILESIZE, TILESIZE);
                Tile newTile = new Tile(TileType.getRandom(), (col * TILESIZE) + tilesXOffset, ((tiles.length -1 -row) * TILESIZE) + tilesYOffset, TILESIZE, TILESIZE);

                tiles[row][col] = newTile;
                System.out.println("Tiles: " + row + " " + col + " with type "+ tiles[row][col].type);
            }
        }
    }

    @Override
    public void render(float delta)
    {

        //Play animations
        for (int index = 0; index < this.animations.size(); ++index)
        {
            this.animations.get(index).update(delta);
        }


    }

    public void swapAnimation(int row1, int col1, int row2, int col2, boolean swapback)
    {
        this.animations.add(new AnimationSwap(tiles[row1][col1], tiles[row2][col2], swapback, this));


    }

    @Override
    public void onComplete(Animation animation)
    {

        if (animation.getClass() == AnimationSwap.class)
        {
            AnimationSwap swapAnimation = (AnimationSwap)animation;

        }
        this.animations.remove(animation);
    }


    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
