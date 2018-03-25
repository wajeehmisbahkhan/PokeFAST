package poke.fast;

import poke.fast.input.MouseManager;
import poke.fast.maps.Map;
import poke.fast.states.GameState;


//This class will be helpful to access the properties of the major objects such as the game and the map
public class Handler {
	private Game game;
	private Map map;
	
	public Handler (Game game) {
		this.game = game;
	}
	
	//Return these values
	public Game getGame () {
		return game;
	}
	
	public MouseManager getMouseManager () {
		return game.getMouseManager();
	}
	
	public GameState getGameState () {
		return game.getGameState();
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public int getWidth() {
		return game.getWidth();
	}
	public int getHeight() {
		return game.getHeight();
	}
}
