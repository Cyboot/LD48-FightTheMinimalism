package de.timweb.ld48.minimalism.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.timweb.ld48.minimalism.Main;

public class ImageLoader {
	public static BufferedImage	tile_glass_grey;
	public static BufferedImage	tile_glass_black;
	public static BufferedImage	tile_wood;
	public static BufferedImage	tile_stone;
	public static BufferedImage	tile_lehm;
	public static BufferedImage	tile_grass;
	public static BufferedImage	tile_dirt;
	public static BufferedImage	spike;

	public static BufferedImage	start_screen;
	public static BufferedImage	background_norm;
	public static BufferedImage	background_complex;
	public static BufferedImage	shade_black;
	public static BufferedImage	shade_white;

	public static BufferedImage	rocket_blue;
	public static BufferedImage	rocket_green;
	public static BufferedImage	rocket_orange;
	public static BufferedImage	rocket_purple;
	public static BufferedImage	rocket_red;
	public static BufferedImage	rocket_yellow;

	public static BufferedImage	specials_push;

	public static BufferedImage	hero_simple_left_1;
	public static BufferedImage	hero_simple_left_2;
	public static BufferedImage	hero_simple_left_3;
	public static BufferedImage	hero_simple_right_1;
	public static BufferedImage	hero_simple_right_2;
	public static BufferedImage	hero_simple_right_3;
	public static BufferedImage	hero_complex_left_1;
	public static BufferedImage	hero_complex_left_2;
	public static BufferedImage	hero_complex_left_3;
	public static BufferedImage	hero_complex_right_1;
	public static BufferedImage	hero_complex_right_2;
	public static BufferedImage	hero_complex_right_3;

	public static BufferedImage	bat_small_1;
	public static BufferedImage	bat_small_2;

	public static void init() {
		try {
			System.out.println("loading Images... ");
			tile_glass_grey = readImage("tile_glass_grey.png");
			tile_glass_black = readImage("tile_glass_black.png");
			tile_wood = readImage("tile_wood.png");
			tile_stone = readImage("tile_stone.png");
			tile_lehm = readImage("tile_lehm.png");
			tile_grass = readImage("tile_grass.png");
			tile_dirt = readImage("tile_dirt.png");
			spike = readImage("spike.png");

			background_norm = readImage("background_norm.png");
			background_complex = readImage("background_complex.png");
			shade_black = readImage("shade_black.png");
			shade_white = readImage("shade_white.png");
			start_screen = readImage("start_screen.png");


			rocket_blue = readImage("rocket_blue.png");
			rocket_green = readImage("rocket_green.png");
			rocket_orange = readImage("rocket_orange.png");
			rocket_purple = readImage("rocket_purple.png");
			rocket_red = readImage("rocket_red.png");
			rocket_yellow = readImage("rocket_yellow.png");

			specials_push = readImage("specials_push.png");

			BufferedImage tmp = readImage("bat_small.png");
			bat_small_1 = getCutImage(tmp, 0, 30);
			bat_small_2 = getCutImage(tmp, 30, 30);


			tmp = readImage("hero_simple.png");
			hero_simple_right_1 = getSubImage(tmp, 0, 0, 32);
			hero_simple_right_2 = getSubImage(tmp, 1, 0, 32);
			hero_simple_right_3 = getSubImage(tmp, 2, 0, 32);
			hero_simple_left_1 = getSubImage(tmp, 0, 1, 32);
			hero_simple_left_2 = getSubImage(tmp, 1, 1, 32);
			hero_simple_left_3 = getSubImage(tmp, 2, 1, 32);

			tmp = readImage("hero_complex.png");
			hero_complex_right_1 = getSubImage(tmp, 0, 0, 32);
			hero_complex_right_2 = getSubImage(tmp, 1, 0, 32);
			hero_complex_right_3 = getSubImage(tmp, 2, 0, 32);
			hero_complex_left_1 = getSubImage(tmp, 0, 1, 32);
			hero_complex_left_2 = getSubImage(tmp, 1, 1, 32);
			hero_complex_left_3 = getSubImage(tmp, 2, 1, 32);

			System.out.println("finished loading Images");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage readImage(final String res) throws IOException {
		return ImageIO.read(Main.class.getResource("/" + res));
	}

	public static BufferedImage getSubImage(final BufferedImage img, final int x, final int y, final int width) {
		return img.getSubimage(x * width, y * width, width, width);
	}

	public static BufferedImage getCutImage(final BufferedImage img, final int x, final int width) {
		return img.getSubimage(x, 0, width, img.getHeight());
	}

}