package de.timweb.ld48.minimalism.world;

public enum Tile {
	AIR(false), GROUND(true);

	private boolean	solid;

	private Tile(final boolean solid) {
		this.solid = solid;
	}

	public boolean isSolid() {
		return solid;
	}
}
