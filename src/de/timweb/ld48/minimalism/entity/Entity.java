package de.timweb.ld48.minimalism.entity;

import de.timweb.ld48.minimalism.interfaces.Renderable;
import de.timweb.ld48.minimalism.interfaces.Updateable;
import de.timweb.ld48.minimalism.util.Vector2d;
import de.timweb.ld48.minimalism.world.World;

public abstract class Entity implements Updateable, Renderable {
	protected final double	SPEED		= 0.005;

	protected Vector2d		pos;
	protected Vector2d		direction	= new Vector2d();
	protected Vector2d		gravity		= new Vector2d();

	private boolean			isAlive		= true;
	private boolean			isSolid		= false;

	public Entity() {
		this(new Vector2d());
	}

	public Entity(final Vector2d pos) {
		this.pos = pos;
	}

	protected void move(final int delta) {
		World world = World.getInstance();
		Vector2d target = pos.copy();

		gravity.add(0, world.getGravity() * delta);

		boolean isGravityValid = true;
		boolean isDirectionValid = true;

		final int STEPS = 1;
		for (int i = 0; i < STEPS; i++) {
			if (isDirectionValid) {
				target.set(pos);
				target.add(direction.x / STEPS, direction.y / STEPS);
				isDirectionValid = world.isValidPos(target);
			}
			if (isDirectionValid) {
				pos.set(target);
			}

			if (isGravityValid) {
				target.set(pos);
				target.add(0, gravity.y / STEPS);
				isGravityValid = world.isValidPos(target);
			}
			if (isGravityValid) {
				pos.set(target);
			}
		}

		if (!isDirectionValid)
			direction.set(0, 0);
		if (!isGravityValid) {
			gravity.set(0, 0);
			direction.y = 0;
		}


		// Slow down direction
		if (direction.x > 0)
			direction.x -= SPEED / 3 * delta;
		if (direction.x < 0)
			direction.x += SPEED / 3 * delta;
		if (direction.y > 0)
			direction.y -= SPEED / 3 * delta;
		if (direction.y < 0)
			direction.y += SPEED / 3 * delta;

		if (Math.abs(direction.x) < SPEED * 4)
			direction.x = 0;
		if (Math.abs(direction.y) < SPEED * 4)
			direction.y = 0;
		System.out.println("dir: " + direction + " ---- gravity: " + gravity);

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
