package poke.fast.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import poke.fast.Handler;
import poke.fast.entities.characters.Assignment;
import poke.fast.entities.characters.Player;
import poke.fast.entities.characters.Senior;
import poke.fast.entities.characters.Teacher;
import poke.fast.gfx.Transition;
import poke.fast.entities.inanimates.Fountain;

public class EntityManager {

	private Handler handler;
	private Player player;
	private Teacher teacher;
	private Assignment assignment;
	private Senior senior;
	private ArrayList<Entity> entities;		//variable-size array of entity sub-classes
	private Comparator<Entity> renderOrder = new Comparator<Entity>() {

		@Override
		public int compare(Entity a, Entity b) {
			if(b instanceof Fountain)
				return 1;
			if(a.getY()+a.getHeight() < b.getY()+b.getHeight())
				return -1;
			return 1;
		}
		
	};
	
	
	public EntityManager(Handler handler, Player player, Teacher teacher, Senior senior, Assignment assignment) {
		this.handler = handler;
		this.player = player;
		this.teacher = teacher;
		this.senior = senior;
		this.assignment = assignment;
		entities = new ArrayList<Entity>();
		addEntity(player);
		addEntity(teacher);
		addEntity(senior);
		addEntity(assignment);
	}
	
	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Senior getSenior() {
		return senior;
	}

	public void setSenior(Senior senior) {
		this.senior = senior;
	}

	public void tick() {
		
		for(int i=0;i<entities.size();i++) {
			Entity e = entities.get(i);
			if (!Transition.playing) //So it doesn't run during transitions... duh
				e.tick();
		}
		entities.sort(renderOrder);
		
		
	}
	public void render(Graphics g) {
		
		for(Entity e : entities) {
			e.render(g);
		}
		
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
