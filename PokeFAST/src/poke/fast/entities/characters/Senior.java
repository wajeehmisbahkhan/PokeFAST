package poke.fast.entities.characters;

import poke.fast.Handler;
import poke.fast.textboxes.Option;

public class Senior extends Enemy {
	
	//The options
	private Option optOne = new Option("Ragging", 5);

<<<<<<< HEAD
	public Senior(Handler handler, float x, float y) {
		super(handler, x, y);
		// TODO Auto-generated constructor stub
=======
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
>>>>>>> branch 'master' of https://github.com/wajeehmisbahkhan/PokeFAST.git
	}

}
