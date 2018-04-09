package poke.fast.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import poke.fast.Handler;

public abstract class Entity {

	protected Handler handler;
	protected float x,y;		//position coords
	protected int width, height;//entity dimensions
	protected Rectangle bounds, fov;
	
	
	public Entity(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this. y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle(16,24,width/2,height/2);	//default bounds, to be over-ridden
		fov = new Rectangle(0,0,width,height);
	}
	
	//For BattleState
	public Entity () {
		
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	};

	public boolean checkEntityCollision( float xOffset, float yOffset) {
		for(Entity e: handler.getMap().getEntityManager().getEntities()) {
			if (e.equals(this))
				continue;
			if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset,yOffset)))
					return true;
		}
		return false;
	}
	public Rectangle getCollisionBounds( float xOffset, float yOffset) {
		return new Rectangle( (int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height );
	}
	
	public boolean checkEntityEncounter( float xOffset, float yOffset) {
		for(Entity e: handler.getMap().getEntityManager().getEntities()) {
			if(e.equals(this))
				continue;
			if(e.getEncounterBounds(0f,0f).intersects(getEncounterBounds(xOffset,yOffset)))
				return true;
		}
		return false;
	}
	
	public Rectangle getEncounterBounds(float xOffset, float yOffset) {
		return new Rectangle( (int) (x + fov.x + xOffset), (int) (y + fov.y + yOffset), fov.width, fov.height );
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
