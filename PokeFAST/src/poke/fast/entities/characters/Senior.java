package poke.fast.entities.characters;

import poke.fast.Handler;
import poke.fast.textboxes.Option;

public class Senior extends Enemy {
	
	public Senior(Handler handler, float x, float y/*, int width, int height*/) {
		super(handler, x, y);
		health = 100;
		fullHealth = 100;
		name = "Senior";
	}
	
	//The options for the player
	private Option optOne = new Option("Shikayat", 70, "The senior was charged 5000 rupees... It was super effective.");
	private Option optTwo = new Option("Witty Comeback", 10, "Senior was shocked... But it was not very effective.");
	
	//Attacks for Senior
	private Option attackOne = new Option("Ragging", 60, "It was super embarassing.");
	private Option attackTwo = new Option("Point party", 30, "You were charged 1500 rupees.");
	private Option attackThree = new Option("Gaana gao", 40, "You were forced to sing.");
	
	
	public void tick () {
		setOptions(new Option[]{optOne, optTwo}); //Anonymous arrays
		setAttacks(new Option[]{attackOne, attackTwo, attackThree}); //The enemy can have infinite attacks
	}

}
