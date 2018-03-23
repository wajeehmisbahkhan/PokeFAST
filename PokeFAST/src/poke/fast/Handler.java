package poke.fast;

import poke.fast.input.MouseManager;


//This class will be helpful to get any of the major parts of the game such as gameCamera, keyManager and the game itself
public class Handler {
	private Game game;
	
	public Handler (Game game) {
		this.game = game;
	}
	
	public Game getGame () {
		return game;
	}
	
	public MouseManager getMouseManager () {
		return game.getMouseManager();
	}
}
