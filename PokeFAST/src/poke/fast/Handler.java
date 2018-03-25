package poke.fast;

import poke.fast.gfx.GameCamera;
import poke.fast.input.KeyManager;
import poke.fast.input.MouseManager;
import poke.fast.maps.Map;
import poke.fast.states.GameState;


//This class will be helpful to access the properties of the major objects such as the game and the map
public class Handler {
	private Game game;
	private Map map;
	private GameState gameState;
	
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
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
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
	
	public GameCamera getGameCamera() {
		return game.getGameCamera() ;
	}
	
}
