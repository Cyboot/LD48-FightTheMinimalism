package de.timweb.ld48.minimalism.game;

import de.timweb.ld48.minimalism.entity.PlayerEntity;
import de.timweb.ld48.minimalism.interfaces.Renderable;
import de.timweb.ld48.minimalism.interfaces.Updateable;

public abstract class Level implements Updateable, Renderable {
	private boolean			isFinished		= false;
	protected PlayerEntity	player;

	public final static int	LEVEL_SIMPLE	= 0;
	public final static int	LEVEL_MID		= 1;
	public final static int	LEVEL_COMPLEX	= 2;

	protected void finish() {
		isFinished = true;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public PlayerEntity getPlayer() {
		return player;
	}

	public abstract int getLevelType();

	public abstract void increaseComplexity();

	public abstract boolean isWon();
}
