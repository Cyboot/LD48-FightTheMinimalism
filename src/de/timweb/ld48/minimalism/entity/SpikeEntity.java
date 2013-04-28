package de.timweb.ld48.minimalism.entity;

import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.ImageLoader;
import de.timweb.ld48.minimalism.util.Vector2d;

public class SpikeEntity extends NonSolidEntity {

	public SpikeEntity(final Vector2d pos) {
		super(pos);
	}

	@Override
	public void collideWithPlayer(final PlayerEntity player) {
		player.kill();
	}

	@Override
	public void render(final Graphics g) {
		g.drawImage(ImageLoader.spike, pos.x, pos.y);
	}

}
