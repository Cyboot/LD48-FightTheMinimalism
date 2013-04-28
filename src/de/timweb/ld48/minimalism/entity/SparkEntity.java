package de.timweb.ld48.minimalism.entity;

import java.awt.Color;

import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.Vector2d;

public class SparkEntity extends Entity {
	private static double	SPEED		= .25;

	private int				timeLeft	= 1000;
	private Color			color;

	public SparkEntity(final Vector2d pos, final Vector2d direction, final Color color) {
		super(pos.copy());

		this.color = color;
		this.direction = direction.setLength(SPEED);

		setSolid(false);
		isGravityEffected = false;
	}

	@Override
	public void update(final int delta) {
		pos.add(direction.x * delta, direction.y * delta);

		timeLeft -= delta;
		if (timeLeft < 0)
			kill();
	}

	@Override
	public void render(final Graphics g) {
		g.setColor(color);
		g.fillRect(pos.x, pos.y, 4, 4);
	}

}
