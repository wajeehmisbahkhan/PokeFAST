package poke.fast.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

//The User Interface Objects
public abstract class UIObject {
	//Properties for every object
	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	protected boolean hovering = false;
	
	public UIObject(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle((int) x, (int) y, width, height);
	}
	
	//When mouse moves
	public void onMouseMove (MouseEvent e) {
		//Check if hovering
		if (bounds.contains(e.getX(), e.getY())) //If the bounds of the object contains the x and y of the mouse
			hovering = true;
		else
			hovering = false;
	}
	
	//When mouse is released
	public void onMouseRelease (MouseEvent e) {
		if (hovering && e.getButton() == MouseEvent.BUTTON1) //If the mouse is hovering and the left button is released
			onClick(); //Perform the action
	}
	
	//The action performed when clicked depends on the object
	public abstract void onClick();
	
	//The tick and render for the children
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	//GETTERS & SETTERS
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
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public boolean isHovering() {
		return hovering;
	}

	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}
	
}
