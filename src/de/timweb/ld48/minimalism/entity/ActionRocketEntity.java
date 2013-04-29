package de.timweb.ld48.minimalism.entity;

import java.awt.Color;
import java.awt.image.BufferedImage;

import de.timweb.ld48.minimalism.game.Game;
import de.timweb.ld48.minimalism.game.Level;
import de.timweb.ld48.minimalism.interfaces.ActionListenerEntity;
import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.ImageLoader;
import de.timweb.ld48.minimalism.util.RandomUtil;
import de.timweb.ld48.minimalism.util.SoundEffect;
import de.timweb.ld48.minimalism.util.Vector2d;
import de.timweb.ld48.minimalism.world.World;

public class ActionRocketEntity extends EnemyEntity implements ActionListenerEntity {
	private static int		lastColor	= 0;

	private int				DURATION	= 1000 + RandomUtil.randInt(1000);

	private int				timeLeft	= DURATION;
	private boolean			isActive	= false;
	private BufferedImage	img;
	private Color			color;

	private Vector2d		startPos;

	public ActionRocketEntity(final Vector2d pos, final Color color) {
		super(pos);

		startPos = pos.copy();

		this.color = color;
		if (color == null) {
			switch (lastColor) {
			case 0:
				this.color = Color.blue;
				break;
			case 1:
				this.color = Color.green;
				break;
			case 2:
				this.color = Color.orange;
				break;
			case 3:
				this.color = Color.magenta;
				break;
			case 4:
				this.color = Color.red;
				break;
			case 5:
				this.color = Color.yellow;
				break;
			}
		}
		lastColor = (lastColor + 1) % 6;

		if (this.color == Color.blue)
			img = ImageLoader.rocket_blue;
		if (this.color == Color.green)
			img = ImageLoader.rocket_green;
		if (this.color == Color.orange)
			img = ImageLoader.rocket_orange;
		if (this.color == Color.magenta)
			img = ImageLoader.rocket_purple;
		if (this.color == Color.red)
			img = ImageLoader.rocket_red;
		if (this.color == Color.yellow)
			img = ImageLoader.rocket_yellow;
	}

	public ActionRocketEntity(final Vector2d pos) {
		this(pos, null);
	}

	@Override
	protected void onKilled() {
		SoundEffect.EXPLOSION.play();

		Game.getInstance().getCurrentLevel().increaseComplexity();
	}

	@Override
	public void actionPerformed() {
		isActive = true;
	}

	@Override
	public void update(final int delta) {
		if (!isActive && !Game.getInstance().getCurrentLevel().isWon())
			return;

		pos.y -= 400. * delta / DURATION;

		timeLeft -= delta;
		if (timeLeft < 0) {
			kill();

			if (Game.getInstance().getCurrentLevel().getLevelType() == Level.LEVEL_COMPLEX) {
				World.getInstance().addEntity(new ActionRocketEntity(startPos, color));
			}

			for (int x = -2; x <= 2; x++) {
				for (int y = -2; y <= 2; y++) {
					if (x + y == 0)
						continue;
					World.getInstance().addEntity(new SparkEntity(pos, new Vector2d(x, y), color));
				}

			}
		}
	}

	@Override
	public void render(final Graphics g) {
		// if (!isActive)
		// return;

		g.drawImage(img, pos.x, pos.y);
	}

}
