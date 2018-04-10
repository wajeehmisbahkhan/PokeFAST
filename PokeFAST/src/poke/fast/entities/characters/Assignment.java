package poke.fast.entities.characters;

import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.gfx.Assets;
import poke.fast.textboxes.Option;

public class Assignment extends Enemy {

	//The options for the player
	private Option optOne = new Option("Slate", 0, "It didn't work.");
	private Option optTwo = new Option("Email", 30, "The teacher objected... but accepted it later.");
	private Option optThree = new Option("Copy From Friend", 50, "It was super efficient.");
	
	//Attacks for Assignment
	private Option attackOne = new Option("Incomplete Question", 50, "It was too ambiguous to solve.");
	private Option attackTwo = new Option("Facebook", 30, "You became distracted.");
	
	public Assignment(Handler handler, float x, float y) {
		super(handler, x, y);
		health = 70;
		fullHealth = 70;
		name = "Assignment";
	}
	
	public void tick() {
		setOptions(new Option[]{optOne, optTwo, optThree}); //Anonymous arrays
		setAttacks(new Option[]{attackOne, attackTwo}); //The enemy can have infinite attacks
	}

	public void render(Graphics g) {
		g.drawImage(Assets.teacher_up[2], (int) ( x - handler.getGameCamera().getxOffset() ), (int) ( y - handler.getGameCamera().getyOffset() ),
				width, height, null);
	}
	
}
