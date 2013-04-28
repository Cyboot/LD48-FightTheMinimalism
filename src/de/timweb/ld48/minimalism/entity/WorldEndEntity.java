package de.timweb.ld48.minimalism.entity;

import java.awt.Color;

import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.SoundEffect;
import de.timweb.ld48.minimalism.util.Vector2d;
import de.timweb.ld48.minimalism.world.World;

public class WorldEndEntity extends NonSolidEntity {

	public WorldEndEntity(final Vector2d pos) {
		super(pos);
	}

	@Override
	public void collideWithPlayer(final PlayerEntity player) {
		World.getInstance().finish();
		SoundEffect.LEVEL.play();
	}

	@Override
	public void render(final Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(collisionBox.x, collisionBox.y, collisionBox.width, collisionBox.height);
	}

}
