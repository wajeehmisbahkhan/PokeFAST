package poke.fast.tiles;

import poke.fast.gfx.Assets;

public class ShadowTile extends Tile {
	public ShadowTile (int id) {
		super(Assets.shadow, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
