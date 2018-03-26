package poke.fast.entities.characters;

import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.gfx.Assets;

public class Teacher extends Enemy{

	
	
	public Teacher(Handler handler, float x, float y) {
		super(handler, x, y);
		// TODO Auto-generated constructor stub
	}

	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(Assets.player_up[1], (int) ( x - handler.getGameCamera().getxOffset() ), (int) ( y - handler.getGameCamera().getyOffset() ),
				width, height, null);
		
	}
}
