package poke.fast.textboxes;

import java.awt.Graphics;
import java.util.ArrayList;

import poke.fast.Handler;
import poke.fast.entities.Entity;
import poke.fast.entities.inanimates.Inanimate;

public class DialogueManager {
	
	private Handler handler;
	private ArrayList<Entity> entities;
	private DialogueBox dialogueBox;
	
	private boolean say;
	private String message;
	
	public DialogueManager (Handler handler) {
		this.handler = handler;
		entities = handler.getMap().getEntityManager().getEntities();
		dialogueBox = new DialogueBox(handler);
		say = false;
	}
	
	public void tick () {
		dialogueBox.tick();
		for (Entity e: entities) {
			if (e instanceof Inanimate)
				if (((Inanimate) e).isInteracting()) {
					message = ((Inanimate) e).getMessage();
					say = true;
				}
		}
	}
	
	public void render (Graphics g) {
		dialogueBox.render(g);
		if ((handler.getKeyManager().spacePressed || DialogueBox.isSaying) && say && message != null) {
			dialogueBox.say(g, message);
			if (DialogueBox.said) {
				DialogueBox.isSaying = false;
				DialogueBox.said = false;
				message = null;
			}
		} else {
			message = null;
			say = false;
		}
		
	}

	public DialogueBox getDialogueBox() {
		return dialogueBox;
	}

	public void setDialogueBox(DialogueBox dialogueBox) {
		this.dialogueBox = dialogueBox;
	}
	
}
