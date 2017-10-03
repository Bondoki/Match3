package com.match3.game.draw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.match3.game.registry.Registry;
import com.match3.game.utility.TileType;

/**
 * Created by bondoki on 27.08.17.
 */

public class Draw {

    public Registry reg;

    public TextureRegion[] textureRegions;

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

    }

    public void update() {

        // tell the camera to update its matrices.
        reg.camera.update();
        reg.batch.setProjectionMatrix(reg.camera.combined);

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.6f, 1);
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        reg.batch.begin();
        reg.batch.draw(reg.img, 0, 0);


        for (int row = 0; row < reg.tiles.length; row++) {
            for (int col = 0; col < reg.tiles.length; col++) {

                switch (reg.tiles[row][col].type)
                     {
                    case NONE:
                                break;
                    case ORANGE: reg.batch.draw(textureRegions[0],reg.tiles[row][col].x,reg.tiles[row][col].y);
                                break;
                    case BLUE:  reg.batch.draw(textureRegions[1],reg.tiles[row][col].x,reg.tiles[row][col].y);
                                 break;
                    case RED: reg.batch.draw(textureRegions[2],reg.tiles[row][col].x,reg.tiles[row][col].y);
                                break;
                    case GREEN: reg.batch.draw(textureRegions[3],reg.tiles[row][col].x,reg.tiles[row][col].y);
                                 break;
                    case PURPLE: reg.batch.draw(textureRegions[4],reg.tiles[row][col].x,reg.tiles[row][col].y);
                                break;
                    case YELLOW: reg.batch.draw(textureRegions[5],reg.tiles[row][col].x,reg.tiles[row][col].y);
                                break;
                    default:
                        break;
                    }
                }
        }


        reg.batch.end();
    }
}
