package de.timweb.ld48.minimalism.util;

import java.awt.Color;
import java.awt.Image;

import de.timweb.ld48.minimalism.engine.Canvas;

public class Graphics {
	private static Graphics		instance	= new Graphics();

	private java.awt.Graphics	g;

	public void drawImage(final Image img, final double x, final double y) {
		g.drawImage(img, (int) x, (int) y, null);
	}

	public java.awt.Graphics g() {
		return g;
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

}
