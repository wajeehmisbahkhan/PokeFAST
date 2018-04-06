package poke.fast.entities.characters;

import poke.fast.Handler;
import poke.fast.textboxes.Option;

public class Senior extends Enemy {
	
	//The options for the player
	private Option optOne = new Option("Snitch", 150, "The senior was charged 5000 rupees... It was super effective.");
	
	public Senior(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y);
		options[0] = optOne;
		health = 100;
		fullHealth = 100;
		name = "Senior";
	}

}
