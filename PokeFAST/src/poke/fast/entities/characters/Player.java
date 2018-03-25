package poke.fast.entities.characters;

import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.gfx.Assets;

public class Player extends Character{

	//animation & direction
	
	
	
	public Player( Handler handler, float x, float y) {
		super(handler, x, y, Character.DEFAULT_WIDTH, Character.DEFAULT_HEIGHT);
		
		bounds.x = 16;
		bounds.y = 32;
		bounds.width = 32;
		bounds.height = 32;
		
		//Animations: up, down, left, right
		
		
	}

	@Override
	public void tick() {
		//tick all 4 anims
		
		
		
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
	}

	private void getInput() {
		xMove = 0; yMove = 0;
		
		//set xMove, yMove; KeyManager needed
	}
	
	
	@Override
	public void render(Graphics g) {
		//assets.player should be replaced with current animation
		g.drawImage(Assets.player, (int) ( x - handler.getGameCamera().getxOffset() ), (int) ( y - handler.getGameCamera().getyOffset() ),
				width, height, null);
		
	}
	
}
