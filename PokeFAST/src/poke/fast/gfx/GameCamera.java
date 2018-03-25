package poke.fast.gfx;

import poke.fast.Handler;
import poke.fast.entities.Entity;
import poke.fast.tiles.Tile;

public class GameCamera {
	
	private float xOffset, yOffset; //The distance between the camera and the 0, 0 of the game
	private Handler handler;
	
	public GameCamera (Handler handler) {
		this.handler = handler;
		xOffset = 0;
		yOffset = 0;
	}
	
	//When the player moves, it will change the position of the camera
	public void move (float xAmt, float yAmt) {
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlankSpace();
	}
	
	//Checks if the camera has reached the bounds of the game
	public void checkBlankSpace () {
		if (xOffset < 0) //The left side
			xOffset = 0;
		else if (xOffset > handler.getMap().getWidth() * Tile.TILEWIDTH - handler.getWidth()) //The right side
			xOffset = handler.getMap().getWidth() * Tile.TILEWIDTH - handler.getWidth();
		if (yOffset < 0) //The top
			yOffset = 0;
		else if (yOffset > handler.getMap().getHeight() * Tile.TILEHEIGHT - handler.getHeight()) //The bottom
				yOffset = handler.getMap().getHeight() * Tile.TILEHEIGHT - handler.getHeight();	
	}
	
	//Adjusts offsets to center entity, in most cases the player
	public void centerOnEntity (Entity e) {
		xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
		yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
		checkBlankSpace();
	}
	
	//GETTERS & SETTERS
	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}
}
