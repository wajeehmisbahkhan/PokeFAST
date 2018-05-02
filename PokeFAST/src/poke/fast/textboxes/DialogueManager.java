package poke.fast.textboxes;

import java.awt.Graphics;
import java.util.ArrayList;

import poke.fast.Handler;
import poke.fast.entities.Entity;
import poke.fast.entities.inanimates.Inanimate;
import poke.fast.transitions.TransitionManager;

public class DialogueManager {
	
	private Handler handler;
	private ArrayList<Entity> entities;
	private DialogueBox dialogueBox;
	
	private boolean itemCheck;
	private String message;
	
	//Extra checkers
	private boolean enemyComing = false;
	
	public DialogueManager (Handler handler) {
		this.handler = handler;
		entities = handler.getMap().getEntityManager().getEntities();
		dialogueBox = new DialogueBox(handler);
		itemCheck = false;
	}
	
	public void tick () {
		dialogueBox.tick();
		for (Entity e: entities) {
			if (e instanceof Inanimate)
				if (((Inanimate) e).isInteracting()) {
					message = ((Inanimate) e).getMessage();
				}
		}

		
		
		String enemy = handler.getGame().getGameState().getCurrentEnemy();
		if (enemy != null) {
			enemy = enemy.toLowerCase();
			if (enemy.equals("senior")) {
				enemyComing = true;
				message = "OYE FRESHIE!";
				if (DialogueBox.said) {
					message = null;
					enemyComing = false;
					TransitionManager.change = true;
				}
			}
		}
		
		if (handler.getGame().getGameState().victory) {
			message = "Congratulations, you have survived the semester with a GPA of " + handler.getMap().getEntityManager().getPlayer().getGPA() + "... Thank you for playing :)... wait, what's going on!?";
			TransitionManager.change = false;
		}
		
		if (message != null)
			itemCheck = true;
	}
	
	public void render (Graphics g) {
		dialogueBox.render(g);
		//Check for items
		if ((handler.getKeyManager().spacePressed || DialogueBox.isSaying) && itemCheck && message != null) {
			dialogueBox.say(g, message);
			if (DialogueBox.said) {
				DialogueBox.isSaying = false;
				DialogueBox.said = false;
				message = null;
			}
		} else if (enemyComing && message != null) {
			dialogueBox.say(g, message);
			if (DialogueBox.said) {
				DialogueBox.isSaying = false;
				DialogueBox.said = false;
				message = null;
				enemyComing = false;
			}
		} else if (handler.getGame().getGameState().victory && message != null) {
			dialogueBox.say(g, message);
			if (DialogueBox.said) {
				DialogueBox.isSaying = false;
				DialogueBox.said = false;
				message = null;
			}
		} else {
			message = null;
			itemCheck = false;
		}
		
	}

	public DialogueBox getDialogueBox() {
		return dialogueBox;
	}

	public void setDialogueBox(DialogueBox dialogueBox) {
		this.dialogueBox = dialogueBox;
	}
	
}
