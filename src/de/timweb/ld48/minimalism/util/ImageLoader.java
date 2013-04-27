package de.timweb.ld48.minimalism.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.timweb.ld48.minimalism.Main;

public class ImageLoader {
	public static BufferedImage	test;

	public static void init() {
		try {
			System.out.println("loading Images... ");
			test = readImage("bg_body_mouth.png");

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

	public static BufferedImage getCutImage(final BufferedImage img, final int width) {
		return img.getSubimage(0, 0, width, img.getHeight());
	}

}