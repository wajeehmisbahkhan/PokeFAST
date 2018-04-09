package poke.fast.tiles;

import poke.fast.gfx.Assets;

public class ShopTile extends Tile {

	public ShopTile(int id) {
		super(Assets.shop, id);
	}

	@Override
	public boolean isSolid() {
		return true;
	}
}
