package de.timweb.ld48.minimalism.game;

import java.awt.Color;
import java.awt.Rectangle;

import de.timweb.ld48.minimalism.engine.Canvas;
import de.timweb.ld48.minimalism.engine.Controls;
import de.timweb.ld48.minimalism.entity.PlayerEntity;
import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.ImageLoader;
import de.timweb.ld48.minimalism.util.SoundEffect;
import de.timweb.ld48.minimalism.util.Vector2d;
import de.timweb.ld48.minimalism.world.World;

public class LevelImpl extends Level {
	private static final int	MAX_TITEL			= 3000;
	private static final int	MAX_LEVEL_BLEND		= 800;
	private World				world;

	private int					level				= 0;

	private int					titelTimeleft;
	private int					levelBlendTimeleft	= 0;
	private boolean				levelFinished		= false;
	private int					levelType			= LEVEL_SIMPLE;
	private boolean				isWin				= false;
	private int					winTextTimeLeft;

	public LevelImpl() {
		SoundEffect.MUSIC_1.loop();
		// SoundEffect.muteSound();
		newLevel(true);
	}

	/**
	 * @param newlevel
	 *            true if new Level, false if same level
	 */
	public void newLevel(final boolean newlevel) {
		if (newlevel)
			level++;

		if (level == 7)
			levelType = LEVEL_MID;
		if (level == 12)
			levelType = LEVEL_COMPLEX;

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
		if (Controls.getInstance().wasR() || !player.isAlive()) {
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
		if (levelFinished && levelBlendTimeleft < 0) {
			newLevel(true);
		}

		if (isWin)
			winTextTimeLeft -= delta;
	}

	@Override
	public void render(final Graphics g) {
		if (levelType == LEVEL_MID) {
			g.drawImage(ImageLoader.background_norm, 0, 0);
			g.drawImage(ImageLoader.shade_white, 0, 0);
		}
		if (levelType == LEVEL_COMPLEX) {
			g.drawImage(ImageLoader.background_complex, 0, 0);

			// if (levelBlendTimeleft < 0 && !isWin)
			// g.drawImage(ImageLoader.shade_white, 0, 0);

			if (isWin)
				g.drawImage(ImageLoader.shade_black, 0, 0);

		}

		world.render(g);
		player.render(g);


		g.setColor(Color.black);
		if (titelTimeleft > 0) {
			g.drawText("Level " + level, Canvas.WIDTH / 2 - 100, Canvas.HEIGHT / 2 - 150, Graphics.font_50);

			String desc = world.getDescription();
			g.drawText(desc, Canvas.WIDTH / 2 - 150, Canvas.HEIGHT / 2 + 10, Graphics.font_20);
		}
		if (levelBlendTimeleft >= -50)
			blend.renderLevelBlend(g);

		if (isWin && winTextTimeLeft < 0) {
			SoundEffect.setMuted(true);

			g.setColor(Color.white);
			g.drawText("The End", Canvas.WIDTH / 2 - 220, Canvas.HEIGHT / 2 + 50, Graphics.font_100);
			g.drawText("Thanks for playing!", Canvas.WIDTH / 2 - 120, Canvas.HEIGHT / 2 + 100, Graphics.font_20);
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

			// System.out.println(rect2.width);
			g.setColor(Color.black);
			g.fillRect(rect.x, rect.y, rect.width, rect.height);
			g.setColor(Color.black);
			g.fillRect(rect2.x, rect2.y, rect2.width, rect2.height);
		}

		public void reset(final int level) {
			renderlevel = level;
			rect.setBounds(0, 0, 0, 0);
			rect2.setBounds(0, 0, 0, 0);
		}
	}

	@Override
	public void increaseComplexity() {
		if (level == 6) {
			SoundEffect.stopMusic();
			levelType = LEVEL_MID;
			SoundEffect.MUSIC_2.loop();
		}
		if (level == 11) {
			SoundEffect.stopMusic();
			levelType = LEVEL_COMPLEX;
			SoundEffect.MUSIC_3.loop();
		}
		if (level == 19) {
			if (!isWin)
				winTextTimeLeft = 3000;
			isWin = true;
		}
	}

	@Override
	public boolean isWon() {
		return isWin;
	}

	@Override
	public int getLevelType() {
		return levelType;
	}

}
