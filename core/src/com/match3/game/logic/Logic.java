package com.match3.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.match3.game.entities.Tile;
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

        //Check if all animations are done before continuing the game
        if (reg.animations.size() > 0)
            return;

        // swap has happend
        if(reg.swapOccurred==true)
        {   System.out.println("Swap Tiles " );

                /*TileType swapTmpType = reg.tiles[reg.activeRow][reg.activeCol].type;
                reg.tiles[reg.activeRow][reg.activeCol].type = reg.tiles[reg.activeRowToSwap][reg.activeColToSwap].type;
                reg.tiles[reg.activeRowToSwap][reg.activeColToSwap].type = swapTmpType;
                */

                Tile swapTmpTile = reg.tiles[reg.activeRow][reg.activeCol];
                reg.tiles[reg.activeRow][reg.activeCol] = reg.tiles[reg.activeRowToSwap][reg.activeColToSwap];
                reg.tiles[reg.activeRowToSwap][reg.activeColToSwap] = swapTmpTile;

                reg.swapOccurred = false;

        }



        // check for matches
        if (reg.gameState == GameState.FIND_MATCH) {

            if(checkMatches() == false)
                reg.gameState = GameState.USERS_TURN;
            else
                reg.gameState = GameState.SCORING_MATCH;

        }

        // scoring matches
        if (reg.gameState == GameState.SCORING_MATCH) {
            scoreMatches();
            deleteMatchTiles();
            reg.gameState = GameState.FALLING_TILES;
        }

        // update the tiles
        if (reg.gameState == GameState.FALLING_TILES) {
            //fallingTiles();
            randomTiles();
            reg.gameState = GameState.FIND_MATCH;
        }

    }

    public boolean isSwapSuccessfulDryRun(int rowA, int colA, int rowB, int colB)
    {
        boolean moveIsPossible = false;

        TileType swapTmpType = reg.tiles[rowA][colA].type;
        reg.tiles[rowA][colA].type = reg.tiles[rowB][colB].type;
        reg.tiles[rowB][colB].type = swapTmpType;

        moveIsPossible = checkMatchesDryRun();

        /*if(!moveIsPossible)
        {
            reg.tiles[rowB][colB].type = reg.tiles[rowA][colA].type;
            reg.tiles[rowA][colA].type = swapTmpType;

        }*/

        //unswap
        reg.tiles[rowB][colB].type = reg.tiles[rowA][colA].type;
        reg.tiles[rowA][colA].type = swapTmpType;

        return moveIsPossible;
    }

    public boolean checkMatchesDryRun()
    {
        boolean foundMatch = false;

        //avoid short-circuit operators
        // smallest amount is sufficient
        foundMatch = checkMatchInRowDryRun(3) | checkMatchInColumnDryRun(3);
        return foundMatch;

    }

    public boolean checkMatches()
    {
        boolean foundMatch = false;
        System.out.println("check match");

        //avoid short-circuit operators
        foundMatch = checkMatchInRow(5) | checkMatchInColumn(5) | checkMatchInRow(4) | checkMatchInColumn(4) | checkMatchInRow(3) | checkMatchInColumn(3);

        return foundMatch;

    }

    public boolean checkMatchInRow(int length) {

        boolean foundMatchInRow = false;

        for (int row = 0; row < reg.tiles.length; row++) {
            for (int col = 0; col < reg.tiles.length; col++) {
                if ((reg.tiles[row][col].type != TileType.MATCH) && (reg.tiles[row][col].type != TileType.NONE) ) {
                    if ((row + (length-1)) < reg.tiles.length) {

                        int count = 0;
                        for(int idx = 1; idx < length; idx++)
                        {
                            if(reg.tiles[row][col].type == reg.tiles[row + idx][col].type)
                                count++;
                        }

                        if(count == (length-1))
                        {
                            for (int k = 0; k < length; k++) {
                                reg.tiles[row + k][col].type = TileType.MATCH;
                            }
                            System.out.println("Match Row" + length + " at row " + row + " col " + col);
                            foundMatchInRow = true;
                        }

                    }
                }
            }
        }

        return foundMatchInRow;
    }

    public boolean checkMatchInColumn(int length) {

        boolean foundMatchInCol = false;

        for (int row = 0; row < reg.tiles.length; row++) {
            for (int col = 0; col < reg.tiles.length; col++) {
                if ((reg.tiles[row][col].type != TileType.MATCH) && (reg.tiles[row][col].type != TileType.NONE) ) {
                    if ((col + (length-1)) < reg.tiles.length) {

                        int count = 0;
                        for(int idx = 1; idx < length; idx++)
                        {
                            if(reg.tiles[row][col].type == reg.tiles[row][col + idx].type)
                                count++;
                        }

                        if(count == (length-1))
                        {
                            for (int k = 0; k < length; k++) {
                                reg.tiles[row][col + k].type = TileType.MATCH;
                            }
                            System.out.println("Match Col" + length + " at row " + row + " col " + col);
                            foundMatchInCol = true;
                        }

                    }
                }
            }
        }

        return foundMatchInCol;
    }

    public boolean checkMatchInRowDryRun(int length) {

        boolean foundMatchInRow = false;

        for (int row = 0; row < reg.tiles.length; row++) {
            for (int col = 0; col < reg.tiles.length; col++) {
                if ((reg.tiles[row][col].type != TileType.MATCH) && (reg.tiles[row][col].type != TileType.NONE) ) {
                    if ((row + (length-1)) < reg.tiles.length) {

                        int count = 0;
                        for(int idx = 1; idx < length; idx++)
                        {
                            if(reg.tiles[row][col].type == reg.tiles[row + idx][col].type)
                                count++;
                        }

                        if(count == (length-1))
                        {
                            //for (int k = 0; k < length; k++) {
                            //    reg.tiles[row + k][col].type = TileType.MATCH;
                            //}
                            System.out.println("Match Dry Run Row" + length + " at row " + row + " col " + col + " type "+ reg.tiles[row][col].type);
                            foundMatchInRow = true;
                        }

                    }
                }
            }
        }

        return foundMatchInRow;
    }

    public boolean checkMatchInColumnDryRun(int length) {

        boolean foundMatchInCol = false;

        for (int row = 0; row < reg.tiles.length; row++) {
            for (int col = 0; col < reg.tiles.length; col++) {
                if ((reg.tiles[row][col].type != TileType.MATCH) && (reg.tiles[row][col].type != TileType.NONE) ) {
                    if ((col + (length-1)) < reg.tiles.length) {

                        int count = 0;
                        for(int idx = 1; idx < length; idx++)
                        {
                            if(reg.tiles[row][col].type == reg.tiles[row][col + idx].type)
                                count++;
                        }

                        if(count == (length-1))
                        {
                            //for (int k = 0; k < length; k++) {
                            //    reg.tiles[row][col + k].type = TileType.MATCH;
                            //}
                            System.out.println("Match Dry Run Col" + length + " at row " + row + " col " + col + " type "+ reg.tiles[row][col].type);
                            foundMatchInCol = true;
                        }

                    }
                }
            }
        }

        return foundMatchInCol;
    }

    public void scoreMatches()
    {
        int count = 0;

        for (int row = 0; row < reg.tiles.length; row++) {
            for (int col = 0; col < reg.tiles.length; col++) {
                if (reg.tiles[row][col].type == TileType.MATCH) {
                    count++;
                }
            }
        }

        reg.score += count;

        System.out.println("Count: " + count +"   ->  Score: "+reg.score);
    }

    public void deleteMatchTiles()
    {

        for (int row = 0; row < reg.tiles.length; row++) {
            for (int col = 0; col < reg.tiles.length; col++) {
                if (reg.tiles[row][col].type == TileType.MATCH) {
                    reg.tiles[row][col].type = TileType.NONE;
                }
            }
        }
    }

    public void randomTiles()
    {

        //random adding
        for (int row = 0; row < reg.tiles.length; row++) {
            for (int col = 0; col < reg.tiles.length; col++) {
                if (reg.tiles[row][col].type == TileType.NONE) {
                    reg.tiles[row][col].type = TileType.getRandom();
                }
            }
        }
    }

    public void fallingTiles()
    {
        /*
        random adding
        for (int row = 0; row < reg.tiles.length; row++) {
            for (int col = 0; col < reg.tiles.length; col++) {
                if (reg.tiles[row][col].type == TileType.NONE) {
                    reg.tiles[row][col].type = TileType.getRandom();
                }
            }
        }
        */

        boolean repeatFalling = false;

        // falling from top to bottom
        for (int row = (reg.tiles.length-1); row >= 0; row--) {
            for (int col = 0; col < reg.tiles.length; col++) {
                if (reg.tiles[row][col].type == TileType.NONE) {
                    if ((row - 1) >= 0) {
                        //if (reg.tiles[row - 1][col].type == TileType.NONE)
                        {

                            // Swap Tiles
                            reg.tiles[row][col].type = reg.tiles[row-1][col].type;
                            reg.tiles[row-1][col].type = TileType.NONE;
                            repeatFalling = true;
                        }
                    }
                }
            }
        }

        // add new tile on top
        for (int col = 0; col < reg.tiles.length; col++) {
                if (reg.tiles[0][col].type == TileType.NONE) {

                        reg.tiles[0][col].type = TileType.getRandom();
                        System.out.println("Added a Tile in col " + col);

                        repeatFalling = true;

                }
        }

        if (repeatFalling) {
            fallingTiles();
        }


    }
}
