package de.timweb.ld48.minimalism.entity;

import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.Vector2d;

public class EnemyEntity extends NonSolidEntity {

	public EnemyEntity(final Vector2d pos) {
		super(pos);
	}

	@Override
	public void collideWithPlayer(final PlayerEntity player) {
		player.kill();
	}

	@Override
	public void render(final Graphics g) {
		g.drawCircleCentered(pos.x, pos.y, SIZE);
	}

}
