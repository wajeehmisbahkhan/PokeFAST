package poke.fast.entities.characters;

import poke.fast.Handler;
import poke.fast.textboxes.Option;

public class Senior extends Enemy {
	
	//The options
	private Option optOne = new Option("Ragging", 5);

	private Option[] options = {optOne};
	
	public Senior(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}
	
	//For BattleState
	public Senior () {
		
	}
	
	@Override
	public Option[] getOptions() {
		return options;
	}

}
