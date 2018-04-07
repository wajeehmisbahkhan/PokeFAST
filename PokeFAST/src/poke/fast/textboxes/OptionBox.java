package poke.fast.textboxes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import poke.fast.Handler;
import poke.fast.entities.characters.Enemy;
import poke.fast.gfx.Assets;
import poke.fast.input.KeyManager;
import poke.fast.utils.Text;

public class OptionBox {
	private boolean flag;
	private int width, height, x, y;
	private Option[] options = new Option[4];
	private int optionPosition[][] = new int[4][2];
	private int validOptions;
	private int selectedOption;
	
	//Temporary for positioning of options
	private int opt;
	
	private Handler handler;
	
	public OptionBox (Handler handler, Enemy e) {
		this.handler = handler;
		options = e.getOptions();
		width = (int) Math.floor(handler.getWidth() * 0.52);
		height = 80;
		x = handler.getWidth() - width - 5;
		y = handler.getHeight() - height - 10;
		
		selectedOption = 1;
		
		setOptionPosition();
		
		flag = false;
	}
	
	public void tick () {
		getInput();
	}
	
	public void render (Graphics g) {
		//Display box
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		//Display Options
		displayOptions(g);
	}
	
	public void setOptionPosition () {
		//X Position
		for (int i = 0; i < 4; i++) {
			if (i % 2 == 0)
				optionPosition[i][0] = x + 65;
			else
				optionPosition[i][0] = (x + 65) + (x + 10) / 2;
		}
		
		//Y Position
		for (int i = 0; i < 4; i++) {
			if (i < 2)
				optionPosition[i][1] = y + 10;
			else
				optionPosition[i][1] = y + 40;
		}
		
	}
	
	public void displayOptions (Graphics g) {
		opt = 0;
		validOptions = 0;
		for (Option o: options) {
			if (o.getText() == "")
				Text.drawString(g, "-", optionPosition[opt][0], optionPosition[opt][1], true, Color.BLACK, Assets.optionFont);
			else {
				if (opt == selectedOption - 1)
					Text.drawString(g, ">" + o.getText(), optionPosition[opt][0], optionPosition[opt][1], true, Color.BLACK, Assets.optionFont);
				else
					Text.drawString(g, " " + o.getText(), optionPosition[opt][0], optionPosition[opt][1], true, Color.BLACK, Assets.optionFont);
				validOptions++;
			}
			opt++;
		}
	}

	public void getInput () {
		if(!handler.getKeyManager().left && !handler.getKeyManager().right && !handler.getKeyManager().up && !handler.getKeyManager().down) {
			flag = false;
		}
		//Moves one
		if (handler.getKeyManager().left  && !flag)
			{selectedOption--; flag = true;}
		if( (handler.getKeyManager().up || handler.getKeyManager().down)   && !flag )
			{selectedOption += 2; flag = true;}
		if (handler.getKeyManager().right  && !flag)
			{selectedOption++; flag = true;}
		//Press left when left top is selected
		if (selectedOption < 1)
			selectedOption += validOptions; 
		//Press right when right bottom is selected
		if (selectedOption > validOptions && validOptions != 0)
			selectedOption %= validOptions;
	
	}
	
	public Option getSelectedOption () {
		return options[selectedOption-1];
	}
	
}
