package poke.fast.entities.characters;

import poke.fast.Handler;
import poke.fast.entities.Entity;
import poke.fast.tiles.Tile;

public abstract class Character extends Entity {
	
	public static final float DEFAULT_GPA = 4.0f ;
	public static final int DEFAULT_WIDTH = 64,
							DEFAULT_HEIGHT = 64;
	public static final float DEFAULT_SPEED = 0.7f;
	
	protected float gpa;
	protected float speed, xMove, yMove;

	public Character(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		gpa = DEFAULT_GPA;
		speed = DEFAULT_SPEED;
		xMove = 0; yMove = 0;
	}

	public void move() {
		moveX();
		moveY();
	}
	
	public void moveY() {
		
		if( yMove < 0 ) {		//UP
			int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
			if (!collisionWithTile ( (int) (x + bounds.x) / Tile.TILEHEIGHT, ty )
					&& !collisionWithTile ( (int) (x + bounds.x + bounds.width) / Tile.TILEHEIGHT, ty))
				y += yMove;
		}
		else if( yMove > 0 ) {	//DOWN
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
			if (!collisionWithTile ( (int) (x + bounds.x) / Tile.TILEHEIGHT, ty )
					&& !collisionWithTile ( (int) (x + bounds.x + bounds.width) / Tile.TILEHEIGHT, ty))
				y += yMove;
			else
				y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
		}
	}
	
	public void moveX() {
		
		if( xMove > 0 ) {		//RIGHT
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
			if (!collisionWithTile (tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
					&& !collisionWithTile (tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT))
				x += xMove;
			else
				x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
		}
		else if( xMove < 0 ) {	//LEFT
			int tx = (int) (x + xMove + bounds.x) / Tile.TILEHEIGHT;
			if (!collisionWithTile (tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
					&& !collisionWithTile (tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT))
				x += xMove;
		}
		
	}
	
	protected boolean collisionWithTile (int x, int y) {
		return handler.getMap().getTile(x,y).isSolid();
	}

	public float getHealth() {
		return gpa;
	}

	public void setHealth(int health) {
		this.gpa = health;
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
