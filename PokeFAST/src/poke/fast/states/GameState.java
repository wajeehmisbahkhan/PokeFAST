package poke.fast.states;

import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.entities.characters.Assignment;
import poke.fast.entities.characters.Player;
import poke.fast.entities.characters.Senior;
import poke.fast.entities.characters.Teacher;
import poke.fast.maps.Map;

public class GameState extends State {

	private Map map;

	public GameState (Handler handler) {
		super(handler);
		map = new Map(handler, "fast");
		handler.setMap(map);

	}

	public void tick() {
		map.tick();
		if (handler.getKeyManager().space)

			State.setState(new BattleState(handler, map.entityManager.getPlayer(),map.entityManager.getSenior()));

			//State.setState(new BattleState(handler, map.entityManager.getPlayer(),map.entityManager.getAssignment()));
	
	}

	public void render(Graphics g) {
		map.render(g);
	}
	
	public Player getPlayer () {
		return map.entityManager.getPlayer();
	}
	public Teacher getTeacher () {
		return  map.entityManager.getTeacher();
	}
	public Senior getSenior () {
		return  map.entityManager.getSenior();
	}
	public Assignment getAssignment () {
		return  map.entityManager.getAssignment();
	}
	
	
}
