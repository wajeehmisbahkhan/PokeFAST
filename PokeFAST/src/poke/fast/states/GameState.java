package poke.fast.states;

import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.entities.characters.Assignment;
import poke.fast.entities.characters.Player;
import poke.fast.entities.characters.Senior;
import poke.fast.entities.characters.Teacher;
import poke.fast.entities.inanimates.Tree;
import poke.fast.maps.Map;

public class GameState extends State {

	private Map map;
	//private Teacher teacher;
	private Senior senior;
	private Assignment assignment;
	
	public GameState (Handler handler) {
		super(handler);
		map = new Map(handler, "fast");
		handler.setMap(map);
		//teacher = new Teacher(handler, 200, 200);
		senior = new Senior(handler, 300, 300);
		assignment = new Assignment(handler, 400, 400);

	}

	public void tick() {
		map.tick();
		//teacher.tick();
		senior.tick();
		assignment.tick();
		if (handler.getKeyManager().space)
			State.setState(new BattleState(handler, map.entityManager.getPlayer(), senior));
	}

	public void render(Graphics g) {
		map.render(g);
		//teacher.render(g);

	}
	
	public Player getPlayer () {
		return  map.entityManager.getPlayer();
	}
	
	
}
