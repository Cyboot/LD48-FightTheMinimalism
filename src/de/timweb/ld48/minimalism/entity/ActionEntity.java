package de.timweb.ld48.minimalism.entity;

import java.awt.Color;

import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.Vector2d;
import de.timweb.ld48.minimalism.world.World;

public class ActionEntity extends NonSolidEntity {

	public ActionEntity(final Vector2d pos) {
		super(pos);
	}

	@Override
	public void render(final Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
	}

	@Override
	public void collideWithPlayer(final PlayerEntity player) {
		if (isAlive())
			World.getInstance().performAction(this);
		kill();
	}

	@Override
	protected void onKilled() {
		// TODO-98: kill Animation
	}
}
