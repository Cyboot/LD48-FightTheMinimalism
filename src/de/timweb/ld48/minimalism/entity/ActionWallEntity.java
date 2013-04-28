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

	public ActionWallEntity(final Vector2d pos, final boolean startSolid) {
		super(pos);

		isSolid = startSolid;
		collisionBox = new Rectangle();
		if (isSolid)
			makeSolid();
	}

	@Override
	public void render(final Graphics g) {
		if (!isSolid)
			return;

		g.setColor(Color.cyan);
		g.drawRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
	}

	@Override
	public Rectangle getCollisionBox() {
		return collisionBox;
	}

	@Override
	public void actionPerformed() {
		if (isSolid)
			kill();
		else
			makeSolid();
	}

	private void makeSolid() {
		collisionBox = new Rectangle(pos.x(), pos.y(), SIZE, SIZE);
		isSolid = true;
	}

	@Override
	protected void onKilled() {
		// TODO-99: kill Animation
	}

}
