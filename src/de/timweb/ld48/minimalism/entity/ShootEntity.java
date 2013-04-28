package de.timweb.ld48.minimalism.entity;

import java.awt.Color;
import java.util.List;

import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.SoundEffect;
import de.timweb.ld48.minimalism.util.Vector2d;
import de.timweb.ld48.minimalism.world.World;

public class ShootEntity extends Entity {
	public ShootEntity(final Vector2d pos, final boolean left) {
		super(pos);

		direction = new Vector2d(left ? -0.7 : 0.7, 0);
		setSolid(false);
		SoundEffect.SHOOT.play();
	}

	@Override
	public void update(final int delta) {
		List<Entity> list = World.getInstance().getEntities();

		for (Entity e : list) {
			if (e instanceof EnemyEntity) {
				if (((EnemyEntity) e).getCollisionBox().contains(pos.x, pos.y))
					e.kill();
			}
		}

		if (!move(delta))
			kill();
	}

	@Override
	public void render(final Graphics g) {
		g.setColor(Color.black);
		g.fillCircleCentered(pos.x, pos.y, 5);
	}

}
