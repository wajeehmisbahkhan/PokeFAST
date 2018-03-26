package poke.fast.entities.characters;

import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.textboxes.Option;

public abstract class Enemy extends Character{

	
	
	
	public Enemy(Handler handler, float x, float y) {
		super(handler, x, y,Character.DEFAULT_WIDTH, Character.DEFAULT_HEIGHT);
		
		bounds.x = 16;
		bounds.y = 32;
		bounds.width = 32;
		bounds.height = 32;
	}
	
	//For BattleState
	public Enemy () {
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	//Enemies will return options
	public abstract Option[] getOptions();

}
