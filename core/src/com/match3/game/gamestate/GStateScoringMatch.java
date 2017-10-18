package com.match3.game.gamestate;

import com.match3.game.entities.Tile;
import com.match3.game.registry.Registry;
import com.match3.game.utility.GameState;
import com.match3.game.utility.TileType;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by bondoki on 18.10.17.
 */

public class GStateScoringMatch implements GState {

    public Registry reg;

    public Set<Tile> MatchedTiles = new HashSet<Tile>();

    public GStateScoringMatch(Registry reg, Set<Tile> MatchedTiles)
    {
        this.reg = reg;
        this.MatchedTiles = MatchedTiles;
    }

    @Override
    public void doLogic() {

        changeTypeToMatch();
        scoreMatches();
        deleteMatchTiles();
        System.out.println("GStateScoringMatch -> GStateFallingTiles");
        reg.changeGameState(new GStateFallingTiles(reg));
    }

    public void changeTypeToMatch()
    {
        for (Iterator<Tile> it = MatchedTiles.iterator(); it.hasNext(); )
        {
            Tile nextTile = it.next();
            nextTile.type = TileType.MATCH;
        }

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
                    reg.tiles[row][col].sizeX = reg.TILESIZE;
                    reg.tiles[row][col].sizeY = reg.TILESIZE;

                    reg.tiles[row][col].x = (col * reg.TILESIZE) + reg.tilesXOffset;
                    reg.tiles[row][col].y = ((reg.tiles.length -1 -row) * reg.TILESIZE) + reg.tilesYOffset;
                }
            }
        }

        //remove matched gems
        MatchedTiles.clear();


    }
}
