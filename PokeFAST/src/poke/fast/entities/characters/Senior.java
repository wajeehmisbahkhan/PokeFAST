package poke.fast.entities.characters;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import poke.fast.Handler;
import poke.fast.gfx.Animation;
import poke.fast.gfx.Assets;
import poke.fast.textboxes.DialogueManager;
import poke.fast.textboxes.Option;

public class Senior extends Enemy {
	
	public Senior(Handler handler, float x, float y/*, int width, int height*/) {
		super(handler, x, y);
		health = 100;
		fullHealth = 100;
		name = "Senior";
		
		//Animations: up, down, left, right
				upimation = new Animation ( 1000/20, Assets.senior_up);
				downimation = new Animation ( 1000/20, Assets.senior_down);
				leftimation = new Animation ( 1000/20, Assets.senior_left);
				rightimation = new Animation ( 1000/20, Assets.senior_right);
		
	}
	
	//The options for the player
	private Option optOne = new Option("Shikayat", 70, "The senior was charged 5000 rupees... It was super effective.");
	private Option optTwo = new Option("Witty Comeback", 10, "Senior was shocked... But it was not very effective.");
	
	//Attacks for Senior
	private Option attackOne = new Option("Ragging", 30, "It was super embarassing.");
	private Option attackTwo = new Option("Point party", 20, "You were charged 1500 rupees.");
	private Option attackThree = new Option("Gaana gao", 10, "You were forced to sing.");
	
	
	public void tick () {
		getInput();
		setOptions(new Option[]{optOne, optTwo}); //Anonymous arrays
		setAttacks(new Option[]{attackOne, attackTwo, attackThree}); //The enemy can have infinite attacks
	}
	
	public void render(Graphics g) {
		if(shouldRender)
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
			return Assets.senior_still[direction];	//nicely done ;)
		
	}
	
	public void startRagging(Graphics g,DialogueManager dialogueManager) {
		
	}
}
