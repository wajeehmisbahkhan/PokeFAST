package poke.fast.entities.inanimates;

import java.awt.Graphics;
import java.awt.Rectangle;

import poke.fast.Handler;
import poke.fast.gfx.Assets;
import poke.fast.tiles.Tile;

public class Bench extends Inanimate{

	private String direction;
	
	public Bench(Handler handler, float x, float y, String direction) {
		super(handler, x, y, Tile.TILEWIDTH*2, Tile.TILEHEIGHT);
		this.direction=direction;
		if(direction=="right")
		bounds = new Rectangle(12,8,36,96);
		else if (direction=="left")
			bounds = new Rectangle(12,0,36,84);
		else if (direction=="front")
			bounds = new Rectangle(0,-8,90,48);
		else if (direction=="back")
			bounds = new Rectangle(0,-8,90,48);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		if(handler.getMap().getCurrentMap()==0)
		if(direction=="front")
			g.drawImage( Assets.bench[0], (int) ( x - handler.getGameCamera().getxOffset() ), (int) (y - handler.getGameCamera().getyOffset()), (int)(0.7*width), (int)(0.7*height), null);
		else if(direction=="back")
			g.drawImage( Assets.bench[1], (int) ( x - handler.getGameCamera().getxOffset() ), (int) (y - handler.getGameCamera().getyOffset()), (int)(0.7*width), (int)(0.7*height), null);
		else if(direction=="left")
			g.drawImage( Assets.bench[2], (int) ( x - handler.getGameCamera().getxOffset() ), (int) (y - handler.getGameCamera().getyOffset()), (int)(0.35*width), (int)(1.4*height), null);
		else if(direction=="right")
			g.drawImage( Assets.bench[3], (int) ( x - handler.getGameCamera().getxOffset() ), (int) (y - handler.getGameCamera().getyOffset()), (int)(0.7*width), (int)(0.7*height), null);
	}

}
