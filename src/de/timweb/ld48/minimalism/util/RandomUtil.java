package de.timweb.ld48.minimalism.util;

import java.awt.Color;
import java.util.Random;


public class RandomUtil {
	private static Random	random	= new Random(119);

	public static boolean randBoolean() {
		return random.nextBoolean();
	}


	/**
	 * 
	 * @param percent
	 *            - 0.1 = 10% true, 0.9 = 90% true
	 * @return
	 */
	public static boolean randBoolean(final double percent) {
		return random.nextDouble() < percent;
	}


	public static int randInt(final int i) {
		return random.nextInt(i);
	}

	public static Color randColor() {
		switch (randInt(6)) {
		case 0:
			return Color.blue;
		case 1:
			return Color.green;
		case 2:
			return Color.orange;
		case 3:
			return Color.magenta;
		case 4:
			return Color.red;
		case 5:
			return Color.yellow;
		default:
			return null;
		}
	}
}
