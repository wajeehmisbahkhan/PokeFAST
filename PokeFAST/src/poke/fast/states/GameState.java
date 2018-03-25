package poke.fast.states;

import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.gfx.GameCamera;
import poke.fast.maps.Map;

public class GameState extends State {

	private Map map;
	
	//The Game Camera for the game
	private GameCamera gameCamera;
	
	public GameState (Handler handler) {
		super(handler);
		map = new Map(handler, "fast");
		handler.setMap(map);
		gameCamera = new GameCamera(handler);
	}

	public void tick() {
		map.tick();
	}

	public void render(Graphics g) {
		map.render(g);
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}

}
