package de.timweb.ld48.minimalism.entity;

import java.awt.Color;
import java.awt.Rectangle;

import de.timweb.ld48.minimalism.engine.Controls;
import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.Vector2d;

public class PlayerEntity extends Entity {
	private final int	SIZE			= 8;

	private Controls	controls		= Controls.getInstance();

	private Rectangle	collisionBox	= new Rectangle(0, 0, 3, 3);
	private boolean		isJumpEnable	= true;

	public PlayerEntity(final Vector2d pos) {
		super(pos);
	}

	@Override
	public void update(final int delta) {
		super.update(delta);
		if (controls.isRIGHT()) {
			direction.add(SPEED * delta, 0);
		}
		if (controls.isLEFT()) {
			direction.add(-SPEED * delta, 0);
		}
		if (controls.isUP()) {
			direction.add(0, -SPEED * delta);
		}
		if (controls.isDOWN()) {
			direction.add(0, SPEED * delta);
		}

		if (controls.wasSpace() && isJumpEnable) {
			direction.add(0, -SPEED * 50 * delta);
		}

		move(delta);
	}

	@Override
	protected void move(final int delta) {
		super.move(delta);

		collisionBox.setLocation(pos.x() - 1, pos.y());
	}

	@Override
	public void render(final Graphics g) {
		g.setColor(Color.GRAY);
		g.fillCircleCentered(pos.x, pos.y, SIZE);
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
