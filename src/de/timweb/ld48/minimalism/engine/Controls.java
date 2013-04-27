package de.timweb.ld48.minimalism.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controls implements KeyListener {
	private static Controls	instance	= new Controls();

	private boolean			isUP		= false;
	private boolean			isDOWN		= false;
	private boolean			isLEFT		= false;
	private boolean			isRIGHT		= false;
	private boolean			wasSpace	= false;

	public static Controls getInstance() {
		return instance;
	}

	@Override
	public void keyPressed(final KeyEvent ke) {
		switch (ke.getKeyCode()) {
		case KeyEvent.VK_UP:
			isUP = true;
			break;
		case KeyEvent.VK_DOWN:
			isDOWN = true;
			break;
		case KeyEvent.VK_LEFT:
			isLEFT = true;
			break;
		case KeyEvent.VK_RIGHT:
			isRIGHT = true;
			break;
		case KeyEvent.VK_SPACE:
			wasSpace = true;
			break;
		}
	}

	@Override
	public void keyReleased(final KeyEvent ke) {
		switch (ke.getKeyCode()) {
		case KeyEvent.VK_UP:
			isUP = false;
			break;
		case KeyEvent.VK_DOWN:
			isDOWN = false;
			break;
		case KeyEvent.VK_LEFT:
			isLEFT = false;
			break;
		case KeyEvent.VK_RIGHT:
			isRIGHT = false;
			break;
		}
	}

	@Override
	public void keyTyped(final KeyEvent ke) {
	}

	public boolean wasSpace() {
		boolean result = wasSpace;
		wasSpace = false;

		return result;
	}

	public boolean isDOWN() {
		return isDOWN;
	}

	public boolean isLEFT() {
		return isLEFT;
	}

	public boolean isRIGHT() {
		return isRIGHT;
	}

	public boolean isUP() {
		return isUP;
	}
}
