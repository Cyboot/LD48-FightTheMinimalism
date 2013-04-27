package de.timweb.ld48.minimalism.game;

import de.timweb.ld48.minimalism.interfaces.Renderable;
import de.timweb.ld48.minimalism.interfaces.Updateable;

public abstract class Level implements Updateable, Renderable {
	private boolean	isFinished	= false;

	protected void finish() {
		isFinished = true;
	}

	public boolean isFinished() {
		return isFinished;
	}
}
