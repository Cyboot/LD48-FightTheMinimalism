package de.timweb.ld48.minimalism.entity;

import java.awt.Color;
import java.awt.Rectangle;

import de.timweb.ld48.minimalism.game.Game;
import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.Vector2d;

public class GrowEntity extends Entity {
	private static final double	GROWSPEED	= 0.2;
	private static final int	SIZE		= 20;
	private static final double	MIN_DIST	= SIZE;
	private static final double	MAX_HEIGHT	= SIZE * 10;
	private Game				game		= Game.getInstance();

	private double				extraheight	= 0;
	private boolean				isGrowing	= false;
	private int					shrinkTimeleft;
	private boolean				isShrinking;
	private Rectangle			collisionBox;

	public GrowEntity(final Vector2d pos) {
		super(pos);

		collisionBox = new Rectangle(pos.x(), pos.y(), SIZE, SIZE);
	}

	@Override
	public void render(final Graphics g) {
		g.setColor(Color.red);
		g.fillRect(collisionBox.getX(), collisionBox.getY(), collisionBox.getWidth(), collisionBox.getHeight());
	}


	@Override
	public void update(final int delta) {
		if (isGrowing)
			extraheight += delta * GROWSPEED;
		if (isShrinking)
			extraheight -= delta * GROWSPEED;

		if (isGrowing || isShrinking) {
			collisionBox.setSize(SIZE, (int) (SIZE + extraheight));
			collisionBox.setLocation(pos.x(), (int) (pos.y - extraheight));
		}

		if (extraheight > MAX_HEIGHT) {
			extraheight = MAX_HEIGHT;
			isGrowing = false;

			shrinkTimeleft = 1000;
		}
		if (extraheight < 0) {
			extraheight = 0;
			isShrinking = false;
		}

		if (MAX_HEIGHT - extraheight < 0.1 && shrinkTimeleft < 0) {
			isShrinking = true;
			isGrowing = false;
		}

		if (extraheight > 0)
			shrinkTimeleft -= delta;


		PlayerEntity player = game.getPlayer();
		double dist = player.getPos().distance(pos);
		// if (dist < MIN_DIST && !isShrinking) {
		// isGrowing = true;
		// isShrinking = false;
		// }

		// System.out.println(collisionBox.x + " --> player " +
		// player.getPos().x);
		if (Math.abs(player.pos.y - collisionBox.y) < SIZE && collisionBox.intersects(player.getCollisionBox())) {
			player.pos.y = collisionBox.getY() - 1;
			isGrowing = true;
			isShrinking = false;
		}

	}

	public Rectangle getCollisionBox() {
		return collisionBox;
	}
}
