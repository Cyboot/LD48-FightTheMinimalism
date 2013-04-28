package de.timweb.ld48.minimalism.game;

import java.awt.Color;
import java.awt.Rectangle;

import de.timweb.ld48.minimalism.engine.Canvas;
import de.timweb.ld48.minimalism.engine.Controls;
import de.timweb.ld48.minimalism.entity.PlayerEntity;
import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.Vector2d;
import de.timweb.ld48.minimalism.world.World;

public class MinLevel extends Level {
	private static final int	MAX_TITEL			= 5000;
	private static final int	MAX_LEVEL_BLEND		= 800;
	private World				world;

	private int					level				= 0;

	private int					titelTimeleft;
	private int					levelBlendTimeleft	= 0;
	private boolean				levelFinished		= false;

	public MinLevel() {
		newLevel(true);
	}

	/**
	 * @param newlevel
	 *            true if new Level, false if same level
	 */
	public void newLevel(final boolean newlevel) {
		if (newlevel)
			level++;

		world = new World(level);
		player = new PlayerEntity(new Vector2d(30, Canvas.HEIGHT - 150));

		titelTimeleft = MAX_TITEL;
		levelFinished = false;
	}

	@Override
	public void update(final int delta) {
		if (Controls.getInstance().wasF12()) {
			world.finish();
		}
		if (Controls.getInstance().wasR()) {
			newLevel(false);
		}

		titelTimeleft -= delta;


		world.update(delta);
		player.update(delta);

		if (world.isFinished() && !levelFinished) {
			levelFinished = true;
			levelBlendTimeleft = MAX_LEVEL_BLEND;
			blend.reset(level);
		}

		levelBlendTimeleft -= delta;
		if (levelFinished && levelBlendTimeleft < MAX_LEVEL_BLEND / 2) {
			newLevel(true);
		}
	}

	@Override
	public void render(final Graphics g) {
		world.render(g);
		player.render(g);

		if (levelBlendTimeleft >= -50)
			blend.renderLevelBlend(g);


		g.setColor(Color.black);
		if (titelTimeleft > 0) {
			g.drawText("Level " + level, Canvas.WIDTH / 2 - 100, Canvas.HEIGHT / 2 - 150, Graphics.font_50);

			String desc = world.getDescription();
			g.drawText(desc, Canvas.WIDTH / 2 - 150, Canvas.HEIGHT / 2 + 10, Graphics.font_20);
		}
	}

	private LevelBlend	blend	= new LevelBlend();

	private class LevelBlend {
		private Rectangle	rect		= new Rectangle();
		private Rectangle	rect2		= new Rectangle();
		private int			HEIGHT		= Canvas.HEIGHT;
		private int			WIDTH		= Canvas.WIDTH;
		private int			HEIGHT_2	= Canvas.HEIGHT / 2;
		private int			WIDTH_2		= Canvas.WIDTH / 2;

		private int			renderlevel	= 1;

		private void renderLevelBlend(final Graphics g) {

			switch (renderlevel % 6) {
			case 0:
				// 1 rect, left-up to right-down
				rect.x = 0;
				rect.y = 0;
				rect.width = (MAX_LEVEL_BLEND - levelBlendTimeleft) * WIDTH / MAX_LEVEL_BLEND;
				rect.height = (MAX_LEVEL_BLEND - levelBlendTimeleft) * HEIGHT / MAX_LEVEL_BLEND;
				break;
			case 3:
				// 1 rect, centered
				int time = (MAX_LEVEL_BLEND / 2 - Math.abs(MAX_LEVEL_BLEND / 2 - levelBlendTimeleft))
						* (2000 / MAX_LEVEL_BLEND);
				rect.x = WIDTH_2 - time / 2;
				rect.y = (int) (HEIGHT_2 - (time / 3.3334));
				rect.width = time;
				rect.height = (int) (time / 1.666666667);
				break;
			case 2:
				// 1 rect, left to right
				rect.x = 0;
				rect.y = 0;
				rect.width = (MAX_LEVEL_BLEND - levelBlendTimeleft) * WIDTH / MAX_LEVEL_BLEND;
				rect.height = HEIGHT;
				break;
			case 4:
				// 2 Rect, Middle to side
				rect.x = WIDTH_2;
				rect.y = 0;
				rect.width = (MAX_LEVEL_BLEND - levelBlendTimeleft) * WIDTH_2 / MAX_LEVEL_BLEND;
				rect.height = HEIGHT;

				rect2.x = WIDTH_2;
				rect2.y = 0;
				rect2.width = -(MAX_LEVEL_BLEND - levelBlendTimeleft) * WIDTH_2 / MAX_LEVEL_BLEND;
				rect2.height = HEIGHT;
				break;
			case 5:
				// 2 Rect, side from different sides
				rect.x = 0;
				rect.y = 0;
				rect.width = (MAX_LEVEL_BLEND - levelBlendTimeleft) * WIDTH / MAX_LEVEL_BLEND;
				rect.height = HEIGHT_2;

				rect2.x = WIDTH;
				rect2.y = HEIGHT_2;
				rect2.width = -(MAX_LEVEL_BLEND - levelBlendTimeleft) * WIDTH / MAX_LEVEL_BLEND;
				rect2.height = HEIGHT_2;
				break;
			case 1:
				// 2 Rect, side to middle
				rect.x = 0;
				rect.y = 0;
				rect.width = (MAX_LEVEL_BLEND - levelBlendTimeleft) * WIDTH_2 / MAX_LEVEL_BLEND;
				rect.height = HEIGHT;

				rect2.x = WIDTH;
				rect2.y = 0;
				rect2.width = -(MAX_LEVEL_BLEND - levelBlendTimeleft) * WIDTH_2 / MAX_LEVEL_BLEND;
				rect2.height = HEIGHT;
				break;
			}

			System.out.println(rect2.width);
			g.setColor(Color.gray);
			g.fillRect(rect.x, rect.y, rect.width, rect.height);
			g.setColor(Color.gray);
			g.fillRect(rect2.x, rect2.y, rect2.width, rect2.height);
		}

		public void reset(final int level) {
			renderlevel = level;
			rect.setBounds(0, 0, 0, 0);
			rect2.setBounds(0, 0, 0, 0);
		}
	}

}
