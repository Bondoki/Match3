package com.match3.game.draw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.match3.game.registry.Registry;

/**
 * Created by bondoki on 27.08.17.
 */

public class Draw {

    public Registry reg;

    public TextureRegion[] textureRegions;

    public Draw() {


        textureRegions = new TextureRegion[2000];
        textureRegions[0] = new TextureRegion(new Texture("gem01_64x64.png"), 0, 0, 64, 64);
        textureRegions[1] = new TextureRegion(new Texture("gem02_64x64.png"), 0, 0, 64, 64);
        textureRegions[2] = new TextureRegion(new Texture("gem03_64x64.png"), 0, 0, 64, 64);
        textureRegions[3] = new TextureRegion(new Texture("gem04_64x64.png"), 0, 0, 64, 64);
        textureRegions[4] = new TextureRegion(new Texture("gem05_64x64.png"), 0, 0, 64, 64);
        textureRegions[5] = new TextureRegion(new Texture("gem06_64x64.png"), 0, 0, 64, 64);
        //for (int i = 0; i < 8; i++) {
        //    textureRegions[i] = new TextureRegion(new Texture("puzzlequestsprites.png"), i * 64, 0, 64, 64);
        //}

    }

    public void update() {

        // tell the camera to update its matrices.
        reg.camera.update();
        reg.batch.setProjectionMatrix(reg.camera.combined);

        Gdx.gl.glClearColor(0.2f, 0.2f, 0.6f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        reg.batch.begin();
        reg.batch.draw(reg.img, 0, 0);


        for (int i = 0; i < reg.tiles.length; i++) {
            for (int j = 0; j < reg.tiles.length; j++) {
                reg.batch.draw(textureRegions[reg.tiles[i][j].type],reg.tiles[i][j].x,reg.tiles[i][j].y);
            }
        }

        reg.batch.end();
    }
}
