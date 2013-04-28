package de.timweb.ld48.minimalism.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.timweb.ld48.minimalism.Main;

public class ImageLoader {
	public static BufferedImage	tile_glass_grey;
	public static BufferedImage	tile_glass_black;

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