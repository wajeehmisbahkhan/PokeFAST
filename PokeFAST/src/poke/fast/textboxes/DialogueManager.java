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
	
	//Extra checkers
	private boolean enemyComing = false;
	private int dialogueNumber = 0;
	
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
				}
		}

		
		
		//If player is at Canteen
		float x = handler.getGame().getGameState().getPlayer().getX();
		float y = handler.getGame().getGameState().getPlayer().getY();
		
		if( (x > 300f && x < 500f) && (y < 320f && y > 290f) ) {
			if (handler.getGame().getGameState().getSenior().isAlive() && dialogueNumber < 2) {
				enemyComing = true;
				message = "OYE FRESHIE!";
			}
		}
		
		if (message != null)
			say = true;
	}
	
	public void render (Graphics g) {
		dialogueBox.render(g);
		//Check for items
		if ((handler.getKeyManager().spacePressed || DialogueBox.isSaying) && say && message != null) {
			dialogueBox.say(g, message);
			if (DialogueBox.said) {
				DialogueBox.isSaying = false;
				DialogueBox.said = false;
				message = null;
			}
		} else if (enemyComing) {
			dialogueBox.say(g, message);
			if (DialogueBox.said) {
				DialogueBox.isSaying = false;
				DialogueBox.said = false;
				message = null;
				enemyComing = false;
				dialogueNumber++;
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
