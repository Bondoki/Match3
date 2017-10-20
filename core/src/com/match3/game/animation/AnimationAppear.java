package com.match3.game.animation;

import com.match3.game.entities.Tile;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by bondoki on 15.10.17.
 */

public class AnimationAppear implements Animation
{
    private static final float totalDuration = 0.1666666f;
    private static final float totalDurationInv = 6.0f;
    private AnimationHandler handler;
    public Set<Tile> tiles;

    private float currentDuration;
    private float gemSize;
    public AnimationAppear(Set<Tile> tiles, float gemSize, AnimationHandler handler)
    {
        this.handler = handler;
        this.tiles = tiles;

        this.gemSize = gemSize;
        this.currentDuration = 0;
        System.out.println("AnimationAppear");
    }

    @Override
    public void update(float delta)
    {
        // TODO Auto-generated method stub
        //float currentDelta = Math.min(AnimationAppear.totalDuration - this.currentDuration, delta);
        this.currentDuration += delta;
        if (this.currentDuration >= AnimationAppear.totalDuration)
        {
            // gives the last time step to achive total time
            float deltaDiff = AnimationAppear.totalDuration-(this.currentDuration-delta);

            for (Iterator<Tile> it = tiles.iterator(); it.hasNext(); )
            {
                Tile nextTile = it.next();
                nextTile.sizeX = this.gemSize;//(int) newSize;
                nextTile.sizeY = this.gemSize;//(int) newSize;

                float newPosX = -0.5f*this.gemSize * ( deltaDiff * AnimationAppear.totalDurationInv);
                float newPosY = -0.5f*this.gemSize * ( deltaDiff * AnimationAppear.totalDurationInv);
               // nextTile.x += newPosX;
               // nextTile.y += newPosY;
                nextTile.x += newPosX;
                nextTile.y += newPosY;
            }



            this.handler.onComplete(this);
        }
        else
        {
            //t=0: initSize = 0
            //t=T: endSize = TILESIZE
            // size(t) = m*t+n
            // initSize = n
            // endSize= m*T+initSize -> m= (endSize-initSize)/T
            // size(t)=(endSize-initSize)/T*t+initSize = this.gemSize/AnimationAppear.totalDurationInv *t
            //float newSize = this.gemSize * (1 - this.currentDuration * AnimationAppear.totalDurationInv);
            float newSize = this.gemSize * ( this.currentDuration * AnimationAppear.totalDurationInv);

            // avoid initial condition by using ODE
            // x = TILESIZE/(2*TOTALDURATION)*t + x0
            // dx = TILESIZE/(2*TOTALDURATION)* delta_t
            float newPosX = -0.5f*this.gemSize * ( delta * AnimationAppear.totalDurationInv);
            float newPosY = -0.5f*this.gemSize * ( delta * AnimationAppear.totalDurationInv);

            for (Iterator<Tile> it = tiles.iterator(); it.hasNext(); )
            {
                Tile nextTile = it.next();
                nextTile.sizeX =  newSize;
                nextTile.sizeY =  newSize;

                nextTile.x += newPosX;
                nextTile.y += newPosY;
            }


        }
    }
}