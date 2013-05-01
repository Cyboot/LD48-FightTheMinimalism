package de.timweb.ld48.minimalism.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.timweb.ld48.minimalism.util.SoundEffect;

public class Controls implements KeyListener {
	private static Controls	instance	= new Controls();

	private boolean			isCTRL		= false;
	private boolean			isUP		= false;
	private boolean			isDOWN		= false;
	private boolean			isLEFT		= false;
	private boolean			isRIGHT		= false;

	private int				space		= 0;

	private boolean			wasEnter;
	private boolean			wasF5;
	private boolean			wasF12;
	private boolean			wasR;

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
		case KeyEvent.VK_CONTROL:
			isCTRL = true;
			break;
		case KeyEvent.VK_F12:
			wasF12 = true;
			break;
		case KeyEvent.VK_F5:
			wasF5 = true;
			break;
		case KeyEvent.VK_R:
			wasR = true;
			break;
		case KeyEvent.VK_ENTER:
			wasEnter = true;
			break;
		case KeyEvent.VK_SPACE:
			if (space != 2)
				space = 1;
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
		case KeyEvent.VK_CONTROL:
			isCTRL = false;
			break;
		case KeyEvent.VK_SPACE:
			space = 0;
			break;
		case KeyEvent.VK_S:
			SoundEffect.muteSound();
			break;
		case KeyEvent.VK_M:
			SoundEffect.muteMusic();
			break;

		}
	}

	@Override
	public void keyTyped(final KeyEvent ke) {
	}

	public boolean wasSpace() {
		boolean result = (space == 1);

		if (result)
			space = 2;

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

	public boolean isCTRL() {
		return isCTRL;
	}

	public boolean wasR() {
		boolean result = wasR;
		wasR = false;

		return result;
	}

	public boolean wasEnter() {
		boolean result = wasEnter;
		wasEnter = false;

		return result;
	}

	public boolean wasF5() {
		boolean result = wasF5;
		wasF5 = false;

		return result;
	}

	public boolean wasF12() {
		boolean result = wasF12;
		wasF12 = false;

		return result;
	}
}
