package poke.fast.entities.characters;

import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.textboxes.Option;

public abstract class Enemy extends Character{

	//The health
	protected int health;
	protected int fullHealth;
	
	protected Option[] options = new Option[4];
	
	
	public Enemy(Handler handler, float x, float y) {
		super(handler, x, y,Character.DEFAULT_WIDTH, Character.DEFAULT_HEIGHT);
		bounds.x = 16;
		bounds.y = 32;
		bounds.width = 32;
		bounds.height = 32;
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
	public Option[] getOptions() {
		return options;
	}
	protected void setOptions(Option[] options) { //Only for enemy children
		this.options = options;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getFullHealth() {
		return fullHealth;
	}
}
