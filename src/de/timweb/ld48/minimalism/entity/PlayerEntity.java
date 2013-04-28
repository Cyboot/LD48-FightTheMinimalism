package de.timweb.ld48.minimalism.entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import de.timweb.ld48.minimalism.engine.Controls;
import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.ImageLoader;
import de.timweb.ld48.minimalism.util.Vector2d;

public class PlayerEntity extends Entity {
	private Controls	controls		= Controls.getInstance();

	private Rectangle	collisionBox	= new Rectangle(0, 0, 3, 3);
	private boolean		isJumpEnable	= true;

	private int			walkCyle		= 0;
	private boolean		left			= true;

	public PlayerEntity(final Vector2d pos) {
		super(pos);
	}

	@Override
	public void update(final int delta) {
		super.update(delta);
		if (controls.isRIGHT()) {
			direction.add(SPEED * delta, 0);
			left = false;
		}
		if (controls.isLEFT()) {
			direction.add(-SPEED * delta, 0);
			left = true;
		}
		if (controls.isUP()) {
			direction.add(0, -SPEED * delta);
		}
		if (controls.isDOWN()) {
			direction.add(0, SPEED * delta);
		}

		// TODO-03: Jump enable/disable
		if (controls.wasSpace() && isJumpEnable) {
			direction.add(0, -SPEED * 50 * delta);
		}

		move(delta);
	}

	@Override
	protected void move(final int delta) {
		super.move(delta);

		if (direction.x != 0) {
			walkCyle += delta;
			walkCyle %= 300;
		}
		collisionBox.setLocation(pos.x() - 1, pos.y());
	}

	@Override
	public void render(final Graphics g) {
		// g.setColor(Color.GRAY);
		// g.fillCircleCentered(pos.x, pos.y - 8, SIZE);

		BufferedImage img = left ? ImageLoader.hero_simple_left_1 : ImageLoader.hero_simple_right_1;
		if (walkCyle > 100)
			img = left ? ImageLoader.hero_simple_left_2 : ImageLoader.hero_simple_right_2;
		if (walkCyle > 200)
			img = left ? ImageLoader.hero_simple_left_3 : ImageLoader.hero_simple_right_3;

		g.drawImage(img, pos.x - 16, pos.y - 28);
	}

	public void setDirection(final Vector2d direction) {
		this.direction.set(direction);
	}

	public void setJumpEnable(final boolean isJumpEnable) {
		this.isJumpEnable = isJumpEnable;
	}

	/**
	 * 
	 * @return a 4*4 Collisionbox around the Players position
	 */
	public Rectangle getCollisionBox() {
		return collisionBox;
	}
}
