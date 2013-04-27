package de.timweb.ld48.minimalism.entity;

import java.awt.Color;

import de.timweb.ld48.minimalism.game.Game;
import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.Vector2d;

public class PushEntity extends Entity {
	private static final double	SIZE		= 20;
	private static final double	MIN_DIST	= SIZE;
	private Game				game		= Game.getInstance();

	public PushEntity(final Vector2d pos) {
		super(pos);

	}

	@Override
	public void update(final int delta) {
		PlayerEntity player = game.getPlayer();

		double dist = player.getPos().distance(pos);
		if (dist < MIN_DIST) {
			direction = new Vector2d(0, -0.5);
			player.setDirection(direction);
		}
	}

	@Override
	public void render(final Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(pos.x - SIZE / 2, pos.y - SIZE / 2, SIZE, SIZE);
	}

}
