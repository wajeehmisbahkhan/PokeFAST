package poke.fast.maps;

import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.entities.EntityManager;
import poke.fast.entities.characters.Assignment;
import poke.fast.entities.characters.Player;
import poke.fast.entities.characters.Senior;
import poke.fast.entities.characters.Teacher;
import poke.fast.entities.inanimates.Tree;
import poke.fast.tiles.Tile;
import poke.fast.utils.TextReader;

//This is going to be the class which will display the world to be loaded
public class Map {
	private Handler handler;
	private int width, height; //The size of the world; Will be defined in the first line of the map
	private int[][] tiles; //The tiles will be stored in this 2d array using their id's
	
	private int spawnX, spawnY; //The player's position in terms of tiles; Will be defined according to the entry point
	
	//Entities
	public EntityManager entityManager;
	
	public Map (Handler handler, String name) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler,100,100), new Teacher(handler, 300, 100), new Senior(handler, 400, 100), new Assignment(handler, 250, 100));
		entityManager.addEntity(new Tree(handler, 100, 250));
		entityManager.addEntity(new Tree(handler, 100, 300));
		entityManager.addEntity(new Tree(handler, 100, 400));
		
		loadMap(name); //Fills up the tiles array with id's
		
		//spawn position of player
		entityManager.getPlayer().setX(200);
		entityManager.getPlayer().setY(200);
		
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void loadMap (String name) {
		//Player loading
		spawnX = 0; //Change later according to entry position
		spawnY = 0;
		//Map loading
		String file = TextReader.loadFileAsString("res/maps/" + name + ".map");
		String[] tokens = file.split("\\s+");
		width = TextReader.parseInt(tokens[0]);
		height = TextReader.parseInt(tokens[1]);
		
		tiles = new int[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x][y] = TextReader.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
	
	public void tick () {
		entityManager.tick();
	}
	
	public void render (Graphics g) {
		//Only render tiles that are within the game camera
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, ((handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH) + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, ((handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT) + 1);
		//Render the tiles
		for (int y = yStart; y < yEnd; y++) { //Prevents problems
			for (int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		
		//entities
		entityManager.render(g);
		
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) //In case our starting and ending are miscalculated
			return Tile.grassTile;
		//The tile is taken using its id
		Tile t = Tile.tiles[tiles[x][y]];
		//If a wrong number is passed
		if (t == null)
			return Tile.dirtTile;
		return t;
	}
	
	//GETTERS & SETTERS
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getSpawnX() {
		return spawnX;
	}

	public void setSpawnX(int spawnX) {
		this.spawnX = spawnX;
	}

	public int getSpawnY() {
		return spawnY;
	}

	public void setSpawnY(int spawnY) {
		this.spawnY = spawnY;
	}
}
