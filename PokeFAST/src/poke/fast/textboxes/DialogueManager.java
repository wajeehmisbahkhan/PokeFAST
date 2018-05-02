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
			int gpa = handler.getMap().getEntityManager().getPlayer().getGPA();
			message = "Congratulations, you have survived the semester with a GPA of " + (float) handler.getMap().getEntityManager().getPlayer().getGPA()/100 + "...";
			if (gpa >= 350)
				message += "Woah, you're in the Dean's list of Honor! Way to go... Nerd... ";
			else if (gpa > 270)
				message += "You enjoyed your time at the university and passed like an average student... ";
			else if (gpa > 100)
				message += "You really need to work harder... ";
			else
				message += "but unfortunately you'll have to repeat it... Work harder next time.,, ";
			message += "Thank you for playing :)";
		}
		
		if (message != null)
			itemCheck = true;
	}
	
	public void render (Graphics g) {
		dialogueBox.render(g);
		//Check for items
		if ((handler.getKeyManager().spacePressed || dialogueBox.isSaying) && itemCheck && message != null &&!enemyCheck && !handler.getGame().getGameState().victory) {
			dialogueBox.say(g, message);
			if (dialogueBox.said) {
				dialogueBox.isSaying = false;
				dialogueBox.said = false;
				message = null;
			}
		} else if (enemyCheck && message != null && !handler.getGame().getGameState().victory) {
			dialogueBox.say(g, message);
			if (dialogueBox.said) {
				dialogueBox.isSaying = false; 
				dialogueBox.said = false;
				message = null;
				TransitionManager.change = true;
			}
		} else if (handler.getGame().getGameState().victory && message != null) {
			dialogueBox.say(g, message);
			if (dialogueBox.said) {
				dialogueBox.isSaying = false;
				dialogueBox.said = false;
				message = null;
				TransitionManager.change = true;
			}
		} else {
			message = null;
			itemCheck = false;
		}
		
	}

	public void setEnemyCheck (boolean enemyCheck) {
		this.enemyCheck = enemyCheck;
	}
	
	public DialogueBox getDialogueBox() {
		return dialogueBox;
	}

	public void setDialogueBox(DialogueBox dialogueBox) {
		this.dialogueBox = dialogueBox;
	}
	
}
