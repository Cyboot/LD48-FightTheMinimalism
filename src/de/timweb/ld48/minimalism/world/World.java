package de.timweb.ld48.minimalism.world;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import de.timweb.ld48.minimalism.entity.Entity;
import de.timweb.ld48.minimalism.entity.GrowEntity;
import de.timweb.ld48.minimalism.interfaces.Renderable;
import de.timweb.ld48.minimalism.interfaces.Updateable;
import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.ImageLoader;
import de.timweb.ld48.minimalism.util.LevelLoader;
import de.timweb.ld48.minimalism.util.Vector2d;

public class World implements Updateable, Renderable {
	public static final int	WORLD_01		= 1;
	public static final int	TILE_X			= 50;
	public static final int	TILE_Y			= 30;
	public static final int	TILE_SIZE		= 20;

	private static World	instance;

	private Tile[][]		tiles;
	private double			gravity			= 0.01;

	private boolean			isFinished		= false;
	private boolean			showTextures	= true;
	private List<Entity>	entities		= new ArrayList<Entity>();

	public World(final int level) {
		LevelLoader.loadLevel(level, this);

		instance = this;

		// TODO-01: Ende of World
		// TODO-02: Box
		// TODO-03: action-special
		// TODO-04: weapon-special
		// TODO-05: gravity-special
	}

	@Override
	public void render(final Graphics g) {
		g.setColor(Color.black);

		g.g().drawRect(0, 0, tiles[0].length * TILE_SIZE, tiles.length * TILE_SIZE);
		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[0].length; x++) {
				if (tiles[y][x].isSolid()) {
					if (showTextures)
						g.drawImage(ImageLoader.tile_glass_grey, x * TILE_SIZE, y * TILE_SIZE);
					else
						g.g().fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
				}
			}
		}

		for (Entity e : entities) {
			e.render(g);
		}
	}

	@Override
	public void update(final int delta) {
		for (Entity e : entities) {
			e.update(delta);
		}
	}

	public static World getInstance() {
		return instance;
	}

	public double getGravity() {
		return gravity;
	}

	public boolean isValidPos(final Vector2d pos) {
		Vector2d copy = pos.copy();

		copy.x /= TILE_SIZE;
		copy.y /= TILE_SIZE;

		// not inside the Tile-Raster --> not valid
		if (copy.x < 0 || copy.x > tiles[0].length || copy.y < 0 || copy.y > tiles.length) {
			return false;
		} else {
			boolean collide = checkForSolidEntities(pos.copy());

			// System.out.println("in raster: " + copy.x() + " : " + copy.y());
			return !collide && !tiles[copy.y()][copy.x()].isSolid();
		}
	}

	private boolean checkForSolidEntities(final Vector2d copy) {
		boolean contains = false;

		for (Entity e : entities) {
			if (e instanceof GrowEntity) {
				Rectangle collisionBox = ((GrowEntity) e).getCollisionBox();
				contains = contains || collisionBox.contains(copy.x(), copy.y());
			}
		}

		return contains;
	}

	public void finish() {
		isFinished = true;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void addEntity(final Entity e) {
		entities.add(e);
	}

	public void setTiles(final Tile[][] tiles) {
		this.tiles = tiles;
	}
}
