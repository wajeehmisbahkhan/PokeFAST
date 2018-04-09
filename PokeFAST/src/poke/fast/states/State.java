package poke.fast.states;

import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.entities.characters.Player;
import poke.fast.textboxes.DialogueBox;

//This class will change the state of the game between menu and play
public abstract class State {
	
	//The currentState will be null by default and will be set to menu when the game starts
	private static State currentState = null; //It is static because we want to access it directly through the class and it can only be one thing globally
	
	//The handler
	protected Handler handler;	
	//Constructor is for the inherited children
	public State(Handler handler) {
		this.handler = handler;
		
	}
	
	//These methods will be defined by the children
	public abstract void tick();
	public abstract void render(Graphics g);
	
	//GETTERS & SETTERS
	//Get the current state
	public static State getState () { //To access the static just by class
		return currentState;
	}
	public static void setState (State state) {
		currentState = state;
	}
	
	
	//Override in gamestate
	public Player getPlayer() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
