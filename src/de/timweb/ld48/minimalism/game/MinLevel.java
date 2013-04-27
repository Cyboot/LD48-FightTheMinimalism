package de.timweb.ld48.minimalism.game;

import de.timweb.ld48.minimalism.entity.PlayerEntity;
import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.Vector2d;
import de.timweb.ld48.minimalism.world.World;

public class MinLevel extends Level {
	private World			world;
	private PlayerEntity	player	= new PlayerEntity(new Vector2d(80, 80));

	public MinLevel() {
		world = new World(World.WORLD_01);
	}

	@Override
	public void update(final int delta) {
		world.update(delta);
		player.update(delta);

	}

	@Override
	public void render(final Graphics g) {
		world.render(g);
		player.render(g);
	}

}
