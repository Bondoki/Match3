package com.match3.game.animation;

import com.match3.game.entities.Tile;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by bondoki on 15.10.17.
 */

public class AnimationFalling implements Animation
{
    private static final float totalDuration = 0.0666666f;
    private static final float totalDurationInv = 15.0f;
    private AnimationHandler handler;
    public Set<Tile> tiles;

    private float currentDuration;
    private float fallLength;
    public AnimationFalling(Set<Tile> tiles, float fallLength, AnimationHandler handler)
    {
        this.handler = handler;
        this.tiles = tiles;

        this.fallLength = fallLength;
        this.currentDuration = 0;
    }

    @Override
    public void update(float delta)
    {
        // TODO Auto-generated method stub
        float currentDelta = Math.min(AnimationFalling.totalDuration - this.currentDuration, delta);
        float deltaLength = this.fallLength * currentDelta * AnimationFalling.totalDurationInv;

        for (Iterator<Tile> it = tiles.iterator(); it.hasNext(); )
        {
            Tile nextTile = it.next();

            nextTile.y -= deltaLength;
        }

        this.currentDuration += delta;
        if (this.currentDuration >= AnimationFalling.totalDuration)
        {

            this.handler.onComplete(this);
        }
    }
}