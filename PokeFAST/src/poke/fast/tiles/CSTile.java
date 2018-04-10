package poke.fast.tiles;

import poke.fast.gfx.Assets;

public class CSTile extends Tile {
	public CSTile (int id) {
		super(Assets.cs, id);
	}
	
	@Override
	public boolean isSolid () {
		return true;
	}
}
