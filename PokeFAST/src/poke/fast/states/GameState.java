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
import poke.fast.textboxes.DialogueBox;

public class GameState extends State {
	private int encounter1,encounter2;
	private Map map;
	private DialogueBox dialogueBox;
	public GameState (Handler handler) {
		super(handler);
		map = new Map(handler, "fast");
		handler.setMap(map);
		SoundManager.setBackground("game");
		

		dialogueBox = new DialogueBox(handler);
	}

	public void tick() {
		map.tick();
		int encounter1 =	getPlayer().checkEntityEncounter(0f,getPlayer().getyMove());
		int encounter2 =	getPlayer().checkEntityEncounter(getPlayer().getxMove(),0f);
		if (	encounter1	!=0	||	encounter2	!=0	) {		//if encounter with enemy occurs
			Transition.playing = true;
			if (Transition.played) {
				if(	encounter1==1	||	encounter2==1	){		//teacher
					State.setState(new BattleState(handler, map.entityManager.getPlayer(),map.entityManager.getTeacher()));
				}
				else if(	encounter1==2	||	encounter2==2	){		//senior
					State.setState(new BattleState(handler, map.entityManager.getPlayer(),map.entityManager.getSenior()));
				}
				else if(	encounter1==3	||	encounter2==3	){		//assignment
					State.setState(new BattleState(handler, map.entityManager.getPlayer(),map.entityManager.getAssignment()));
				}
				Transition.playing = false;
				Transition.played = false;
			}
		}

		
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
		
		if ( Transition.playing) { //First condition will change so the entire structure MIGHT change
			transition.swipeIn(g);
		}
		map.render(g);

		
		dialogueBox.tick();
		dialogueBox.render(g);
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
