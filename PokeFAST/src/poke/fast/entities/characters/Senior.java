package poke.fast.entities.characters;

import poke.fast.Handler;
import poke.fast.textboxes.Option;

public class Senior extends Enemy {
	
	//The options
	private Option optOne = new Option("Ragging", 5);

	public Senior(Handler handler, float x, float y) {
		super(handler, x, y);
	}
	private Option[] options = {optOne};
	
	public Senior(Handler handler, float x, float y, int width, int height) {
		super();
	}
	
	//For BattleState
	public Senior () {
		
	}
	
	@Override
	public Option[] getOptions() {
		return options;
	}

}
