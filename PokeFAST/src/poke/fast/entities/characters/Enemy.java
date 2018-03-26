package poke.fast.entities.characters;

import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.textboxes.Option;

public abstract class Enemy extends Character{

	public Enemy(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		// TODO Auto-generated constructor stub
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
