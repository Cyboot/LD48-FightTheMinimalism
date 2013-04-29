package de.timweb.ld48.minimalism.game;

import java.awt.Color;

import de.timweb.ld48.minimalism.engine.Canvas;
import de.timweb.ld48.minimalism.engine.Controls;
import de.timweb.ld48.minimalism.entity.PlayerEntity;
import de.timweb.ld48.minimalism.interfaces.Renderable;
import de.timweb.ld48.minimalism.interfaces.Updateable;
import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.ImageLoader;

public class Game implements Updateable, Renderable {
	private static Game	instance	= new Game();

	private Level		currentLevel;
	private boolean		isStarted	= false;

	public Game() {
	}

	public void restart() {
		currentLevel = new LevelImpl();

	}


	@Override
	public void render(final Graphics g) {
		if (isStarted)
			currentLevel.render(g);
		else {
			g.drawImage(ImageLoader.start_screen, 0, 0);

			g.setColor(Color.gray);
			g.drawText("Fight the Minimalism", Canvas.WIDTH / 2 - 240, Canvas.HEIGHT / 2 - 50, Graphics.font_50);
			g.drawText("<Enter> to continue", Canvas.WIDTH / 2 - 120, Canvas.HEIGHT / 2 + 100, Graphics.font_20);

			g.setColor(Color.white);
			g.drawText("m to mute/unmute music", Canvas.WIDTH / 2 + 230, Canvas.HEIGHT / 2 + 200, Graphics.font_20);
			g.drawText("s  to mute/unmute sound", Canvas.WIDTH / 2 + 230, Canvas.HEIGHT / 2 + 240, Graphics.font_20);
		}
	}

	@Override
	public void update(final int delta) {
		if (Controls.getInstance().wasEnter())
			isStarted = true;

		if (!isStarted)
			return;


		if (currentLevel.isFinished()) {
			System.out.println("Level comlete...");
			System.out.println("New Level");
		}

		currentLevel.update(delta);
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}

	public PlayerEntity getPlayer() {
		return currentLevel.getPlayer();
	}

	public static Game getInstance() {
		return instance;
	}

}
