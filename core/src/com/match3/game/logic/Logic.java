package com.match3.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.match3.game.utility.GameState;
import com.match3.game.registry.Registry;
import com.match3.game.utility.TileType;

/**
 * Created by bondoki on 27.08.17.
 */

public class Logic {

    public Registry reg;

    public Vector3 mouse_position = new Vector3(0,0,0);

    public void update() {

        if(reg.gameState == GameState.USERS_TURN)
        if(Gdx.input.isTouched())
        {
            mouse_position.set(Gdx.input.getX(), Gdx.input.getY(), 0);
             reg.camera.unproject(mouse_position, reg.viewport.getScreenX(), reg.viewport.getScreenY(), reg.viewport.getScreenWidth(), reg.viewport.getScreenHeight());
           //reg.camera.unproject(mouse_position);
            //mouse_position.x += reg.windowWidth / 2;
            //mouse_position.y += reg.windowHeight / 2;

            System.out.println("Mouse Position: " + mouse_position.x + " " + mouse_position.y);

            // Check tile to left side
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            if (reg.tileIsActive) {
                if (reg.activeCol - 1 >= 0) {
                    if ((reg.tiles[reg.activeRow][reg.activeCol].type != TileType.NONE) && (reg.tiles[reg.activeRow][reg.activeCol -1].type != TileType.NONE)) {
                        if ((mouse_position.x > reg.tiles[reg.activeRow][reg.activeCol -1].x) && (mouse_position.x < reg.tiles[reg.activeRow][reg.activeCol -1].x + Registry.TILESIZE) && (mouse_position.y > reg.tiles[reg.activeRow][reg.activeCol -1].y) && (mouse_position.y < reg.tiles[reg.activeRow][reg.activeCol-1].y + Registry.TILESIZE)) {

                            // De-activate
                            reg.tileIsActive = false;
                            reg.tiles[reg.activeRow][reg.activeCol].isActivated = false;

                            // Swap Types
                            TileType swapTmpType = reg.tiles[reg.activeRow][reg.activeCol].type;
                            reg.tiles[reg.activeRow][reg.activeCol].type = reg.tiles[reg.activeRow][reg.activeCol -1].type;
                            reg.tiles[reg.activeRow][reg.activeCol -1].type = swapTmpType;

                            //clickDelayCounter = 0;

                        }
                    }
                }
            }

            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            // process user input
            for (int row = 0; row < reg.tiles.length; row++) {
                for (int col = 0; col < reg.tiles.length; col++) {

                    if ((mouse_position.x > reg.tiles[row][col].x) && (mouse_position.x < reg.tiles[row][col].x + Registry.TILESIZE) && (mouse_position.y > reg.tiles[row][col].y) && (mouse_position.y < reg.tiles[row][col].y + Registry.TILESIZE)) {

                        if (reg.tiles[row][col].type != TileType.NONE) {

                            // De-activate previous tile
                            reg.tiles[reg.activeRow][reg.activeCol].isActivated = false;

                            // Active new tile
                            reg.tiles[row][col].isActivated = true;
                            reg.tileIsActive = true;
                            reg.activeCol = col;
                            reg.activeRow = row;



                            System.out.println("Activated: Row" + row + " Col" + col);

                           // reg.tiles[i][j].type =  0;

                        }

                    }

                }
            }
        }
    }
}
