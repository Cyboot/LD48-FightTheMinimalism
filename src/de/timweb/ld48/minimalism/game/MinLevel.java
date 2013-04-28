package de.timweb.ld48.minimalism.game;

import java.awt.Color;

import de.timweb.ld48.minimalism.engine.Canvas;
import de.timweb.ld48.minimalism.engine.Controls;
import de.timweb.ld48.minimalism.entity.PlayerEntity;
import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.Vector2d;
import de.timweb.ld48.minimalism.world.World;

public class MinLevel extends Level {
	private static final int	MAX_TITEL	= 5000;
	private World				world;

	private int					level		= 0;

	private int					titelTimeleft;

	public MinLevel() {
		newLevel();
	}

	public void newLevel() {
		world = new World(++level);
		player = new PlayerEntity(new Vector2d(30, Canvas.HEIGHT - 150));

		titelTimeleft = MAX_TITEL;
	}

	@Override
	public void update(final int delta) {
		if (Controls.getInstance().wasF12()) {
			world.finish();
		}

		titelTimeleft -= delta;


		world.update(delta);
		player.update(delta);

		if (world.isFinished()) {
			newLevel();
		}
	}

	@Override
	public void render(final Graphics g) {
		world.render(g);
		player.render(g);

		g.setColor(Color.black);
		if (titelTimeleft > 0)
			g.drawText("Level " + level, Canvas.WIDTH / 2 - 50, Canvas.HEIGHT / 2 - 20, Graphics.font_20);
	}

}
