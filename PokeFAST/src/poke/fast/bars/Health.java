package poke.fast.bars;

import java.awt.Color;
import java.awt.Graphics;

public class Health {
	
	private int healthWidth = -1;
	private int maxHealth;
	
	public void render(Graphics g, int x, int y, int currentHealth, int maxHealth) {
		this.maxHealth = maxHealth;
		//Health bar
		g.drawRoundRect(x-1, y-1, 100+1, 15+1, 5, 5);
		if (currentHealth > maxHealth/2)
			g.setColor(Color.GREEN);
		else if (currentHealth > maxHealth / 5)
			g.setColor(Color.YELLOW);
		else
			g.setColor(Color.RED);
		//Filled up
		if (healthWidth < 0 && maxHealth != 0)
			healthWidth = (int) ((float)currentHealth/maxHealth * 100);
		g.fillRoundRect(x, y, healthWidth, 15, 5, 5);
		g.setColor(Color.BLACK);
	}

	public void decrease(int health) {
		if (healthWidth > health*100/maxHealth)
			healthWidth--;
	}
	
	public int getHealthWidth () {
		return healthWidth;
	}

}
