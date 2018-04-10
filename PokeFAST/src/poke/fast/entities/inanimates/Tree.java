package poke.fast.entities.inanimates;

import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.gfx.Assets;
import poke.fast.tiles.Tile;

public class Tree extends Inanimate{

	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {

		g.drawImage( Assets.tree, (int) ( x - handler.getGameCamera().getxOffset() ), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		g.drawImage( Assets.tree, (int) ( x - handler.getGameCamera().getxOffset() ),(int) ( y - handler.getGameCamera().getyOffset() ), width, height, null);
	}

}
