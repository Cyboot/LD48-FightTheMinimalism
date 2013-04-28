package de.timweb.ld48.minimalism.world;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import de.timweb.ld48.minimalism.entity.ActionEntity;
import de.timweb.ld48.minimalism.entity.Entity;
import de.timweb.ld48.minimalism.interfaces.ActionListenerEntity;
import de.timweb.ld48.minimalism.interfaces.CollidableEntity;
import de.timweb.ld48.minimalism.interfaces.Renderable;
import de.timweb.ld48.minimalism.interfaces.Updateable;
import de.timweb.ld48.minimalism.util.Graphics;
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
	private String			description		= null;
	private List<Entity>	entitiesToAdd	= new ArrayList<Entity>();

	public World(final int level) {
		LevelLoader.loadLevel(level, this);

		instance = this;
	}

	@Override
	public void render(final Graphics g) {
		g.setColor(Color.black);

		g.g().drawRect(0, 0, tiles[0].length * TILE_SIZE, tiles.length * TILE_SIZE);
		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[0].length; x++) {
				tiles[y][x].render(g, x * TILE_SIZE, y * TILE_SIZE);
			}
		}

		for (Entity e : entities) {
			e.render(g);
		}
	}

	@Override
	public void update(final int delta) {
		if (!entitiesToAdd.isEmpty()) {
			entities.addAll(entitiesToAdd);
			entitiesToAdd.clear();
		}

		List<Entity> deadEntites = null;

		for (Entity e : entities) {
			e.update(delta);

			if (!e.isAlive()) {
				if (deadEntites == null)
					deadEntites = new ArrayList<Entity>();
				deadEntites.add(e);
			}
		}

		if (deadEntites != null)
			entities.removeAll(deadEntites);
	}

	public static World getInstance() {
		return instance;
	}

	public double getGravity() {
		return gravity;
	}

	public boolean isValidPos(final Vector2d pos, final Entity self) {
		Vector2d copy = pos.copy();

		copy.x /= TILE_SIZE;
		copy.y /= TILE_SIZE;

		// not inside the Tile-Raster --> not valid
		if (copy.x < 0 || copy.x > tiles[0].length || copy.y < 0 || copy.y > tiles.length) {
			return false;
		} else {
			boolean collide = checkForSolidEntities(pos.copy(), self);

			// System.out.println("in raster: " + copy.x() + " : " + copy.y());
			return !collide && !tiles[copy.y()][copy.x()].isSolid();
		}
	}

	private boolean checkForSolidEntities(final Vector2d copy, final Entity self) {
		boolean contains = false;

		for (Entity e : entities) {
			// Do not collide with yourself
			if (e == self)
				continue;

			if (e instanceof CollidableEntity) {
				Rectangle collisionBox = ((CollidableEntity) e).getCollisionBox();
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
		entitiesToAdd.add(e);
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void setTiles(final Tile[][] tiles) {
		this.tiles = tiles;
	}

	public void performAction(final ActionEntity actionEntity) {
		for (Entity e : entities) {
			if (e instanceof ActionListenerEntity)
				((ActionListenerEntity) e).actionPerformed();
		}
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
