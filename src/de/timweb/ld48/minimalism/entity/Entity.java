package de.timweb.ld48.minimalism.entity;

import de.timweb.ld48.minimalism.interfaces.Renderable;
import de.timweb.ld48.minimalism.interfaces.Updateable;
import de.timweb.ld48.minimalism.util.Vector2d;
import de.timweb.ld48.minimalism.world.World;

public abstract class Entity implements Updateable, Renderable {
	protected Vector2d	pos;
	protected Vector2d	direction	= new Vector2d();

	private boolean		isAlive		= true;
	private boolean		isSolid		= true;

	public Entity() {
		this(new Vector2d());
	}

	public Entity(final Vector2d pos) {
		this.pos = pos;
	}

	protected boolean move(final int delta) {
		return move(direction.x * delta, direction.y * delta);
	}

	protected boolean move(final double x, final double y) {
		pos.add(x, y);

		boolean isValid = World.getInstance().isValidPos(pos);
		if (!isValid)
			pos.add(-x, -y);

		return isValid;
	}

	protected void onKilled() {
	}

	public void kill() {
		isAlive = false;
		onKilled();
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setSolid(final boolean isSolid) {
		this.isSolid = isSolid;
	}

	public Vector2d getPos() {
		return pos;
	}
}
