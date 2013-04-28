package de.timweb.ld48.minimalism.entity;

import java.awt.Color;
import java.awt.Rectangle;

import de.timweb.ld48.minimalism.game.Game;
import de.timweb.ld48.minimalism.interfaces.CollidableEntity;
import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.Vector2d;
import de.timweb.ld48.minimalism.world.World;

public class BoxEntity extends Entity implements CollidableEntity {
	private static final int	SIZE	= 20;
	protected Rectangle			collisionBox;
	private Game				game	= Game.getInstance();

	public BoxEntity(final Vector2d pos) {
		super(pos);

		collisionBox = new Rectangle(pos.x(), pos.y(), SIZE, SIZE);

	}

	@Override
	public void update(final int delta) {
		PlayerEntity player = game.getPlayer();

		if (collisionBox.intersects(player.getCollisionBox()) && Math.abs(player.pos.y - pos.y - SIZE) < 3) {
			collideWithPlayer(player, delta);
		}
	}

	public void collideWithPlayer(final PlayerEntity player, final int delta) {
		boolean playerToLeft = player.pos.x - pos.x - SIZE / 2 < 0;

		Vector2d target = pos.copy();
		if (playerToLeft)
			target.add(0.03 * delta, 0);
		else
			target.add(-0.03 * delta, 0);

		boolean isValide = World.getInstance().isValidPos(target, this)
				&& World.getInstance().isValidPos(target.copy().add(SIZE - 1, 0), this);
		if (isValide) {
			pos.set(target);
			collisionBox.x = pos.x();
		}

		// System.out.println("Valid move: " + isValide);
	}


	@Override
	public void render(final Graphics g) {
		g.setColor(Color.green);
		g.fillRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
	}

	@Override
	public Rectangle getCollisionBox() {
		return collisionBox;
	}

}
