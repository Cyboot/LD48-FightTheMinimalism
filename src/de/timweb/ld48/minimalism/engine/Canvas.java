package de.timweb.ld48.minimalism.engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import de.timweb.ld48.minimalism.game.Game;
import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.ImageLoader;

public class Canvas extends java.awt.Canvas implements Runnable {
	public static final int		WIDTH			= 1000;
	public static final int		HEIGHT			= 600;
	private static final long	TARGET_FPS		= 80;
	private static final long	TARGET_DELTA	= 1000 / TARGET_FPS;

	private static Canvas		instance		= new Canvas();

	private Game				game			= Game.getInstance();
	private long				fps;


	private Canvas() {
		Dimension dim = new Dimension(WIDTH - 10, HEIGHT - 10);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.addKeyListener(Controls.getInstance());
	}

	public void start() {
		Thread t = new Thread(this);

		ImageLoader.init();
		Game.getInstance().restart();
		t.start();
	}

	@Override
	public void run() {
		long delta = 0;

		while (true) {
			long start = System.currentTimeMillis();

			update((int) delta);

			BufferStrategy bs = getBufferStrategy();
			if (bs == null) {
				createBufferStrategy(3);
				continue;
			}
			render(Graphics.instance(bs.getDrawGraphics()));

			if (bs != null)
				bs.show();

			long timepassed = System.currentTimeMillis() - start;
			if (timepassed < TARGET_DELTA) {
				try {
					Thread.sleep(TARGET_DELTA - timepassed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			delta = System.currentTimeMillis() - start;
			if (delta > 0)
				fps = 1000 / delta;
		}
	}


	private void render(final Graphics g) {
		g.clear();

		game.render(g);

		g.setColor(Color.red);
		g.g().drawString("FPS: " + fps, WIDTH - 60, 15);

		g.dispose();
		Toolkit.getDefaultToolkit().sync();
	}

	private void update(final int delta) {
		game.update(delta);
	}

	public static Canvas getInstance() {
		return instance;
	}
}
