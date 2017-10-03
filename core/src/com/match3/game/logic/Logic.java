package com.match3.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.match3.game.registry.Registry;

/**
 * Created by bondoki on 27.08.17.
 */

public class Logic {

    public Registry reg;

    public Vector3 mouse_position = new Vector3(0,0,0);

    public void update() {

        if(Gdx.input.isTouched())
        {
            mouse_position.set(Gdx.input.getX(), Gdx.input.getY(), 0);
             reg.camera.unproject(mouse_position, reg.viewport.getScreenX(), reg.viewport.getScreenY(), reg.viewport.getScreenWidth(), reg.viewport.getScreenHeight());
           //reg.camera.unproject(mouse_position);
            //mouse_position.x += reg.windowWidth / 2;
            //mouse_position.y += reg.windowHeight / 2;

            System.out.println("Mouse Position: " + mouse_position.x + " " + mouse_position.y);
            //if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            // process user input
            for (int i = 0; i < reg.tiles.length; i++) {
                for (int j = 0; j < reg.tiles.length; j++) {

                    if ((mouse_position.x > reg.tiles[i][j].x) && (mouse_position.x < reg.tiles[i][j].x + Registry.TILESIZE) && (mouse_position.y > reg.tiles[i][j].y) && (mouse_position.y < reg.tiles[i][j].y + Registry.TILESIZE)) {

                        if (reg.tiles[i][j].type != 100) {

                            // De-activate previous tile
                            reg.tiles[reg.activeX][reg.activeY].activated = false;

                            // Active new tile
                            reg.tiles[i][j].activated = true;
                            reg.tileIsActive = true;
                            reg.activeX = i;
                            reg.activeY = j;



                           // System.out.println("Activated: " + i + " " + j);

                            reg.tiles[i][j].type =  0;

                        }

                    }

                }
            }
        }
    }
}
