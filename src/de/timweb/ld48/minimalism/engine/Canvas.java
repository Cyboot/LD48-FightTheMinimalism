package de.timweb.ld48.minimalism.engine;

import java.awt.Dimension;

public class Canvas extends java.awt.Canvas implements Runnable {
	public static final int	WIDTH		= 1000;
	public static final int	HEIGHT		= 600;

	private static Canvas	instance	= new Canvas();


	private Canvas() {
		Dimension dim = new Dimension(WIDTH - 10, HEIGHT - 10);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
	}

	public void start() {
		Thread t = new Thread(this);

		t.start();
	}

	@Override
	public void run() {

	}


	public static Canvas getInstance() {
		return instance;
	}
}
