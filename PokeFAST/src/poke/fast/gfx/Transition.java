package poke.fast.gfx;

import java.awt.Color;
import java.awt.Graphics;

import poke.fast.Handler;

public class Transition {
	
	public static boolean starting = true; //Totally useless
	public static boolean playing = false; //Might be useless
	public static boolean played = false; //When animation is done, it will become true; Change it back to false
	private Handler handler;
	
	//Variable stuff for animations
	private int x;
	private int width;
	private float opacity;
	
	private int speed; //Of animation
	
	public Transition (Handler handler) {
		this.handler = handler;
		
	}
	
	public void fadeIn (Graphics g, int speed) {
		if (starting) {
			speed = 2;
			opacity = 100;
			starting = false;
			playing = true;
		}
		if (playing) {
			g.setColor(new Color (0, 0, 0, opacity/100));
			g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
			if (opacity > 0)
				opacity -= speed;
			else {
				starting = true;
				playing = false;
				played = true;
			}
		}
	}
	
	public void fadeOut (Graphics g, int speed) {
		if (starting) {
			opacity = 0;
			starting = false;
			playing = true;
		}
		if (playing) {
			g.setColor(new Color (0, 0, 0, opacity/100));
			g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
			if (opacity < 100)
				opacity += speed;
			else {
				starting = true;
				playing = false;
				played = true;
			}
		}
	}
	
	public void swipeIn (Graphics g) {
		//Setup
		if (starting) {
			speed = 3;
			width = 0;
			x = 400;
			starting = false;
			playing = true;
		}
		//Animation
		if (playing) {
			//Left coming swipes
			for (int i = 0; i < 10; i++) {
				g.fillRect(0, i*40, width, 20);
				//Pokeballs
				//g.drawImage(Assets.pokeBag, width, i*40, 25, 25, null);
			}
			//Right coming swipes
			for (int i = 0; i < 10; i++) {
				g.fillRect(x, 20 + (i*40), width, 20);
				//g.drawImage(Assets.pokeBag, x-20, 20+(i*40), 25, 25, null);
			}
			if (width <= 400) {
				width += speed;
				x -= speed;
			}
			else {
				starting = true;
				playing = false;
				played = true;
			}
		}
	}
	
}
