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
	private Option optOne = new Option("Funny Noises", 80, "It was super annoying."); //The teacher said 'main superman nahi hoon'
	private Option optTwo = new Option("Plead", 10, "You begged the teacher for marks... It was not very effective.");
	private Option optThree = new Option("Witty Comeback", 50, "The whole class started laughing.");
	private Option optFour = new Option("Ignore", 20, "The teacher's anger increased.");
	//Attacks for teacher
	private Option attackOne = new Option("Lecture", 20, "You endured it.");
	private Option attackTwo = new Option("Surprize Quiz", 100, "It was unexpectedly hard.");
	private Option attackThree = new Option("Impossible to do assignment", 5, "You ignored it.");
	public void tick() {
		setOptions(new Option[]{optOne, optTwo, optThree, optFour}); //Anonymous arrays
		setAttacks(new Option[]{attackOne, attackTwo, attackThree}); //The enemy can have infinite attacks
	}
	
	public void render(Graphics g) {
		g.drawImage(Assets.teacher_down[2], (int) ( x - handler.getGameCamera().getxOffset() ), (int) ( y - handler.getGameCamera().getyOffset() ),
				width, height, null);
	}
}
