package com.match3.game.gamestate;

import com.match3.game.animation.AnimationFalling;
import com.match3.game.entities.Tile;
import com.match3.game.registry.Registry;
import com.match3.game.utility.TileType;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by bondoki on 18.10.17.
 */

public class GStateFallingTiles implements GState {

    public Registry reg;


    public Set<Tile> FallingTiles = new HashSet<Tile>();
    public Set<Tile> ReplaceTiles = new HashSet<Tile>();



    public GStateFallingTiles(Registry reg)
    {
        this.reg = reg;
    }

    @Override
    public void doLogic() {

        for (Iterator<Tile> it = ReplaceTiles.iterator(); it.hasNext(); )
        {
            Tile nextTile = it.next();
            nextTile.type = TileType.REPLACE;
        }

        for (int row = (reg.tiles.length-1); row >= 0; row--) {
            for (int col = 0; col < reg.tiles.length; col++) {
                if (reg.tiles[row][col].type == TileType.REPLACE) {
                    if ((row - 1) >= 0) {
                        if (reg.tiles[row - 1][col].type != TileType.NONE) {

                            System.out.println("FallSwappedBeforeChecks [" + (row-1) +"]["+col+"]="+reg.tiles[row-1][col].type+" -> [" + (row) +"]["+col+"]="+reg.tiles[row][col].type);


                            reg.tiles[row][col].type = reg.tiles[row - 1][col].type;
                            reg.tiles[row - 1][col].type = TileType.NONE;

                            reg.tiles[row][col].x = (col * reg.TILESIZE) + reg.tilesXOffset;
                            reg.tiles[row][col].y = ((reg.tiles.length -1 -row) * reg.TILESIZE) + reg.tilesYOffset;

                            reg.tiles[row-1][col].x = (col * reg.TILESIZE) + reg.tilesXOffset;
                            reg.tiles[row-1][col].y = ((reg.tiles.length -1 - (row-1)) * reg.TILESIZE) + reg.tilesYOffset;
                            System.out.println("FallSwappedAfterChecks [" + (row-1) +"]["+col+"]="+reg.tiles[row-1][col].type+" -> [" + (row) +"]["+col+"]="+reg.tiles[row][col].type);

                        }
                    }

                }
            }
        }

        if(fallingTiles() == true) {
            //swapFallenTiles();
            //reg.gameState = GameState.FALLING_ANIMATION;
            animateFallingTiles();
            System.out.println("GStateFallingTiles true -> GStateFallingTiles");
        }
        else {
            System.out.println("GStateFallingTiles false -> GStateInsertTiles");
            reg.changeGameState(new GStateInsertTiles(reg));
        }


    }



    public boolean fallingTiles()
    {
        //
        //clear all falling tiles
        FallingTiles.clear();
        ReplaceTiles.clear();

        boolean repeatFalling = false;


        // falling from top to bottom
        for (int row = (reg.tiles.length-1); row >= 0; row--) {
            for (int col = 0; col < reg.tiles.length; col++) {
                if (reg.tiles[row][col].type == TileType.NONE) {
                    if ((row - 1) >= 0) {
                        if (reg.tiles[row - 1][col].type != TileType.NONE)
                        {
                            FallingTiles.add(reg.tiles[row-1][col]);
                            ReplaceTiles.add(reg.tiles[row][col]);
                            System.out.println("FallSwappedBefore [" + (row-1) +"]["+col+"]="+reg.tiles[row-1][col].type+" -> [" + (row) +"]["+col+"]="+reg.tiles[row][col].type);
                            // Swap Tiles
                            //reg.tiles[row][col].type = reg.tiles[row-1][col].type;
                            //reg.tiles[row-1][col].type = TileType.NONE;
                            repeatFalling = true;

                            //System.out.println("FallSwappedAfter [" + (row-1) +"]["+col+"]="+reg.tiles[row-1][col].type+" -> [" + (row) +"]["+col+"]="+reg.tiles[row][col].type);

                        }
                    }
                }
            }
        }

        return repeatFalling;

        // add new tile on top
            /*for (int col = 0; col < reg.tiles.length; col++) {
                if (reg.tiles[0][col].type == TileType.NONE) {

                    reg.tiles[0][col].type = TileType.getRandom();
                    System.out.println("Added a Tile in col " + col);

                    repeatFalling = true;

                }
            }*/

        //if (repeatFalling) {
        //    fallingTiles();
        //}




        /* boolean repeatFalling = false;

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

    */
    }




    public void animateFallingTiles()
    {
        // falling tile animation
        if (FallingTiles.size() > 0)
        {
            reg.animations.add(new AnimationFalling(FallingTiles, reg.TILESIZE, reg));
        }
    }
}
