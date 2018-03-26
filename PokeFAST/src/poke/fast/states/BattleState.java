package poke.fast.states;

import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.entities.characters.Teacher;
import poke.fast.textboxes.DialogueBox;
import poke.fast.textboxes.OptionBox;

public class BattleState extends State {
	
	private DialogueBox dialogueBox;
	private OptionBox optionBox;
	
	public BattleState (Handler handler) {
		super(handler);
		dialogueBox = new DialogueBox();
		optionBox = new OptionBox(handler, new Teacher());
	}


	public void tick() {
		optionBox.tick();
	}


	public void render(Graphics g) {
		//Show student
		
		//Show enemy
		
		//Show hp's
		
		//Show dialogue box
		
		//Update dialogue box
		
		//Show option box
		optionBox.render(g);
		//Update option box
		
	}
}
