package de.timweb.ld48.minimalism.game;

import de.timweb.ld48.minimalism.entity.PlayerEntity;
import de.timweb.ld48.minimalism.interfaces.Renderable;
import de.timweb.ld48.minimalism.interfaces.Updateable;

public abstract class Level implements Updateable, Renderable {
	private boolean			isFinished	= false;
	protected PlayerEntity	player;

	protected void finish() {
		isFinished = true;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public PlayerEntity getPlayer() {
		return player;
	}
}
