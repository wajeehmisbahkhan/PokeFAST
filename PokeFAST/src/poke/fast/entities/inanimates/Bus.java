package poke.fast.entities.inanimates;

import java.awt.Graphics;
import java.awt.Rectangle;

import poke.fast.Handler;
import poke.fast.gfx.Assets;
import poke.fast.tiles.Tile;

public class Bus extends Inanimate {
	public Bus(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH*2, Tile.TILEHEIGHT);
		bounds = new Rectangle(0, 0, 256, 96);
		interaction = new Rectangle(-20, -50, 256+(20), 96+(20));
		message = "Ah yes, the silver point. This is a symbol of the Karachi NUCES campus... maine suna hai ke ye kabhi bhi nahi chalti.";
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {

		g.drawImage( Assets.bus, (int) ( x - handler.getGameCamera().getxOffset() ), (int) (y - handler.getGameCamera().getyOffset()), null);
		
	}
	
}
