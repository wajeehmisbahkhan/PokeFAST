package poke.fast.states;

import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.entities.characters.Assignment;
import poke.fast.entities.characters.Player;
import poke.fast.entities.characters.Senior;
import poke.fast.entities.characters.Teacher;
import poke.fast.maps.Map;
import poke.fast.textboxes.DialogueManager;
import poke.fast.transitions.TransitionManager;

public class GameState extends State {
	private Map map;
	private DialogueManager dialogueManager;
	public GameState (Handler handler) {
		super(handler);
		map = new Map(handler, "fast");
		handler.setMap(map);
		dialogueManager = new DialogueManager(handler);
		transitionManager = new TransitionManager(handler);
	}

	public void tick() {
		map.tick();
		dialogueManager.tick();
		battleCheck();
		transitionManager.tick();
		
		if (	getPlayer().stepOnPortal()	&& handler.getKeyManager().spacePressed	) {
			if(handler.getMap().getCurrentMap()==0) {
				map.loadMap("cs_1",getPlayer().getDirection());
				handler.getMap().setCurrentMap(1);
			}
			
			else if(handler.getMap().getCurrentMap()==1) {
				map.loadMap("fast",getPlayer().getDirection());
				handler.getMap().setCurrentMap(0);
			}
				
		}

	}

	public void render(Graphics g) {
		map.render(g);
		dialogueManager.render(g);
		transitionManager.render(g);
	}
	
	private void battleCheck() {
		String encounter = getPlayer().checkEntityEncounter(getPlayer().getxMove(),getPlayer().getyMove());
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
		}
	}
	
	private void assignmentEncountered() {
		TransitionManager.change = true;
		
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
