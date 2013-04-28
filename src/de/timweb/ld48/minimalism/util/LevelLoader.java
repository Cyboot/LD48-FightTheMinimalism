package de.timweb.ld48.minimalism.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.timweb.ld48.minimalism.Main;
import de.timweb.ld48.minimalism.entity.ActionEntity;
import de.timweb.ld48.minimalism.entity.ActionWallEntity;
import de.timweb.ld48.minimalism.entity.BoxEntity;
import de.timweb.ld48.minimalism.entity.EnemyEntity;
import de.timweb.ld48.minimalism.entity.GrowEntity;
import de.timweb.ld48.minimalism.entity.PushEntity;
import de.timweb.ld48.minimalism.entity.WorldEndEntity;
import de.timweb.ld48.minimalism.world.Tile;
import de.timweb.ld48.minimalism.world.World;

public class LevelLoader {

	public static void loadLevel(final int level, final World world) {
		BufferedReader breader = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("/level_"
				+ level + ".lvl")));

		Tile[][] tiles = new Tile[World.TILE_Y][World.TILE_X];

		try {
			world.setDescription(breader.readLine());

			for (int i = 0; i < World.TILE_Y; i++) {
				String line = breader.readLine();
				process(i, line, tiles, world);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		world.setTiles(tiles);

		try {
			breader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void process(final int y, final String line, final Tile[][] tiles, final World world) {
		for (int x = 0; x < line.length(); x++) {
			Tile tile = null;

			switch (line.charAt(x)) {
			case '#':
				tile = Tile.GROUND;
				break;
			case 'P':
				tile = Tile.AIR;
				world.addEntity(new PushEntity(new Vector2d(x * World.TILE_SIZE, y * World.TILE_SIZE)));
				break;
			case 'G':
				tile = Tile.AIR;
				world.addEntity(new GrowEntity(new Vector2d(x * World.TILE_SIZE, y * World.TILE_SIZE)));
				break;
			case '*':
				tile = Tile.AIR;
				world.addEntity(new WorldEndEntity(new Vector2d(x * World.TILE_SIZE, y * World.TILE_SIZE)));
				break;
			case 'B':
				tile = Tile.AIR;
				world.addEntity(new BoxEntity(new Vector2d(x * World.TILE_SIZE, y * World.TILE_SIZE)));
				break;
			case 'A':
				tile = Tile.AIR;
				world.addEntity(new ActionEntity(new Vector2d(x * World.TILE_SIZE, y * World.TILE_SIZE)));
				break;
			case 'a':
				tile = Tile.AIR;
				world.addEntity(new ActionWallEntity(new Vector2d(x * World.TILE_SIZE, y * World.TILE_SIZE)));
				break;
			case 'e':
				tile = Tile.AIR;
				world.addEntity(new EnemyEntity(new Vector2d(x * World.TILE_SIZE, y * World.TILE_SIZE)));
				break;
			default:
				tile = Tile.AIR;
				break;
			}

			tiles[y][x] = tile;
		}
	}

}
