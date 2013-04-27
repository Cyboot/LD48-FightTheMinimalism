package de.timweb.ld48.minimalism.util;

import java.text.DecimalFormat;

public class Vector2d {
	public double	x, y;

	public Vector2d() {
		this(0, 0);
	}

	public Vector2d(final double x, final double y) {
		this.x = x;
		this.y = y;
	}

	public int x() {
		return (int) x;
	}

	public int y() {
		return (int) y;
	}

	public Vector2d add(final double x, final double y) {
		this.x += x;
		this.y += y;
		return this;
	}

	public Vector2d copy() {
		return new Vector2d(x, y);
	}

	public void set(final Vector2d position) {
		x = position.x;
		y = position.y;
	}

	public void set(final double x, final double y) {
		this.x = x;
		this.y = y;
	}

	public double distance(final Vector2d other) {
		double dx = Math.abs(x - other.x);
		double dy = Math.abs(y - other.y);

		return Math.sqrt(dx * dx + dy * dy);
	}

	public double distance(final double x, final double y) {
		double dx = Math.abs(this.x - x);
		double dy = Math.abs(this.y - y);

		return Math.sqrt(dx * dx + dy * dy);
	}

	public Vector2d normalize() {
		return setLength(1);
	}

	public Vector2d flipX() {
		x = -x;
		return this;
	}

	public Vector2d flipY() {
		y = -y;
		return this;
	}

	public Vector2d setLength(final double dist) {
		double length = length();

		x = dist * x / length;
		y = dist * y / length;

		return this;

	}

	public double length() {
		return Math.sqrt(x * x + y * y);
	}

	public static Vector2d randomNormalized() {
		double x = Math.random() * 2 - 1;
		double y = Math.random() * 2 - 1;

		return new Vector2d(x, y).normalize();
	}

	private static DecimalFormat	df	= new DecimalFormat("#0.00");

	@Override
	public String toString() {
		return df.format(x) + " : " + df.format(y);
	}
}
