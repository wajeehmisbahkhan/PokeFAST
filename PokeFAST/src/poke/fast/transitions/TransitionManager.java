package poke.fast.transitions;

import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.states.BattleState;
import poke.fast.states.GameState;
import poke.fast.states.MenuState;
import poke.fast.states.OutroState;
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
				if (handler.getGame().getGameState().victory) {
					fadeOut.tick();
				} else {
					swipeIn.setSpeed(2);
					swipeIn.tick();
				}
				if (Transition.played) {
					change = false;
					String enemy = handler.getGame().getGameState().getCurrentEnemy();
					if (enemy != null) {
						if (enemy.toLowerCase().equals("senior")) {
							State.setState(new BattleState(handler, handler.getGame().getGameState().getPlayer(), handler.getMap().getEntityManager().getSenior()));
						} else if (enemy.toLowerCase().equals("teacher")) {
							State.setState(new BattleState(handler, handler.getGame().getGameState().getPlayer(), handler.getMap().getEntityManager().getTeacher()));
						} else if (enemy.toLowerCase().equals("assignment")) {
							State.setState(new BattleState(handler, handler.getGame().getGameState().getPlayer(), handler.getMap().getEntityManager().getAssignment()));
						}
					}
					
					//Game end
					if (handler.getGame().getGameState().victory)
						State.setState(new OutroState(handler));
					
				}
			}
		}
		
		
		
		//Reset everything
		if (Transition.played) {
			handler.getKeyManager().canMove = true;
			change = false;
			Transition.played = false;
			swipeIn = new SwipeIn(handler);
			fadeIn = new FadeIn(handler);
			fadeOut = new FadeOut(handler);
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
