package poke.fast.transitions;

import java.awt.Graphics;

import poke.fast.Handler;

public class SwipeIn extends Transition {

	public SwipeIn(Handler handler) {
		super(handler);
		opacity = 100;
		this.speed = 3;
		width = 0;
		x = handler.getWidth();
	}

	@Override
	public void tick() {
		if (width <= handler.getWidth()) {
			width += speed;
			x -= speed;
		} else {
			Transition.played = true;
		}
	}

	@Override
	public void render(Graphics g) {
		//Left coming swipes
		for (int i = 0; i < handler.getHeight()/40; i++)
			g.fillRect(0, i*40, width, 20);
		//Right coming swipes
		for (int i = 0; i < handler.getHeight()/40; i++)
			g.fillRect(x, 20 + (i*40), width, 20);
	}
	
}
