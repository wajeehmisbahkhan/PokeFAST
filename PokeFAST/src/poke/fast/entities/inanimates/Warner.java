package poke.fast.entities.inanimates;

import java.awt.Graphics;
import java.awt.Rectangle;

import poke.fast.Handler;
import poke.fast.gfx.Assets;
import poke.fast.tiles.Tile;

public class Warner extends Inanimate {
	public Warner(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		bounds = new Rectangle(0, 0, 64, 64);
		interaction = new Rectangle(0, 0, 64, 64);
		message = "Freshie lag rahe ho. Dhabe se door rahna agar seniors ki ragging se door rahna chahte ho to.";
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		g.drawImage( Assets.young, (int) ( x - handler.getGameCamera().getxOffset() ), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}
}
