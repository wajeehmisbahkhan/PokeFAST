package poke.fast.entities.characters;

import poke.fast.Handler;
import poke.fast.entities.Entity;

public abstract class Character extends Entity {
	
	//some gpa, health, speed vars needed
	public static final int DEFAULT_WIDTH = 64,
							DEFAULT_HEIGHT = 64;
	
	protected int health;
	protected float speed, xMove, yMove;

	public Character(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	public void move() {
		moveX();
		moveY();
	}
	
	public void moveX() {
		
	}
	
	public void moveY() {
		
	}
	
	protected boolean collisionWithTile (int x, int y) {
		return false;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}
	
	
	
}
