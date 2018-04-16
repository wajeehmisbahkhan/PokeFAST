package poke.fast.states;

import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.entities.characters.Assignment;
import poke.fast.entities.characters.Player;
import poke.fast.entities.characters.Senior;
import poke.fast.entities.characters.Teacher;
import poke.fast.gfx.Transition;
import poke.fast.maps.Map;
import poke.fast.sfx.SoundManager;

public class GameState extends State {
	
	private Map map;
	private Player player;
	
	public GameState (Handler handler) {
		super(handler);
		map = new Map(handler, "fast");
		handler.setMap(map);
	}

	public void tick() {

		map.tick();
		if (getPlayer().checkEntityEncounter(0f,getPlayer().getyMove())	||	getPlayer().checkEntityEncounter(getPlayer().getxMove(),0f)	)
			State.setState(new BattleState(handler, map.entityManager.getPlayer(),map.entityManager.getSenior()));
		
		if (Transition.played) {
			Transition.played = false;
			State.setState(new BattleState(handler, player, map.entityManager.getAssignment()));
		}
		
		if (	getPlayer().stepOnPortal()	&& handler.getKeyManager().spacePressed) {
			if(handler.getMap().getCurrentMap()==0) {
				map.loadMap("cs_1",100);
				handler.getMap().setCurrentMap(1);
			}
			
			else if(handler.getMap().getCurrentMap()==1) {
				map.loadMap("fast",100);
				handler.getMap().setCurrentMap(0);
			}
				
		}

	}

	public void render(Graphics g) {
		map.render(g);
		if ( Transition.playing) { //First condition will change so the entire structure MIGHT change
			Transition.playing = true;
			transition.swipeIn(g);
		}
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
