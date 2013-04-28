package de.timweb.ld48.minimalism.entity;

import java.awt.Color;
import java.awt.Rectangle;

import de.timweb.ld48.minimalism.interfaces.ActionListenerEntity;
import de.timweb.ld48.minimalism.interfaces.CollidableEntity;
import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.Vector2d;

public class ActionWallEntity extends Entity implements CollidableEntity, ActionListenerEntity {
	private static final int	SIZE	= 20;
	protected Rectangle			collisionBox;

	public ActionWallEntity(final Vector2d pos) {
		super(pos);
		collisionBox = new Rectangle(pos.x(), pos.y(), SIZE, SIZE);
	}

	@Override
	public void render(final Graphics g) {
		g.setColor(Color.blue);
		g.drawRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
	}

	@Override
	public Rectangle getCollisionBox() {
		return collisionBox;
	}

	@Override
	public void actionPerformed() {
		kill();
	}

	@Override
	protected void onKilled() {
		// TODO-99: kill Animation
	}

}
