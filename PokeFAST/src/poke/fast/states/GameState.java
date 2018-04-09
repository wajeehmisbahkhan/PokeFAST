package poke.fast.states;

import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.entities.characters.Assignment;
import poke.fast.entities.characters.Player;
import poke.fast.entities.characters.Senior;
import poke.fast.entities.characters.Teacher;
import poke.fast.entities.inanimates.Fountain;
import poke.fast.entities.inanimates.Tree;
import poke.fast.maps.Map;

public class GameState extends State {

	private Map map;
	private Player player;
	private Teacher teacher;
	private Senior senior;
	private Assignment assignment;
	private Tree tree;
	private Fountain fountain;	
	public GameState (Handler handler) {
		super(handler);
		map = new Map(handler, "fast");
		handler.setMap(map);
		player = new Player(handler, 200, 200);
		//teacher = new Teacher(handler, 200, 200);
		senior = new Senior(handler, 300, 300);
		assignment = new Assignment(handler, 400, 400);
		tree = new Tree(handler, 150, 100);
		fountain = new Fountain(handler, 1500-32, 964+32);

	}

	public void tick() {
		map.tick();
		//player.tick();
		//teacher.tick();
		tree.tick();
		senior.tick();
		assignment.tick();
		if (handler.getKeyManager().space)
			State.setState(new BattleState(handler, player, assignment));
	}

	public void render(Graphics g) {
		map.render(g);
		//player.render(g);
		//teacher.render(g);
		tree.render(g);
		fountain.render(g);
	}
	
	public Player getPlayer () {
		return player;
	}
	
	
}
