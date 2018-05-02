package poke.fast.entities.inanimates;

import java.awt.Graphics;
import java.awt.Rectangle;

import poke.fast.Handler;
import poke.fast.gfx.Assets;
import poke.fast.tiles.Tile;

public class MolviSahab extends Inanimate {
	public MolviSahab(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		bounds = new Rectangle(0, 0, 64, 64);
		interaction = new Rectangle(0, 0, 64, 64);
		message = "Aik or khali Masjid, mahnat ki zaroorat hai.";
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		g.drawImage( Assets.old, (int) ( x - handler.getGameCamera().getxOffset() ), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}
}
