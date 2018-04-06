package poke.fast.entities.characters;

import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.gfx.Assets;
import poke.fast.textboxes.Option;

public class Teacher extends Enemy {	
	
	public Teacher(Handler handler, float x, float y) {
		super(handler, x, y);
		name = "Teacher";
		health = 120;
		fullHealth = 120;
	}
	//The options for the player
	private Option optOne = new Option("Funny Noises", 80, "It was super annoying...");
	private Option optTwo = new Option("Plead", 7, "It was not very effective...");
	private Option optThree = new Option("Witty Comeback", 60, "The whole class started laughing.");

	public void tick() {
		setOptions(new Option[]{optOne, optTwo, optThree}); //Anonymous arrays
	}
	
	public void render(Graphics g) {
		g.drawImage(Assets.player_up[1], (int) ( x - handler.getGameCamera().getxOffset() ), (int) ( y - handler.getGameCamera().getyOffset() ),
				width, height, null);
		
	}
}
