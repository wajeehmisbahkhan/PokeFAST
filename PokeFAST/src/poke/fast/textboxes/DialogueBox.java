package poke.fast.textboxes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import poke.fast.Handler;
import poke.fast.gfx.Assets;
import poke.fast.utils.Text;

public class DialogueBox {
	//Styling
	private int width, height, x, y;
	private int margin;
	//Message tokens
	private ArrayList <String> message;
	private int current;
	private int end;
	private boolean running;

	
	private Handler handler;
	
	public DialogueBox (Handler handler) {
		this.handler = handler;
		width = handler.getWidth() - 1;
		height = 100;
		x = 0;
		y = handler.getHeight() - height - 1;
		margin = 10;
		message = new ArrayList <String>();
		running = false;
	}


	public void tick () {
		if (running)
			getInput();
	}
	
	public void render(Graphics g) {
		//Display box
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
	}
	
	public void say (Graphics g, String message) {
		this.message = Text.getTokenedMessage(message); //This will split up the message using the \n's
		//Will need to disableEverythingElse()
		current = 0;
		end = this.message.size();
		for (int i = current; i < end; i++)
			Text.drawString(g, this.message.get(i), x+margin, y + Assets.dialogueFont.getSize() + margin, false, Color.BLACK, Assets.dialogueFont);
	}
	
	public void getInput () {
		/*if (handler.getKeyManager().space && running) {	
			current++;
			Text.drawString(g, this.message.get(current), x+margin, y + Assets.dialogueFont.getSize() + margin, false, Color.BLACK, Assets.dialogueFont);
			if (current > end)
				running = false;
			System.out.println(current);
		}*/
	}

	//GETTERS & SETTERS
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


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}
	
}
