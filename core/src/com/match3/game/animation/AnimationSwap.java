package com.match3.game.animation;


import com.match3.game.entities.Tile;
import com.match3.game.utility.TileType;

public class AnimationSwap implements Animation
{
	private static final float totalDuration = 0.1f;
	private static final float totalDurationInv = 10.0f;
	private AnimationHandler handler;
	public Tile gem1;
	public Tile gem2;
	private float currentDuration;
	private float distanceX;
	private float distanceY;
	public boolean swapBack;
	public AnimationSwap(Tile gem1, Tile gem2, boolean swapBack, AnimationHandler handler)
	{
		this.handler = handler;
		this.gem1 = gem1;
		this.gem2 = gem2;
		//this.gem1.activity = 2;
		//this.gem2.activity = 2;
		this.swapBack = swapBack;
		this.distanceX = this.gem2.x - this.gem1.x;
		this.distanceY = this.gem2.y - this.gem1.y;
		this.currentDuration = 0;
	}

	@Override
	public void update(float delta)
	{
		// TODO Auto-generated method stub
		float currentDelta = Math.min(AnimationSwap.totalDuration - this.currentDuration, delta);
		float deltaX = this.distanceX * currentDelta * AnimationSwap.totalDurationInv;
		float deltaY = this.distanceY * currentDelta * AnimationSwap.totalDurationInv;
		this.gem1.x += deltaX;
		this.gem1.y += deltaY;
		this.gem2.x -= deltaX;
		this.gem2.y -= deltaY;
		this.currentDuration += delta;

		System.out.println("Tile1 X"+gem1.x+" y"+gem1.y);
		System.out.println("Tile2 X"+gem2.x+" y"+gem2.y);

		if (this.currentDuration >= AnimationSwap.totalDuration)
		{
			if (this.swapBack)
			{
				System.out.println("Tile1 SwapReturn X"+gem1.x+" y"+gem1.y);
				System.out.println("Tile2 SwapReturn X"+gem2.x+" y"+gem2.y);

				this.swapBack = false;
				this.currentDuration = 0;
				this.distanceX = -this.distanceX;
				this.distanceY = -this.distanceY;
				this.update(delta - currentDelta);
			}
			else
			{
				System.out.println("Tile1 END X"+gem1.x+" y"+gem1.y);
				System.out.println("Tile2 END X"+gem2.x+" y"+gem2.y);


				this.handler.onComplete(this);
			}
		}
	}
}
