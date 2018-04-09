package poke.fast.tiles;

import poke.fast.gfx.Assets;

public class ShadesTile extends Tile {

	public ShadesTile(int id) {
		super(Assets.shades, id);
	}

	@Override
	public boolean renderLater () {
		return true;
	}
}
