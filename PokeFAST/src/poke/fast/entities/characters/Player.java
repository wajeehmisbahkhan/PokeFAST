package poke.fast.entities.characters;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import poke.fast.Handler;
import poke.fast.gfx.Animation;
import poke.fast.gfx.Assets;

public class Player extends Character{

	//animation & direction
	private int direction;
	private Animation upimation, downimation, leftimation, rightimation;
	
	
	public Player( Handler handler, float x, float y) {
		super(handler, x, y, Character.DEFAULT_WIDTH, Character.DEFAULT_HEIGHT);
		
		bounds.x = 16;
		bounds.y = 32;
		bounds.width = 32;
		bounds.height = 32;
		
		//Animations: up, down, left, right
		direction = 0;
		upimation = new Animation ( 1000/20, Assets.player_up);
		downimation = new Animation ( 1000/20, Assets.player_down);
		leftimation = new Animation ( 1000/20, Assets.player_left);
		rightimation = new Animation ( 1000/20, Assets.player_right);
	}

	@Override
	public void tick() {
		downimation.tick();
		upimation.tick();
		rightimation.tick();
		leftimation.tick();
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		System.out.println("player ticking");
	}

	private void getInput() {
		xMove = 0; yMove = 0;
		//will edit this later to get constant velocity
		if ( handler.getKeyManager().up)
			yMove = -speed;
		if ( handler.getKeyManager().down)
			yMove = speed;
		if ( handler.getKeyManager().left)
			xMove = -speed;
		if ( handler.getKeyManager().right)
			xMove = speed;
		
		
	}
	
	
	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) ( x - handler.getGameCamera().getxOffset() ), (int) ( y - handler.getGameCamera().getyOffset() ),
				width, height, null);
		
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		
		if( xMove < 0 ) {
			direction = 1;
			return leftimation.getCurrentFrame();
		}
		else if( xMove > 0 ) {
			direction = 2;
			return rightimation.getCurrentFrame();
		}
		else if( yMove < 0 ) {
			direction = 3;
			return upimation.getCurrentFrame();
		}
		else if( yMove > 0 ) {
			direction = 0;
			return downimation.getCurrentFrame();
		}
		else
			return Assets.player_still[direction];	//nicely done ;)
		
	}
	
}