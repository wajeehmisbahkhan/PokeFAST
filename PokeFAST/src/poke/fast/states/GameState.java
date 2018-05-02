package poke.fast.states;

import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.entities.characters.Assignment;
import poke.fast.entities.characters.Player;
import poke.fast.entities.characters.Senior;
import poke.fast.entities.characters.Teacher;
import poke.fast.maps.Map;
import poke.fast.maps.MapManager;
import poke.fast.textboxes.DialogueManager;
import poke.fast.transitions.TransitionManager;

public class GameState extends State {
	private String currentEnemy = null;
	private Map map;
	public boolean victory = false;
	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
	private DialogueManager dialogueManager;
	private MapManager mapManager;
	public GameState (Handler handler) {
		super(handler);
		map = new Map(handler, "fast");
		handler.setMap(map);
		dialogueManager = new DialogueManager(handler);
		transitionManager = new TransitionManager(handler);
		mapManager = new MapManager(handler);
	}

	public void tick() {
		map.tick();
		dialogueManager.tick();
		battleCheck();
		transitionManager.tick();
		mapManager.tick();

	}

	public void render(Graphics g) {
		map.render(g);
		dialogueManager.render(g);
		transitionManager.render(g);
	}
	
	private void battleCheck() {
		/*String encounter = getPlayer().checkEntityEncounter(getPlayer().getxMove(),getPlayer().getyMove());
		if (encounter != null) {
			encounter = encounter.toLowerCase();
			if (encounter.equals("teacher"))
				State.setState(new BattleState(handler, map.entityManager.getPlayer(),map.entityManager.getTeacher()));
			if (encounter.equals("senior")) {
				map.entityManager.getSenior().setShouldRender(true);
				Graphics g = null;
				map.entityManager.getSenior().startRagging(g,dialogueManager);
			}
			if (encounter.equals("assignment"))
				assignmentEncountered();
		}*/
		
		currentEnemy = getPlayer().checkEntityEncounter(getPlayer().getxMove(),getPlayer().getyMove());
		if (!handler.getMap().getEntityManager().getSenior().isAlive()
				||	!handler.getMap().getEntityManager().getTeacher().isAlive()
				|| !handler.getMap().getEntityManager().getAssignment().isAlive())
			victory = true;
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

	public String getCurrentEnemy() {
		return currentEnemy;
	}

	public void setCurrentEnemy(String currentEnemy) {
		this.currentEnemy = currentEnemy;
	}
	
}
