package de.timweb.ld48.minimalism.entity;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import de.timweb.ld48.minimalism.engine.Controls;
import de.timweb.ld48.minimalism.game.Game;
import de.timweb.ld48.minimalism.game.Level;
import de.timweb.ld48.minimalism.util.Graphics;
import de.timweb.ld48.minimalism.util.ImageLoader;
import de.timweb.ld48.minimalism.util.SoundEffect;
import de.timweb.ld48.minimalism.util.Vector2d;
import de.timweb.ld48.minimalism.world.World;

public class PlayerEntity extends Entity {
	private static final int	SHOOT_COOLDOWN	= 250;

	private Controls			controls		= Controls.getInstance();

	private Rectangle			collisionBox	= new Rectangle(0, 0, 3, 3);
	private boolean				isJumpEnable	= true;
	private boolean				isShotEnable	= true;

	private int					walkCyle		= 0;
	private boolean				left			= false;
	private int					lastShot		= 0;


	public PlayerEntity(final Vector2d pos) {
		super(pos);
	}

	@Override
	public void update(final int delta) {
		super.update(delta);
		if (controls.isRIGHT()) {
			direction.add(SPEED * delta, 0);
			left = false;
		}
		if (controls.isLEFT()) {
			direction.add(-SPEED * delta, 0);
			left = true;
		}
		if (controls.isUP()) {
			direction.add(0, -SPEED * delta);
		}
		if (controls.isDOWN()) {
			direction.add(0, SPEED * delta);
		}
		if (controls.isCTRL() && isShotEnable && lastShot > SHOOT_COOLDOWN) {
			World.getInstance().addEntity(new ShootEntity(pos.copy().add(0, -5), left));
			lastShot = 0;
		}

		if (controls.wasSpace() && isJumpEnable && gravity.length() < 1) {
			SoundEffect.JUMP.play();
			direction.add(0, -SPEED * 50 * 13);
		}

		lastShot += delta;

		move(delta);
	}

	@Override
	protected boolean move(final int delta) {
		boolean result = super.move(delta);

		if (direction.x != 0) {
			walkCyle += delta;
			walkCyle %= 300;
		}
		collisionBox.setLocation(pos.x() - 1, pos.y());
		return result;
	}

	@Override
	protected void onKilled() {
		SoundEffect.KILL.play();
	}


	@Override
	public void render(final Graphics g) {
		if (Game.getInstance().getCurrentLevel().getLevelType() == Level.LEVEL_SIMPLE) {
			g.setColor(Color.GRAY);
			g.fillCircleCentered(pos.x, pos.y - 8, 16);
		}
		if (Game.getInstance().getCurrentLevel().getLevelType() == Level.LEVEL_MID) {

			BufferedImage img = left ? ImageLoader.hero_simple_left_1 : ImageLoader.hero_simple_right_1;
			if (walkCyle > 100)
				img = left ? ImageLoader.hero_simple_left_2 : ImageLoader.hero_simple_right_2;
			if (walkCyle > 200)
				img = left ? ImageLoader.hero_simple_left_3 : ImageLoader.hero_simple_right_3;

			g.drawImage(img, pos.x - 16, pos.y - 28);
		}
		if (Game.getInstance().getCurrentLevel().getLevelType() == Level.LEVEL_COMPLEX) {

			BufferedImage img = left ? ImageLoader.hero_complex_left_1 : ImageLoader.hero_complex_right_1;
			if (walkCyle > 100)
				img = left ? ImageLoader.hero_complex_left_2 : ImageLoader.hero_complex_right_2;
			if (walkCyle > 200)
				img = left ? ImageLoader.hero_complex_left_3 : ImageLoader.hero_complex_right_3;

			g.drawImage(img, pos.x - 16, pos.y - 28);
		}

	}

	public void setDirection(final Vector2d direction) {
		this.direction.set(direction);
	}

	public void setJumpEnable(final boolean isJumpEnable) {
		this.isJumpEnable = isJumpEnable;
	}

	public void setShotEnable(final boolean isShotEnable) {
		this.isShotEnable = isShotEnable;
	}

	/**
	 * 
	 * @return a 4*4 Collisionbox around the Players position
	 */
	public Rectangle getCollisionBox() {
		return collisionBox;
	}
}
