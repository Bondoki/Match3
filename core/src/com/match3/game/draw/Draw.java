package com.match3.game.draw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.match3.game.registry.Registry;
import com.match3.game.utility.TileType;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by bondoki on 27.08.17.
 */

public class Draw {

    public Registry reg;

    public TextureRegion[] textureRegions;

    public TextureRegion[] texNumbers;

    Stack<Integer> stack = new Stack<Integer>();

    public Draw() {


        textureRegions = new TextureRegion[8];
        textureRegions[0] = new TextureRegion(new Texture("gem01_48x48.png"), 0, 0, Registry.TILESIZE, Registry.TILESIZE);
        textureRegions[1] = new TextureRegion(new Texture("gem02_48x48.png"), 0, 0, Registry.TILESIZE, Registry.TILESIZE);
        textureRegions[2] = new TextureRegion(new Texture("gem03_48x48.png"), 0, 0, Registry.TILESIZE, Registry.TILESIZE);
        textureRegions[3] = new TextureRegion(new Texture("gem04_48x48.png"), 0, 0, Registry.TILESIZE, Registry.TILESIZE);
        textureRegions[4] = new TextureRegion(new Texture("gem05_48x48.png"), 0, 0, Registry.TILESIZE, Registry.TILESIZE);
        textureRegions[5] = new TextureRegion(new Texture("gem06_48x48.png"), 0, 0, Registry.TILESIZE, Registry.TILESIZE);
        /*textureRegions[0] = new TextureRegion(new Texture("gem01_64x64.png"), 0, 0, 64, 64);
        textureRegions[1] = new TextureRegion(new Texture("gem02_64x64.png"), 0, 0, 64, 64);
        textureRegions[2] = new TextureRegion(new Texture("gem03_64x64.png"), 0, 0, 64, 64);
        textureRegions[3] = new TextureRegion(new Texture("gem04_64x64.png"), 0, 0, 64, 64);
        textureRegions[4] = new TextureRegion(new Texture("gem05_64x64.png"), 0, 0, 64, 64);
        textureRegions[5] = new TextureRegion(new Texture("gem06_64x64.png"), 0, 0, 64, 64);
        */

        texNumbers = new TextureRegion[10];
        int xsize=36;
        int ysize=56;
        texNumbers[0] = new TextureRegion(new Texture("number00_36x56.png"), 0, 0, xsize, ysize);
        texNumbers[1] = new TextureRegion(new Texture("number01_36x56.png"), 0, 0, xsize, ysize);
        texNumbers[2] = new TextureRegion(new Texture("number02_36x56.png"), 0, 0, xsize, ysize);
        texNumbers[3] = new TextureRegion(new Texture("number03_36x56.png"), 0, 0, xsize, ysize);
        texNumbers[4] = new TextureRegion(new Texture("number04_36x56.png"), 0, 0, xsize, ysize);
        texNumbers[5] = new TextureRegion(new Texture("number05_36x56.png"), 0, 0, xsize, ysize);
        texNumbers[6] = new TextureRegion(new Texture("number06_36x56.png"), 0, 0, xsize, ysize);
        texNumbers[7] = new TextureRegion(new Texture("number07_36x56.png"), 0, 0, xsize, ysize);
        texNumbers[8] = new TextureRegion(new Texture("number08_36x56.png"), 0, 0, xsize, ysize);
        texNumbers[9] = new TextureRegion(new Texture("number09_36x56.png"), 0, 0, xsize, ysize);




    }

    public void getScoreInDigits()
    {
        stack.clear();

        int number = reg.score;

        while (number > 0) {
            stack.push( number % 10 );
            number = number / 10;
        }



    }

    public void update() {

        // get score as digits
        getScoreInDigits();

        // tell the camera to update its matrices.
        reg.camera.update();
        reg.batch.setProjectionMatrix(reg.camera.combined);

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.6f, 1);
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        reg.batch.begin();
        reg.batch.draw(reg.img, 0, 0);

        /*for (int i = 0; i <= 9; i++)
        {
            reg.batch.draw(texNumbers[i], 60+i*texNumbers[i].getRegionWidth(),reg.windowHeight-texNumbers[i].getRegionHeight()-20 );
        }
        */

        int countIdx = 0;
        int stackSize = stack.size();
        while (!stack.isEmpty()) {

            int numberOnStack = stack.pop();
            reg.batch.draw(texNumbers[numberOnStack], reg.windowWidth-60+(-1*stackSize+countIdx)*texNumbers[numberOnStack].getRegionWidth(),reg.windowHeight-texNumbers[numberOnStack].getRegionHeight()-20 );
            countIdx++;
        }

        // adding 0 to unused score fields
        for (int i = 0; i <= 9-countIdx; i++)
        {
            reg.batch.draw(texNumbers[0], 60+i*texNumbers[0].getRegionWidth(),reg.windowHeight-texNumbers[0].getRegionHeight()-20 );
        }



        for (int row = 0; row < reg.tiles.length; row++) {
            for (int col = 0; col < reg.tiles.length; col++) {

                switch (reg.tiles[row][col].type)
                     {
                    case NONE:
                                break;
                    case ORANGE: reg.batch.draw(textureRegions[0],reg.tiles[row][col].x,reg.tiles[row][col].y, reg.tiles[row][col].sizeX, reg.tiles[row][col].sizeY);
                                break;
                    case BLUE:  reg.batch.draw(textureRegions[1],reg.tiles[row][col].x,reg.tiles[row][col].y, reg.tiles[row][col].sizeX, reg.tiles[row][col].sizeY);
                                 break;
                    case RED: reg.batch.draw(textureRegions[2],reg.tiles[row][col].x,reg.tiles[row][col].y, reg.tiles[row][col].sizeX, reg.tiles[row][col].sizeY);
                                break;
                    case GREEN: reg.batch.draw(textureRegions[3],reg.tiles[row][col].x,reg.tiles[row][col].y, reg.tiles[row][col].sizeX, reg.tiles[row][col].sizeY);
                                 break;
                    case PURPLE: reg.batch.draw(textureRegions[4],reg.tiles[row][col].x,reg.tiles[row][col].y, reg.tiles[row][col].sizeX, reg.tiles[row][col].sizeY);
                                break;
                    case YELLOW: reg.batch.draw(textureRegions[5],reg.tiles[row][col].x,reg.tiles[row][col].y, reg.tiles[row][col].sizeX, reg.tiles[row][col].sizeY);
                                break;
                    default:
                        break;
                    }
                }
        }


        reg.batch.end();
    }
}
