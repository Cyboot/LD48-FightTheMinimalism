package de.timweb.ld48.minimalism.entity;

import java.awt.Rectangle;

import de.timweb.ld48.minimalism.game.Game;
import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.Vector2d;

public abstract class NonSolidEntity extends Entity {
	private static final int	SIZE	= 20;
	private Game				game	= Game.getInstance();

	protected Rectangle			collisionBox;

	public NonSolidEntity(final Vector2d pos) {
		super(pos);

		collisionBox = new Rectangle(pos.x(), pos.y(), SIZE, SIZE);
	}

	@Override
	public void update(final int delta) {
		PlayerEntity player = game.getPlayer();

		if (collisionBox.intersects(player.getCollisionBox())) {
			collideWithPlayer(player);
		}
	}

	public abstract void collideWithPlayer(PlayerEntity player);

	@Override
	public abstract void render(final Graphics g);
}
