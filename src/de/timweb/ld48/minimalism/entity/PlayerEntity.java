package de.timweb.ld48.minimalism.entity;

import java.awt.Color;

import de.timweb.ld48.minimalism.engine.Controls;
import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.Vector2d;

public class PlayerEntity extends Entity {
	private final int		SIZE		= 8;
	private final double	SPEED		= 0.1;

	private Controls		controls	= Controls.getInstance();

	public PlayerEntity(final Vector2d pos) {
		super(pos);
	}

	@Override
	public void update(final int delta) {
		if (controls.isRIGHT()) {
			move(SPEED * delta, 0);
		}
		if (controls.isLEFT()) {
			move(-SPEED * delta, 0);
		}
		if (controls.isUP()) {
			move(0, -SPEED * delta);
		}
		if (controls.isDOWN()) {
			move(0, SPEED * delta);
		}
	}

	@Override
	public void render(final Graphics g) {
		g.setColor(Color.GRAY);
		g.fillCircleCentered(pos.x, pos.y, SIZE);
	}

}
