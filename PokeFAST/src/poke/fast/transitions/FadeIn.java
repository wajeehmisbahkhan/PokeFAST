package poke.fast.transitions;

import java.awt.Color;
import java.awt.Graphics;

import poke.fast.Handler;

public class FadeIn extends Transition {
	public FadeIn(Handler handler) {
		super(handler);
		this.opacity = 100;
		this.speed = 1;
	}

	@Override
	public void tick() {
		if (opacity-speed > 100)
			opacity -= speed;
		else {
			opacity = 0;
			Transition.played = true;
		}

	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color (0, 0, 0, opacity/100));
		g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
	}
}
