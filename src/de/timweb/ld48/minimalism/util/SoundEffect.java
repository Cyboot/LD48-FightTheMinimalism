package de.timweb.ld48.minimalism.util;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import de.timweb.ld48.minimalism.Main;
import de.timweb.ld48.minimalism.game.Game;
import de.timweb.ld48.minimalism.game.Level;

public enum SoundEffect {
	MUSIC_1("music_1.wav"), //
	MUSIC_2("music_2.wav"), //
	MUSIC_3("music_3.wav"), //
	EXPLOSION("explosion.wav"), //
	HURT("hurt.wav"), //
	JUMP("jump.wav"), //
	KILL("kill.wav"), //
	LEVEL("level.wav"), //
	PICKUP("pickup.wav"), //
	SHOOT("shoot.wav");

	private static boolean	isMuted			= false;
	private static boolean	isMusicMuted	= false;
	private Clip[]			clips;
	private int				curser;

	SoundEffect(final String file) {
		try {
			URL url = Main.class.getResource("/" + file);

			if (file != "music.wav")
				clips = new Clip[5];
			else
				clips = new Clip[2];

			for (int i = 0; i < clips.length; i++) {

				AudioInputStream ais = AudioSystem.getAudioInputStream(url);

				AudioFormat format = ais.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, format);
				clips[i] = (Clip) AudioSystem.getLine(info);
				clips[i].open(ais);

				ais.close();
			}

		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void play() {
		if (isMuted)
			return;
		new Thread() {
			@Override
			public void run() {
				try {
					clips[curser].stop();
					clips[curser].setFramePosition(0);
					clips[curser].start();
				} catch (Exception e) {
				}
			}
		}.start();

		curser = ++curser % clips.length;
	}

	public void loop() {
		try {

			clips[0].loop(-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void stop() {
		try {
			for (Clip c : clips) {
				c.stop();
				c.setFramePosition(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void init() {
		values();
	}

	public static void stopMusic() {
		try {
			switch (Game.getInstance().getCurrentLevel().getLevelType()) {
			case Level.LEVEL_SIMPLE:
				SoundEffect.MUSIC_1.stop();
				break;
			case Level.LEVEL_MID:
				SoundEffect.MUSIC_2.stop();
				break;
			case Level.LEVEL_COMPLEX:
				SoundEffect.MUSIC_3.stop();
				break;
			}
			isMusicMuted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void muteMusic() {
		if (!isMusicMuted) {
			stopMusic();
			isMusicMuted = true;
		} else {
			try {
				switch (Game.getInstance().getCurrentLevel().getLevelType()) {
				case Level.LEVEL_SIMPLE:
					SoundEffect.MUSIC_1.loop();
					break;
				case Level.LEVEL_MID:
					SoundEffect.MUSIC_2.loop();
					break;
				case Level.LEVEL_COMPLEX:
					SoundEffect.MUSIC_3.loop();
					break;
				}
				isMusicMuted = false;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void setMuted(final boolean mute) {
		isMuted = mute;
	}


	public static void muteSound() {
		if (!isMuted) {
			isMuted = true;
		} else {
			isMuted = false;
		}
	}

	public static boolean isMusicMuted() {
		return isMusicMuted;
	}

	public static boolean isSoundMuted() {
		return isMuted;
	}

}