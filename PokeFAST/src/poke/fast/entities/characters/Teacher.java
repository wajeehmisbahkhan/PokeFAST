package poke.fast.entities.characters;

import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.gfx.Assets;
import poke.fast.textboxes.Option;

public class Teacher extends Enemy {	
	
	public Teacher(Handler handler, float x, float y) {
		super(handler, x, y);
	}
	//The options
	private Option optOne = new Option("Surprise Quiz", 32);
	private Option optTwo = new Option("Nagging", 7);

	private Option[] options;
	
	public Teacher(Handler handler, float x, float y, int width, int height) {
		super();
	}
	
	//For BattleState
	public Teacher() {
		options = new Option[4];
		options[0] = optOne;
		options[1] = optTwo;
	}

	@Override
	public Option[] getOptions() {
		return options;
		}

	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(Assets.player_up[1], (int) ( x - handler.getGameCamera().getxOffset() ), (int) ( y - handler.getGameCamera().getyOffset() ),
				width, height, null);
		
	}
}
