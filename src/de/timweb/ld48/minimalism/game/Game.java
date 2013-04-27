package de.timweb.ld48.minimalism.game;

import de.timweb.ld48.minimalism.interfaces.Renderable;
import de.timweb.ld48.minimalism.interfaces.Updateable;
import de.timweb.ld48.minimalism.util.Graphics;

public class Game implements Updateable, Renderable {
	private static Game	instance	= new Game();

	private Level		currentLevel;

	public Game() {
		currentLevel = new MinLevel();
	}

	@Override
	public void render(final Graphics g) {
		currentLevel.render(g);
	}

	@Override
	public void update(final int delta) {
		if (currentLevel.isFinished()) {
			// TODO: next Level
			System.out.println("Level comlete...");
			System.out.println("New Level");
		}

		currentLevel.update(delta);
	}

	public static Game getInstance() {
		return instance;
	}

}
