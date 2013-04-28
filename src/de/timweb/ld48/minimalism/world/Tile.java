package de.timweb.ld48.minimalism.world;

import java.awt.image.BufferedImage;

import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.ImageLoader;

public enum Tile {
	AIR(' ', false), //
	GROUND('1', true), //
	GROUND_GLASS_GREY('2', true), //
	GROUND_GLASS_BLACK('3', true), //
	GROUND_DIRT('4', true), //
	GROUND_GRASS('5', true), //
	GROUND_LEHM('6', true), //
	GROUND_STONE('7', true), //
	GROUND_WOOD('8', true), //
	GROUND_INVISIBLE('9', true);

	private BufferedImage	img;
	private boolean			solid;
	private char			c;

	private Tile(final char c, final boolean solid) {
		this.solid = solid;
		this.c = c;

		switch (c) {
		case '2':
			img = ImageLoader.tile_glass_grey;
			break;
		case '3':
			img = ImageLoader.tile_glass_black;
			break;
		case '4':
			img = ImageLoader.tile_dirt;
			break;
		case '5':
			img = ImageLoader.tile_grass;
			break;
		case '6':
			img = ImageLoader.tile_lehm;
			break;
		case '7':
			img = ImageLoader.tile_stone;
			break;
		case '8':
			img = ImageLoader.tile_wood;
			break;
		}
	}

	public boolean isSolid() {
		return solid;
	}

	public static Tile getByChar(final char c) {
		for (Tile t : values()) {
			if (t.c == c)
				return t;
		}
		return null;
	}

	public void render(final Graphics g, final int x, final int y) {
		if (!solid || c == '9')
			return;

		if (img != null)
			g.drawImage(img, x, y);
		else
			g.g().fillRect(x, y, World.TILE_SIZE, World.TILE_SIZE);
	}
}
