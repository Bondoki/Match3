package com.match3.game.gamestate;

import com.match3.game.registry.Registry;
import com.match3.game.utility.TileType;

/**
 * Created by bondoki on 18.10.17.
 */

public class GStateInsertTiles implements GState {

    public Registry reg;

    public GStateInsertTiles(Registry reg)
    {
        this.reg = reg;
    }
    @Override
    public void doLogic() {

        randomTiles();
        System.out.println("GStateInsertTiles -> GStateFindMatch");
        reg.changeGameState(new GStateFindMatch(reg));
    }

    public void randomTiles()
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
    }
}
