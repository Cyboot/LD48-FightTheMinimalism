package de.timweb.ld48.minimalism.util;

import java.util.Random;


public class RandomUtil {
	private static Random	random	= new Random(19);

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
}
