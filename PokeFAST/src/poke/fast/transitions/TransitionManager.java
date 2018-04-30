package poke.fast.transitions;

import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.states.GameState;
import poke.fast.states.MenuState;
import poke.fast.states.State;

public class TransitionManager {
	
	public static boolean change;

	private Handler handler;
	
	private Transition fadeIn;
	private Transition fadeOut;
	private Transition swipeIn;
	
	public TransitionManager(Handler handler) {
		this.handler = handler;
		change = false;
		fadeIn = new FadeIn(handler);
		fadeOut = new FadeOut(handler);
		swipeIn = new SwipeIn(handler);
	}
	
	public void tick () {
		//If transition called
		if (change) {
			handler.getKeyManager().canMove = false;
			//For menu state
			if (State.getState() instanceof MenuState) {
				fadeOut.setSpeed(2);
				fadeOut.tick();
				if (Transition.played) {
					handler.getGame().setGameState(new GameState(handler));
					State.setState(handler.getGame().getGameState());
				}
			} else if (State.getState() instanceof GameState) {
				swipeIn.setSpeed(2);
				swipeIn.tick();
				if (Transition.played) {
					handler.getGame().setGameState(new MenuState(handler));
					State.setState(handler.getGame().getGameState());
				}
			}
		}
		
		
		
		//Reset everything
		if (Transition.played) {
			handler.getKeyManager().canMove = true;
			change = false;
			Transition.played = false;
		}
	}
	
	public void render (Graphics g) {
		if (change) {
			if (State.getState() instanceof MenuState) {
				fadeOut.render(g);
			} else if (State.getState() instanceof GameState) {
				swipeIn.render(g);
			}
		}
	}
	
}
