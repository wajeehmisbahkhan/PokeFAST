package poke.fast.entities.inanimates;

import java.awt.Graphics;
import java.awt.Rectangle;

import poke.fast.Handler;
import poke.fast.gfx.Assets;
import poke.fast.tiles.Tile;

public class Fountain extends Inanimate {
	
	public Fountain(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH*4, Tile.TILEHEIGHT*2);
		bounds = new Rectangle(32,16,196,96);
		interaction = new Rectangle(-20, -50, 196+(20*2), 96+(50));
		message = "According to an old legend, this fountain runs once every year on orientation day...";
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		g.drawImage( Assets.fountain, (int) ( x - handler.getGameCamera().getxOffset() ), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}
}
