package com.match3.game.gamestate;

import com.match3.game.animation.AnimationDisappear;
import com.match3.game.entities.Tile;
import com.match3.game.registry.Registry;
import com.match3.game.utility.GameState;
import com.match3.game.utility.TileType;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bondoki on 18.10.17.
 */

public class GStateFindMatch implements GState{

    public Set<Tile> MatchedTiles = new HashSet<Tile>();

    public Registry reg;

    public GStateFindMatch(Registry reg)
    {
        this.reg = reg;
    }

    @Override
    public void doLogic() {

        if(checkMatches() == false) {
            System.out.println("GStateFindMatch false -> GStateIdleUser");
            reg.changeGameState(new GStateIdleUser(reg));
        }
        else {
            animationTilesDisappear();
            System.out.println("GStateFindMatch true -> GStateScoringMatch");
            reg.changeGameState(new GStateScoringMatch(reg, MatchedTiles));
        }
    }

    public boolean checkMatches()
    {
        boolean foundMatch = false;
        System.out.println("check match");

        //clear all matched tiles
        MatchedTiles.clear();

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
                                //reg.tiles[row + k][col].type = TileType.MATCH;

                                // add to Set
                                MatchedTiles.add(reg.tiles[row + k][col]);
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
                                //reg.tiles[row][col + k].type = TileType.MATCH;

                                // add to Set
                                MatchedTiles.add(reg.tiles[row][col + k]);
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

    public void animationTilesDisappear()
    {
        // disappear matching tile animation
        if (MatchedTiles.size() > 0)
        {
            reg.animations.add(new AnimationDisappear(MatchedTiles, reg.TILESIZE, reg));
        }
    }

}
