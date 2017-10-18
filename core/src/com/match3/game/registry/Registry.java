package com.match3.game.registry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.match3.game.animation.Animation;
import com.match3.game.animation.AnimationDisappear;
import com.match3.game.animation.AnimationFalling;
import com.match3.game.animation.AnimationHandler;
import com.match3.game.animation.AnimationSwap;
import com.match3.game.gamestate.GState;
import com.match3.game.gamestate.GStateFindMatch;
import com.match3.game.input.Input;
import com.match3.game.utility.GameState;
import com.match3.game.draw.Draw;
import com.match3.game.entities.Tile;
import com.match3.game.logic.Logic;
import com.match3.game.utility.TileType;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.Gdx.input;

/**
 * Created by bondoki on 27.08.17.
 */

public class Registry extends ScreenAdapter implements AnimationHandler {

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

    // inputHandler
    public Input input;

    public SpriteBatch batch;
    public Texture img;

    // Active Tile
    public boolean tileIsActive = false;
    public int activeCol = 0;
    public int activeRow = 0;

    public int activeColToSwap = 0;
    public int activeRowToSwap = 0;

    public static final int TILESIZE = 56; // 48, 56, 64, 256

    //! Initial state maybe causes already matches
    public GState gameState;// = GameState.FIND_MATCH;//GameState.USERS_TURN;

    public int score = 0;

    public List<Animation> animations = new ArrayList<Animation>();
    public boolean swapOccurred = false;

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

        input = new Input();
        input.reg = this;

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

    public void logic()
    {
        //Check if all animations are done before continuing the game
        if (animations.size() > 0)
            return;

        gameState.doLogic();
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

    public void trySwapTiles(int row1, int col1, int row2, int col2)
    {
        Tile A = tiles[row1][col1];
        Tile B = tiles[row2][col2];
        boolean success = logic.isSwapSuccessfulDryRun(row1, col1, row2, col2);

        this.animations.add(new AnimationSwap(A, B, !success, this));

        activeCol = col1;
        activeRow = row1;

        activeColToSwap = col2;
        activeRowToSwap = row2;

        if (success)
        {
            swapOccurred = true;
            System.out.println("Swapping with Match");
            /*
            TileType swapTmpType = tiles[row1][col1].type;
            tiles[row1][col1].type = tiles[row2][col2].type;
            tiles[row2][col2].type = swapTmpType;
            */

            Tile swapTmpTile = tiles[activeRow][activeCol];
            tiles[activeRow][activeCol] = tiles[activeRowToSwap][activeColToSwap];
            tiles[activeRowToSwap][activeColToSwap] = swapTmpTile;

            swapOccurred = false;

            changeGameState(new GStateFindMatch(this));//gameState=GameState.FIND_MATCH;
            //        gameState = GameState.SCORING_MATCH;
        }
        else
        {
            //changeGameState(new );
            //gameState = GameState.USERS_TURN;
           // this.animations.add(new AnimationSwap(tiles[row1][col1], tiles[row2][col2], !success, this));

        }
    }



    @Override
    public void onComplete(Animation animation)
    {
        if (animation.getClass() == AnimationDisappear.class)
        {
            AnimationDisappear disappearAnimation = (AnimationDisappear)animation;

           // gameState = GameState.SCORING_MATCH;

        }

        if (animation.getClass() == AnimationFalling.class)
        {
            AnimationFalling fallingAnimation = (AnimationFalling)animation;

          //  gameState = GameState.FALLING_TILES;

        }

        if (animation.getClass() == AnimationSwap.class)
        {
            AnimationSwap swapAnimation = (AnimationSwap)animation;
            // at least a match with no back swapping
            if (swapAnimation.swapBack==false)
            {
                //System.out.println("Animation Swap with Match completed.");
                /*TileType swapTmpType = tiles[activeRow][activeCol].type;
                tiles[activeRow][activeCol].type = tiles[activeRowToSwap][activeColToSwap].type;
                tiles[activeRowToSwap][activeColToSwap].type = swapTmpType;
                */
            }

            System.out.println("Animation Swap completed.");

        }
        this.animations.remove(animation);
    }




    public void changeGameState(GState gstate)
    {
        this.gameState = gstate;
    }

    public GState getGameState() {

        return gameState;

    }

    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
