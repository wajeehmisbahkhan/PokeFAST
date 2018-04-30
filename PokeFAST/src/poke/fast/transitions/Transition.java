package poke.fast.transitions;

import java.awt.Graphics;

import poke.fast.Handler;

public abstract class Transition {
	
	public static boolean playing = false; //Might be useless
	public static boolean played = false; //When animation is done, it will become true; Change it back to false
	protected Handler handler;
	
	//Variable stuff for animations
	protected int x;
	protected int width;
	protected int opacity;
	protected int speed; //Of animation
	
	public Transition (Handler handler) {
		this.handler = handler;
	}
	
	
	abstract public void tick();
	abstract public void render(Graphics g);
	
	public void setSpeed (int speed) {
		this.speed = speed;
	}
	
	public int getSpeed () {
		return speed;
	}
	
}
