package poke.fast.entities.inanimates;

import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.gfx.Assets;
import poke.fast.tiles.Tile;

public class Fountain extends Inanimate {
	
	public Fountain(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH*4, Tile.TILEHEIGHT*2);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage( Assets.fountain, (int) ( x - handler.getGameCamera().getxOffset() ), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}
}
