package de.timweb.ld48.minimalism.entity;

import java.awt.Color;

import de.timweb.ld48.minimalism.engine.Controls;
import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.Vector2d;

public class PlayerEntity extends Entity {
	private final int	SIZE		= 8;

	private Controls	controls	= Controls.getInstance();

	public PlayerEntity(final Vector2d pos) {
		super(pos);
	}

	@Override
	public void update(final int delta) {
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

		if (controls.wasSpace()) {
			direction.add(0, -SPEED * 100 * delta);
		}

		move(delta);
	}

	@Override
	public void render(final Graphics g) {
		g.setColor(Color.GRAY);
		g.fillCircleCentered(pos.x, pos.y, SIZE);
	}

}
