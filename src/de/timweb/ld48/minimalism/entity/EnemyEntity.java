package de.timweb.ld48.minimalism.entity;

import java.awt.Rectangle;

import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.ImageLoader;
import de.timweb.ld48.minimalism.util.Vector2d;

public class EnemyEntity extends NonSolidEntity {
	protected static final int	SIZE		= 30;

	private int					animCyle	= 0;

	public EnemyEntity(final Vector2d pos) {
		super(pos);

		collisionBox = new Rectangle(pos.x(), pos.y(), SIZE, SIZE);
	}

	@Override
	public void update(final int delta) {
		super.update(delta);

		animCyle += delta;
		animCyle %= 500;
	}

	@Override
	public void collideWithPlayer(final PlayerEntity player) {
		player.kill();
	}

	@Override
	public void render(final Graphics g) {
		// g.setColor(Color.yellow);
		// g.drawRect(collisionBox.x, collisionBox.y, collisionBox.width,
		// collisionBox.height);

		if (animCyle > 250)
			g.drawImage(ImageLoader.bat_small_1, pos.x, pos.y);
		else
			g.drawImage(ImageLoader.bat_small_2, pos.x, pos.y);
	}

}
