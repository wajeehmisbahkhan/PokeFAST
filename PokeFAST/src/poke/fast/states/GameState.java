package poke.fast.states;

import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.entities.characters.Player;
import poke.fast.gfx.GameCamera;
import poke.fast.maps.Map;

public class GameState extends State {

	private Map map;
	private Player player;
	
	public GameState (Handler handler) {
		super(handler);
		map = new Map(handler, "fast");
		handler.setMap(map);
		player = new Player(handler, 100, 100);
		
	}

	public void tick() {
		map.tick();
		player.tick();
	}

	public void render(Graphics g) {
		map.render(g);
		player.render(g);
	}
	
}
