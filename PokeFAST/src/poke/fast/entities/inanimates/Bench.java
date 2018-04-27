package poke.fast.entities.inanimates;

import java.awt.Graphics;
import java.awt.Rectangle;

import poke.fast.Handler;
import poke.fast.gfx.Assets;
import poke.fast.tiles.Tile;

public class Bench extends Inanimate{

	public Bench(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH*2, Tile.TILEHEIGHT);
		bounds = new Rectangle(8,0,96,72);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {

		g.drawImage( Assets.bench, (int) ( x - handler.getGameCamera().getxOffset() ), (int) (y - handler.getGameCamera().getyOffset()), (int)(0.8*width), (int)(0.8*height), null);
		
	}

}
