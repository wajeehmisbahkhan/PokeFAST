package poke.fast.entities.characters;

import poke.fast.Handler;
import poke.fast.textboxes.Option;

public class Teacher extends Enemy {

	//The options
	private Option optOne = new Option("Surprise Quiz", 32);
	private Option optTwo = new Option("Nagging", 7);

	private Option[] options;

	public Teacher(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
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

}
