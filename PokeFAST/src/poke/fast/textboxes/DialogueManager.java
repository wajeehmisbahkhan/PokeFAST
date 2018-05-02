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
	
	private String message;
	
	//Extra checkers
	private boolean itemCheck;
	private boolean enemyCheck;
	private boolean victoryCheck;
	
	public DialogueManager (Handler handler) {
		this.handler = handler;
		entities = handler.getMap().getEntityManager().getEntities();
		dialogueBox = new DialogueBox(handler);
		itemCheck = false;
		enemyCheck = false;
		victoryCheck = false;
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
		if (enemy != null && !enemyCheck) {
			enemyCheck = true;
			enemy = enemy.toLowerCase();
			if (enemy.equals("senior")) {
				message = "OYE FRESHIE!";
			} else if (enemy.equals("teacher")) {
				message = "Time for a test...";
			} else {
				message = "Surprise!";
			}
		}
		
		if (handler.getGame().getGameState().victory && !victoryCheck) {
			victoryCheck = true;
			message = "Congratulations, you have survived the semester with a GPA of " + (float) handler.getMap().getEntityManager().getPlayer().getGPA()/100 + "... Thank you for playing :)";
		}
		
		if (message != null)
			itemCheck = true;
	}
	
	public void render (Graphics g) {
		dialogueBox.render(g);
		//Check for items
		if ((handler.getKeyManager().spacePressed || DialogueBox.isSaying) && itemCheck && message != null &&!enemyCheck) {
			dialogueBox.say(g, message);
			if (DialogueBox.said) {
				DialogueBox.isSaying = false;
				DialogueBox.said = false;
				message = null;
			}
		} else if (enemyCheck && message != null) {
			dialogueBox.say(g, message);
			if (DialogueBox.said) {
				DialogueBox.isSaying = false;
				DialogueBox.said = false;
				message = null;
				enemyCheck = false;
				TransitionManager.change = true;
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
