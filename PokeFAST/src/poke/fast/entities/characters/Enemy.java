package poke.fast.entities.characters;

import java.awt.Graphics;
import java.awt.Rectangle;

import poke.fast.Handler;
import poke.fast.gfx.Animation;
import poke.fast.gfx.Assets;
import poke.fast.textboxes.Option;

public abstract class Enemy extends Character{

	protected int direction;
	protected Animation upimation, downimation, leftimation, rightimation;
	
	//The health
	protected int health;
	protected int fullHealth;
	protected boolean shouldRender;
	
	
	protected Option[] options = new Option[4];
	protected Option[] attacks = new Option[4];
	
	public Enemy(Handler handler, float x, float y) {
		super(handler, x, y,Character.DEFAULT_WIDTH, Character.DEFAULT_HEIGHT);
		bounds.x = 16;
		bounds.y = 32;
		bounds.width = 32;
		bounds.height = Character.DEFAULT_HEIGHT - bounds.y;
		shouldRender=false;
		direction=0;
		
	}

	@Override
	public void tick() {
		
		downimation.tick();
		upimation.tick();
		rightimation.tick();
		leftimation.tick();
		getInput();
		move();
		
	}

	@Override
	public void render(Graphics g) {
	}

	//Enemies will return options
	public Option[] getOptions() {
		return options;
	}
	protected void setOptions(Option[] options) { //Only for enemy children
		this.options = options;
	}
	public Option[] getAttacks() {
		return attacks;
	}
	public Option getRandomAttack() {
		int randomNumber = (int) (Math.random() * attacks.length); // (random() * (max-min) + 1) + min
		return attacks[randomNumber];
	}
	protected void setAttacks(Option[] attacks) { //Only for enemy children
		this.attacks = attacks;
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

	//renderFlag
	public boolean getShouldRender() {
		return shouldRender;
	}

	public void setShouldRender(boolean shouldRender) {
		this.shouldRender = shouldRender;
	}
	
	
	public void getInput() {
		
		
		float xDest = handler.getMap().entityManager.getPlayer().getX(); 
		float yDest = handler.getMap().entityManager.getPlayer().getY(); 
		if(yDest<y) {	//UP
				yMove = -speed;
				System.out.println("TRIED TO MOVE UP");
		}	
		if(yDest>y) {	//DOWN
				yMove = speed;
				System.out.println("TRIED TO MOVE DOWN");
		}	
		if(xDest>x) {	//RIGHT
				xMove = speed;
				System.out.println("TRIED TO MOVE RIGHT");
		}	
		if(xDest<x) {	//LEFT
				xMove = -speed;
				System.out.println("TRIED TO MOVE LEFT");
		}	
	}
	
	
}
