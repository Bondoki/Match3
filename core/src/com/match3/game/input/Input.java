package com.match3.game.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.match3.game.registry.Registry;
import com.match3.game.utility.GameState;
import com.match3.game.utility.TileType;

/**
 * Created by bondoki on 07.10.17.
 */

public class Input implements InputProcessor {

    public Registry reg;

    public Vector3 mouse_position = new Vector3(0,0,0);

    private int touchedAtX = -1;
    private int touchedAtY = -1;

    public void update() {

      /*  if(reg.gameState == GameState.USERS_TURN)
            if(Gdx.input.isTouched())
            {
                mouse_position.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                reg.camera.unproject(mouse_position, reg.viewport.getScreenX(), reg.viewport.getScreenY(), reg.viewport.getScreenWidth(), reg.viewport.getScreenHeight());

                //System.out.println("Mouse Position: " + mouse_position.x + " " + mouse_position.y);

                // Check for SWAP
                // Check tile to left side
                if (Gdx.input.isButtonPressed(com.badlogic.gdx.Input.Buttons.LEFT))
                    if (reg.tileIsActive) {
                        // swap left
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



                                }
                            }
                        }
                        // swap right
                        if (reg.activeCol + 1 < reg.tiles.length) {
                            if ((reg.tiles[reg.activeRow][reg.activeCol].type != TileType.NONE) && (reg.tiles[reg.activeRow][reg.activeCol +1].type != TileType.NONE)) {
                                if ((mouse_position.x > reg.tiles[reg.activeRow][reg.activeCol +1].x) && (mouse_position.x < reg.tiles[reg.activeRow][reg.activeCol +1].x + Registry.TILESIZE) && (mouse_position.y > reg.tiles[reg.activeRow][reg.activeCol +1].y) && (mouse_position.y < reg.tiles[reg.activeRow][reg.activeCol+1].y + Registry.TILESIZE)) {

                                    // De-activate
                                    reg.tileIsActive = false;
                                    reg.tiles[reg.activeRow][reg.activeCol].isActivated = false;

                                    // Swap Types
                                    TileType swapTmpType = reg.tiles[reg.activeRow][reg.activeCol].type;
                                    reg.tiles[reg.activeRow][reg.activeCol].type = reg.tiles[reg.activeRow][reg.activeCol +1].type;
                                    reg.tiles[reg.activeRow][reg.activeCol +1].type = swapTmpType;



                                }
                            }
                        }

                        // swap up
                        if (reg.activeRow - 1 >= 0) {
                            if ((reg.tiles[reg.activeRow][reg.activeCol].type != TileType.NONE) && (reg.tiles[reg.activeRow -1][reg.activeCol].type != TileType.NONE)) {
                                if ((mouse_position.x > reg.tiles[reg.activeRow -1][reg.activeCol].x) && (mouse_position.x < reg.tiles[reg.activeRow -1][reg.activeCol].x + Registry.TILESIZE) && (mouse_position.y > reg.tiles[reg.activeRow -1][reg.activeCol].y) && (mouse_position.y < reg.tiles[reg.activeRow -1][reg.activeCol].y + Registry.TILESIZE)) {

                                    // De-activate
                                    reg.tileIsActive = false;
                                    reg.tiles[reg.activeRow][reg.activeCol].isActivated = false;

                                    // Swap Types
                                    TileType swapTmpType = reg.tiles[reg.activeRow][reg.activeCol].type;
                                    reg.tiles[reg.activeRow][reg.activeCol].type = reg.tiles[reg.activeRow -1][reg.activeCol].type;
                                    reg.tiles[reg.activeRow -1][reg.activeCol].type = swapTmpType;



                                }
                            }
                        }

                        // swap down
                        if (reg.activeRow + 1 < reg.tiles.length) {
                            if ((reg.tiles[reg.activeRow][reg.activeCol].type != TileType.NONE) && (reg.tiles[reg.activeRow +1][reg.activeCol].type != TileType.NONE)) {
                                if ((mouse_position.x > reg.tiles[reg.activeRow +1][reg.activeCol].x) && (mouse_position.x < reg.tiles[reg.activeRow +1][reg.activeCol].x + Registry.TILESIZE) && (mouse_position.y > reg.tiles[reg.activeRow +1][reg.activeCol].y) && (mouse_position.y < reg.tiles[reg.activeRow +1][reg.activeCol].y + Registry.TILESIZE)) {

                                    // De-activate
                                    reg.tileIsActive = false;
                                    reg.tiles[reg.activeRow][reg.activeCol].isActivated = false;

                                    // Swap Types
                                    TileType swapTmpType = reg.tiles[reg.activeRow][reg.activeCol].type;
                                    reg.tiles[reg.activeRow][reg.activeCol].type = reg.tiles[reg.activeRow +1][reg.activeCol].type;
                                    reg.tiles[reg.activeRow +1][reg.activeCol].type = swapTmpType;

                                    //reg.swapAnimation(reg.activeRow, reg.activeCol, reg.activeRow +1,reg.activeCol, false);

                                }
                            }
                        }

                        // check for match
                        reg.gameState = GameState.FIND_MATCH;

                    }

                if (Gdx.input.isButtonPressed(com.badlogic.gdx.Input.Buttons.LEFT))
                    // process user input
                    if (reg.tileIsActive == false)

            }


    */
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    public int getCoordToCol(float coord)
    {
        return (int)(( coord - reg.tilesXOffset) / reg.TILESIZE);
    }

    public int getCoordToRow(float coord)
    {
        return (reg.tiles.length-1)-(int)(( coord - reg.tilesYOffset) / reg.TILESIZE);
    }

    public boolean isIdxInsideRowAndCol(int row, int col)
    {
        if((row >= 0) && (row < reg.tiles.length))
            if((col >= 0) && (col < reg.tiles.length))
                return true;

        return false;
    }

    public boolean trySwap(int posX, int posY, float swapDistance)
    {
        int dx = posX - this.touchedAtX;
        int dy = posY - this.touchedAtY;

        System.out.println("dx:" +dx + "  dy:"+dy);

        if (Math.abs(dx) > swapDistance || Math.abs(dy) > swapDistance)
        {
            int rowA = reg.activeRow;
            int colA = reg.activeCol;

            int rowB = reg.activeRow; //this.getCoordToRow(screenY);
            int colB = reg.activeCol; //this.getCoordToCol(screenX);


            if (Math.abs(dx) > Math.abs(dy)) // horizontal
            {
                colB += dx > 0 ? 1 : -1;
            }
            else // vertical
            {
                rowB += dy > 0 ? -1 : 1;
            }

            if(isIdxInsideRowAndCol(rowB, colB))
            {
                System.out.println("rowB " + rowB + " colB " + colB + " in table " + isIdxInsideRowAndCol(rowB, colB) + " type: " +reg.tiles[rowB][colB].type);
                reg.trySwapTiles(rowA, colA, rowB, colB);
                //reg.swapAnimation(rowA, colA, rowB, colB, true);

            }
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        //Check if all animations are done before continuing the game
        if (reg.animations.size() > 0)
            return false;

        // screenX equivalent to Gdx.input.getX()
        // screenY equivalent to Gdx.input.getY()
        //System.out.println("S" + screenX + " " + screenY+ " == I " + Gdx.input.getX() + " " + Gdx.input.getY());
        mouse_position.set(screenX, screenY, 0);
        reg.camera.unproject(mouse_position, reg.viewport.getScreenX(), reg.viewport.getScreenY(), reg.viewport.getScreenWidth(), reg.viewport.getScreenHeight());

        int rowIdx = this.getCoordToRow(mouse_position.y);
        int colIdx = this.getCoordToCol(mouse_position.x);

        System.out.println("S" + screenX + " " + screenY+ " == I " + mouse_position.x + " " + mouse_position.y);


        if(isIdxInsideRowAndCol(rowIdx, colIdx))
        {
            if (reg.tiles[rowIdx][colIdx].type != TileType.NONE) {

                this.touchedAtX = (int) mouse_position.x;
                this.touchedAtY = (int) mouse_position.y;

                // De-activate previous tile
                if (reg.tileIsActive)
                    reg.tiles[reg.activeRow][reg.activeCol].isActivated = false;

                // Active new tile
                reg.tiles[rowIdx][colIdx].isActivated = true;
                reg.tileIsActive = true;
                reg.activeCol = colIdx;
                reg.activeRow = rowIdx;
                System.out.println("row " + rowIdx + " col " + colIdx + " in table " + isIdxInsideRowAndCol(rowIdx, colIdx) + " type: " +reg.tiles[rowIdx][colIdx].type + "  x: "+reg.tiles[rowIdx][colIdx].x +" y: "+reg.tiles[rowIdx][colIdx].y + "  size.x: " + reg.tiles[rowIdx][colIdx].sizeX + "  size.y: " + reg.tiles[rowIdx][colIdx].sizeY);

                return true;
            }
        }
        else
        {
            //outside of the table
            // De-activate previous tile
            if (reg.tileIsActive)
                reg.tiles[reg.activeRow][reg.activeCol].isActivated = false;

            // No new Active tile
            reg.tileIsActive = false;
            reg.activeCol = -1;
            reg.activeRow = -1;

        }

        return false;

        /*
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

        return false;
        */
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        //Check if all animations are done before continuing the game
        if (reg.animations.size() > 0)
            return false;

        mouse_position.set(screenX, screenY, 0);
        reg.camera.unproject(mouse_position, reg.viewport.getScreenX(), reg.viewport.getScreenY(), reg.viewport.getScreenWidth(), reg.viewport.getScreenHeight());

        if(reg.tileIsActive == true)
         this.trySwap((int) mouse_position.x, (int) mouse_position.y, 0.25f * reg.TILESIZE);

        this.touchedAtX = -1;
        this.touchedAtY = -1;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

       /* mouse_position.set(screenX, screenY, 0);
        reg.camera.unproject(mouse_position, reg.viewport.getScreenX(), reg.viewport.getScreenY(), reg.viewport.getScreenWidth(), reg.viewport.getScreenHeight());


        if(this.trySwap((int) mouse_position.x, (int) mouse_position.y, 0.25f * reg.TILESIZE))
        {

            this.touchedAtX = -1;
            this.touchedAtY = -1;
            return true;
        }*/
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
