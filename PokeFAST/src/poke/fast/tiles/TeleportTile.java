package poke.fast.tiles;

import poke.fast.gfx.Assets;


public class TeleportTile extends Tile {

	public TeleportTile(int id) {
		super(Assets.teleport, id);
	}

	public boolean isPortal() {
		return true;
	}
}
