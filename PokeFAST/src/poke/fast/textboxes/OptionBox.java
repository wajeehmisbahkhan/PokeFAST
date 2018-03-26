package poke.fast.textboxes;

import java.awt.Color;
import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.entities.characters.Enemy;
import poke.fast.input.KeyManager;

public class OptionBox {
	
	private int width, height, x, y;
	private Option[] options = new Option[4];
	private int optionPosition[][] = new int[4][2];
	private int selectedOption;
	
	//Temporary for positioning of options
	private int opt;
	
	private Handler handler;
	
	public OptionBox (Handler handler, Enemy e) {
		this.handler = handler;
		options = e.getOptions();
		width = (int) Math.floor(handler.getWidth() * 0.5);
		height = 80;
		x = handler.getWidth() - width;
		y = handler.getHeight() - height;
		
		setOptionPosition();
		
		selectedOption = 1;
	}
	
	public void tick () {
		getInput();
	}
	
	public void render (Graphics g) {
		g.setColor(Color.WHITE);
		//Display box
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		//Display Options
		displayOptions(g);
		//Display selection
		g.drawString(">", optionPosition[selectedOption-1][0] - 10, optionPosition[selectedOption-1][1]);
	}
	
	public void setOptionPosition () {
		//X Position
		for (int i = 0; i < 4; i++) {
			if (i % 2 == 0)
				optionPosition[i][0] = x + 15;
			else
				optionPosition[i][0] = (x + 15) + x / 2;
		}
		
		//Y Position
		for (int i = 0; i < 4; i++) {
			if (i < 2)
				optionPosition[i][1] = y + 25;
			else
				optionPosition[i][1] = y + 60;
		}
		
	}
	
	public void displayOptions (Graphics g) {
		opt = 0;

		for (Option o: options) {
			if (o == null)
				g.drawString("-", optionPosition[opt][0], optionPosition[opt][1]);
			else
				g.drawString(o.getText(), optionPosition[opt][0], optionPosition[opt][1]);
			opt++;
		}
	}

	public void getInput () {
		//Moves one 
		if (handler.getKeyManager().left)
			selectedOption--;
		if (handler.getKeyManager().up || handler.getKeyManager().down)
			selectedOption += 2;
		if (handler.getKeyManager().right)
			selectedOption++;
		//Press left when left top is selected
		if (selectedOption < 1)
			selectedOption += 4;
		//Press right when right bottom is selected
		if (selectedOption > 4)
			selectedOption %= 4;
	}
	
	
}
