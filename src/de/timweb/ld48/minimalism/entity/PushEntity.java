package de.timweb.ld48.minimalism.entity;

import java.awt.image.BufferedImage;

import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.ImageLoader;
import de.timweb.ld48.minimalism.util.SoundEffect;
import de.timweb.ld48.minimalism.util.Vector2d;

public class PushEntity extends NonSolidEntity {
	private static final int	MAX_ANIM_CYLE	= 1000;

	private int					animCyle		= 0;

	public PushEntity(final Vector2d pos) {
		super(pos);
	}

	@Override
	public void update(final int delta) {
		super.update(delta);

		animCyle += delta;
		animCyle %= MAX_ANIM_CYLE;
	}


	@Override
	public void render(final Graphics g) {
		BufferedImage img = ImageLoader.getSubImage(ImageLoader.specials_push, (int) (animCyle / (MAX_ANIM_CYLE / 6.)),
				0, 20);
		g.drawImage(img, pos.x, pos.y);
	}

	@Override
	public void collideWithPlayer(final PlayerEntity player) {
		direction = new Vector2d(0, -0.75);
		player.setDirection(direction);

		SoundEffect.JUMP.play();
	}

}
