package de.timweb.ld48.minimalism.entity;

import de.timweb.ld48.minimalism.interfaces.Renderable;
import de.timweb.ld48.minimalism.interfaces.Updateable;
import de.timweb.ld48.minimalism.util.Vector2d;
import de.timweb.ld48.minimalism.world.World;

public abstract class Entity implements Updateable, Renderable {
	protected final double	SPEED				= 0.0005;
	protected final double	MAXSPEED			= SPEED * 500;

	protected Vector2d		pos;
	protected Vector2d		direction			= new Vector2d();
	protected Vector2d		gravity				= new Vector2d();

	private boolean			isAlive				= true;
	protected boolean		isGravityEffected	= true;
	protected boolean		isSolid				= true;

	public Entity() {
		this(new Vector2d());
	}

	public Entity(final Vector2d pos) {
		this.pos = pos;
	}

	@Override
	public void update(final int delta) {
		World world = World.getInstance();

		// if (delta > 40)
		// delta += 120;
		// else if (delta > 30)
		// delta += 70;
		// else if (delta > 24)
		// delta += 35;
		// else if (delta > 15)
		// delta += 10;
		// System.out.println("         " + delta);

		gravity.add(0, 0.00087 * delta);
	}

	protected boolean move(final int delta) {
		World world = World.getInstance();
		Vector2d target = pos.copy();


		boolean isGravityValid = true;
		boolean isDirectionValid = true;

		final int STEPS = 2;
		for (int i = 0; i < STEPS; i++) {
			if (isDirectionValid) {
				target.set(pos);
				target.add(direction.x * delta / STEPS, direction.y * delta / STEPS);
				isDirectionValid = world.isValidPos(target, this);
			}
			if (isDirectionValid || !isSolid) {
				pos.set(target);
			}

			if (isGravityValid && isGravityEffected) {
				target.set(pos);
				target.add(0, (gravity.y * delta) / STEPS);
				// System.out.println(gravity.y);
				isGravityValid = world.isValidPos(target, this);
			}
			if (isGravityValid && isGravityEffected) {
				pos.set(target);
			}
		}

		if (!isDirectionValid && isGravityEffected)
			direction.set(0, 0);
		if (!isGravityValid) {
			gravity.set(0, 0);
			direction.y = 0;
		}

		if (!isGravityEffected)
			return isDirectionValid;

		// Slow down direction
		if (direction.x > 0)
			direction.x -= SPEED / 2 * delta;
		if (direction.x < 0)
			direction.x += SPEED / 2 * delta;
		if (direction.y > 0)
			direction.y -= SPEED / 2 * delta;
		if (direction.y < 0)
			direction.y += SPEED / 2 * delta;

		// System.out.println(direction.x);

		// Stop if very slow
		if (Math.abs(direction.x) < SPEED / 2 * delta)
			direction.x = 0;
		if (Math.abs(direction.y) < SPEED / 2 * delta)
			direction.y = 0;

		// not to fast (MAXSPEED)
		if (direction.x > MAXSPEED)
			direction.x = MAXSPEED;
		if (direction.x < -MAXSPEED)
			direction.x = -MAXSPEED;

		// System.out.println("dir: " + direction + " ---- gravity: " +
		// gravity);
		return isDirectionValid && isGravityValid;
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
		this.isGravityEffected = isSolid;
	}

	public Vector2d getPos() {
		return pos;
	}
}
