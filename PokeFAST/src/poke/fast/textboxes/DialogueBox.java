package poke.fast.textboxes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import poke.fast.Handler;
import poke.fast.gfx.Assets;
import poke.fast.states.BattleState;
import poke.fast.states.GameState;
import poke.fast.states.State;
import poke.fast.utils.Text;

public class DialogueBox {
	//Styling
	private int width, height, x, y;
	private int margin;
	//Message tokens
	private ArrayList <String> messages;
	private String message = "";
	private int letters = 0;
	private int page = 0;
	
	//Static isSaying
	public static boolean isSaying = false;
	
	private Handler handler;
	
	public DialogueBox (Handler handler) {
		this.handler = handler;
		width = handler.getWidth() - 1;
		height = 100;
		x = 0;
		y = this.handler.getHeight() - height - 1;
		margin = 10;
		messages = new ArrayList <String>();
	}


	public void tick () {
		//Keep box open during battle
		if (State.getState() instanceof BattleState)
			isSaying = true;
		else {
			//During other states
			if (page < this.messages.size()) {
				isSaying = true;
				//Showing one at a time
				if (letters <= this.message.length())
					letters++;
				//If space is pressed
				if (handler.getKeyManager().spacePressed) {
					if (letters < this.message.length())
						letters = this.message.length();
					else {
						letters = 0;
						page++;
					}
				}
			}
			if (page == this.messages.size() && this.messages.size() != 0) {
				isSaying = false;
				letters = 0;
				page = 0;
			}
		}
	}
	
	public void render(Graphics g) {
		if (isSaying) {
			//Display box
			g.setColor(Color.WHITE);
			g.fillRect(x, y, width, height);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, height);
		}
	}
	
	public void say (Graphics g, String message) {
		messages = Text.getTokenedMessage(message); //This will split up the message using the \n's
		if (State.getState() instanceof BattleState) {
			Text.drawString(g, this.messages.get(0), x+margin, y + Assets.dialogueFont.getSize() + margin, false, Color.BLACK, Assets.dialogueFont);
		} else {
			this.message = this.messages.get(page);
			for (int i = 0; i < letters; i++)
				Text.drawString(g, this.message.substring(0, i), x+margin, y + Assets.dialogueFont.getSize() + margin, false, Color.BLACK, Assets.dialogueFont);
		}
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
