package poke.fast.entities;

import java.awt.Graphics;
import java.util.ArrayList;

import poke.fast.Handler;
import poke.fast.entities.characters.Player;

public class EntityManager {

	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;		//variable-size array of entity sub-classes
	
	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
	}
	
	public void tick() {
		
		for(int i=0;i<entities.size();i++) {
			Entity e = entities.get(i);
			e.tick();
		}
		player.tick();
		
	}
	public void render(Graphics g) {
		
		for(Entity e : entities) {
			e.render(g);
		}
		player.render(g);
		
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
}
