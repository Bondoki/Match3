package com.match3.game.gamestate;

import com.match3.game.animation.AnimationAppear;
import com.match3.game.animation.AnimationFalling;
import com.match3.game.entities.Tile;
import com.match3.game.registry.Registry;
import com.match3.game.utility.TileType;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bondoki on 18.10.17.
 */

public class GStateInsertTiles implements GState {

    public Registry reg;

    public Set<Tile> InsertTiles = new HashSet<Tile>();

    public GStateInsertTiles(Registry reg)
    {
        this.reg = reg;
    }
    @Override
    public void doLogic() {

        insertRandomTilesOnTop();

        if(InsertTiles.size() != 0)
        {
            System.out.println("GStateInsertTiles -> GStateFallingTiles");
            reg.animations.add(new AnimationAppear(InsertTiles, reg.TILESIZE, reg));
            reg.changeGameState(new GStateFallingTiles(reg));
        }
        else {
            System.out.println("GStateInsertTiles -> GStateFindMatch");
            reg.changeGameState(new GStateFindMatch(reg));
        }
    }

    public void insertRandomTilesOnTop()
    {
        InsertTiles.clear();

        //random adding at top row for falling

        for (int col = 0; col < reg.tiles.length; col++) {
            if (reg.tiles[0][col].type == TileType.NONE) {
                reg.tiles[0][col].type = TileType.getRandom();
                reg.tiles[0][col].sizeX = 0;// Start at Zero and expand in animation   reg.TILESIZE;
                reg.tiles[0][col].sizeY = 0;// Start at Zero and expand in animation   reg.TILESIZE;

                reg.tiles[0][col].x = (col * reg.TILESIZE) + reg.tilesXOffset +  reg.TILESIZE/2.0f;
                reg.tiles[0][col].y = ((reg.tiles.length - 1 - 0) * reg.TILESIZE) + reg.tilesYOffset +  reg.TILESIZE/2.0f;

                InsertTiles.add(reg.tiles[0][col]);
            }
        }

    }

    /*public void randomTiles()
    {

        //random adding
        for (int row = 0; row < reg.tiles.length; row++) {
            for (int col = 0; col < reg.tiles.length; col++) {
                if (reg.tiles[row][col].type == TileType.NONE) {
                    reg.tiles[row][col].type = TileType.getRandom();
                    reg.tiles[row][col].sizeX = reg.TILESIZE;
                    reg.tiles[row][col].sizeY = reg.TILESIZE;

                    reg.tiles[row][col].x = (col * reg.TILESIZE) + reg.tilesXOffset;
                    reg.tiles[row][col].y = ((reg.tiles.length -1 -row) * reg.TILESIZE) + reg.tilesYOffset;
                }
            }
        }
    }*/
}
