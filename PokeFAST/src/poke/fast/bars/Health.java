package poke.fast.bars;

import java.awt.Color;
import java.awt.Graphics;

public class Health {
	
	private int healthWidth = -1;
	
	public void render(Graphics g, int x, int y, int currentHealth, int maxHealth) {
		g.setColor(Color.GREEN);
		//Health bar
		g.drawRoundRect(x, y, 100, 15, 5, 5);
		//Filled up
		if (healthWidth < 0 && maxHealth != 0)
			healthWidth = currentHealth/maxHealth * 100;
		g.fillRoundRect(x, y, healthWidth, 15, 5, 5);
		g.setColor(Color.BLACK);
	}

	public void decrease(int health) {
		if (healthWidth > health)
			healthWidth--;
	}

}
