package de.timweb.ld48.minimalism.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import de.timweb.ld48.minimalism.engine.Canvas;

public class Graphics {
	private static Graphics		instance	= new Graphics();

	public static Font			font_14;
	public static Font			font_20;
	public static Font			font_50;
	public static Font			font_100;

	private java.awt.Graphics	g;

	public void drawImage(final Image img, final double x, final double y) {
		g.drawImage(img, (int) x, (int) y, null);
	}

	public java.awt.Graphics g() {
		return g;
	}

	public void drawText(final String str, final int x, final int y) {
		drawText(str, x, y, font_14);
	}

	public void drawText(final String str, final int x, final int y, final Font font) {
		Graphics2D g2d = (Graphics2D) g;
		try {
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		} catch (Exception e) {
			System.err.println("Antialias failed for displaying the Font");
		}

		g.setFont(font);
		g.drawString(str == null ? "" : str, x, y);
	}

	public static Graphics instance(final java.awt.Graphics g) {
		instance.g = g;

		return instance;
	}

	public void setColor(final Color color) {
		g.setColor(color);
	}

	public void dispose() {
		g.dispose();
	}

	public void clear() {
		g.clearRect(0, 0, Canvas.WIDTH, Canvas.HEIGHT);
	}

	public void fillCircleCentered(final double x, final double y, final double size) {
		g.fillOval((int) (x - size / 2), (int) (y - size / 2), (int) size, (int) size);
	}

	public void drawCircleCentered(final double x, final double y, final double size) {
		g.drawOval((int) (x - size / 2), (int) (y - size / 2), (int) size, (int) size);
	}

	public void fillRect(final double x, final double y, final double width, final double height) {
		g.fillRect((int) x, (int) y, (int) width, (int) height);
	}

	public void drawRect(final double x, final double y, final double width, final double height) {
		g.drawRect((int) x, (int) y, (int) width, (int) height);
	}

	public static void init() {
		// try {
		// font_14 = Font.createFont(Font.TRUETYPE_FONT,
		// Main.class.getResourceAsStream("/cinnamon_cake.ttf"));
		// } catch (FontFormatException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// } finally {
		// if (font_14 == null) {
		// // Default Font
		// font_14 = Canvas.getInstance().getFont();
		// }
		// }
		if (font_14 == null) {
			// Default Font
			font_14 = Canvas.getInstance().getFont();
		}

		font_14 = font_14.deriveFont(Font.PLAIN, 14);
		font_20 = font_14.deriveFont(Font.BOLD, 20);
		font_50 = font_14.deriveFont(Font.BOLD, 50);
		font_100 = font_14.deriveFont(Font.BOLD, 100);
	}

}
