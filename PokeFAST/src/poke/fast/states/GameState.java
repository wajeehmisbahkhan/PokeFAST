package poke.fast.states;

import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.entities.characters.Player;
import poke.fast.entities.characters.Teacher;
import poke.fast.entities.inanimates.Tree;
import poke.fast.gfx.GameCamera;
import poke.fast.maps.Map;

public class GameState extends State {

	private Map map;
	private Player player;
	private Teacher teacher;
	private Tree tree;
	
	public GameState (Handler handler) {
		super(handler);
		map = new Map(handler, "fast");
		handler.setMap(map);
		player = new Player(handler, 100, 100);
		teacher = new Teacher(handler, 200,200);
		tree = new Tree(handler, 150, 100);
	}

	public void tick() {
		map.tick();
		player.tick();
		teacher.tick();
		tree.tick();
	}

	public void render(Graphics g) {
		map.render(g);
		player.render(g);
		teacher.render(g);
		tree.render(g);
	}
	
}
