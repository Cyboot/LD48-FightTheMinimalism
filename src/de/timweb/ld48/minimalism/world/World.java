package de.timweb.ld48.minimalism.world;

import java.awt.Color;

import de.timweb.ld48.minimalism.interfaces.Renderable;
import de.timweb.ld48.minimalism.interfaces.Updateable;
import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.RandomUtil;
import de.timweb.ld48.minimalism.util.Vector2d;

public class World implements Updateable, Renderable {
	public static final int		WORLD_01	= 1;
	private static final int	TILE_SIZE	= 10;

	private static World		instance;

	private Tile[][]			tiles;
	private int					offsetX		= 0;
	private int					offsetY		= 0;
	private double				gravity		= 0.01;

	public World(final int level) {
		switch (level) {
		case WORLD_01:
			offsetX = 50;
			offsetY = 50;

			tiles = new Tile[30][40];
			initTiles(level);
			break;
		default:
			break;
		}

		instance = this;
	}

	private void initTiles(final int level) {
		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[0].length; x++) {
				if (RandomUtil.randBoolean(0.9))
					tiles[y][x] = Tile.AIR;
				else
					tiles[y][x] = Tile.GROUND;
				if (y == tiles.length - 1)
					tiles[y][x] = Tile.GROUND;
			}
		}

	}

	@Override
	public void render(final Graphics g) {
		g.setColor(Color.black);

		g.g().drawRect(offsetX, offsetY, tiles[0].length * TILE_SIZE, tiles.length * TILE_SIZE);
		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[0].length; x++) {
				if (tiles[y][x].isSolid())
					g.g().fillRect(x * TILE_SIZE + offsetX, y * TILE_SIZE + offsetY, TILE_SIZE, TILE_SIZE);
			}
		}
	}

	@Override
	public void update(final int delta) {

	}

	public static World getInstance() {
		return instance;
	}

	public double getGravity() {
		return gravity;
	}

	public boolean isValidPos(final Vector2d pos) {
		Vector2d copy = pos.copy();

		copy.add(-offsetX, -offsetY);
		copy.x /= TILE_SIZE;
		copy.y /= TILE_SIZE;

		// not inside the Tile-Raster --> not valid
		if (copy.x < 0 || copy.x > tiles[0].length || copy.y < 0 || copy.y > tiles.length) {
			return false;
		} else {
			// System.out.println("in raster: " + copy.x() + " : " + copy.y());
			return !tiles[copy.y()][copy.x()].isSolid();
		}
	}
}
