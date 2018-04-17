package poke.fast.maps;

import java.awt.Graphics;

import poke.fast.Handler;
import poke.fast.entities.EntityManager;
import poke.fast.entities.characters.Assignment;
import poke.fast.entities.characters.Player;
import poke.fast.entities.characters.Senior;
import poke.fast.entities.characters.Teacher;
import poke.fast.entities.inanimates.Fountain;
import poke.fast.entities.inanimates.Tree;
import poke.fast.tiles.Tile;
import poke.fast.utils.TextReader;

//This is going to be the class which will display the world to be loaded
public class Map {
	private Handler handler;
	private int width, height; //The size of the world; Will be defined in the first line of the map
	private int[][] tiles; //The tiles will be stored in this 2d array using their id's
	
	private int spawnX, spawnY; //The player's position in terms of tiles; Will be defined according to the entry point
	private int currentMap;		//0 fast, 1 cs1
	
	
	//Entities
	public EntityManager entityManager;
	
	public Map (Handler handler, String name) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler,0,0), new Teacher(handler, 600, 100), new Senior(handler, 400, 300), new Assignment(handler, 250, 500));
		entityManager.addEntity(new Tree(handler, 100, 250));
		entityManager.addEntity(new Tree(handler, 100, 300));
		entityManager.addEntity(new Tree(handler, 100, 400));
		entityManager.addEntity(new Fountain(handler, 1470, 1020));	//don't change
		loadMap(name); //Fills up the tiles array with id's
		
		//spawn position of player
		entityManager.getPlayer().setX(100);
		entityManager.getPlayer().setY(1200);
		
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void loadMap (String name) {
		//Player loading
		spawnX = 6*64;		//(int) entityManager.getPlayer().getX(); //Change later according to entry position
		spawnY = 9*64;		//(int) entityManager.getPlayer().getY();
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
		
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
	
	
	//overload of loadMap, will merge later
	
	public void loadMap (String name, int direction) {
		//Player loading
		if(currentMap==0) {
			
			if(direction==3) {
				System.out.println("YO");
				spawnX = 6*64;
				spawnY = 9*64;
			}
			else if(direction==0){
				System.out.println("YO");
				spawnX = 6*64;
				spawnY = 5*64;
			}
			else if(direction==1){
				System.out.println("YO");
				spawnX = 13*64;
				spawnY = 7*64;
			}
			else if(direction==2){
				System.out.println("YO");
				spawnX = 13*64;
				spawnY = 7*64;
			}		
				
		}
		
		else if(currentMap==1) {

			if(direction==0) {
				System.out.println("YO");
				spawnX = 9*64;
				spawnY = 20*64;
			}
			else if(direction==3){
				System.out.println("YO");
				spawnX = 6*64;
				spawnY = 13*64;
			}
			else if(direction==1){
				System.out.println("YO");
				spawnX = 17*64;
				spawnY = 17*64;
			}
			else if(direction==2){
				System.out.println("YO");
				spawnX = 17*64;
				spawnY = 17*64;
			}		
			
		}
			
		//Teleportation links
		//(1) 9 20 		6 9
		//(2) 6 13		6 5
		//(3) 17 17		13 7
		
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
		
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
	
	//overload loadmaop
	
	
	
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
		
		
		//tilesAboveEntities
		
				for (int y = yStart; y < yEnd; y++) 
					for (int x = xStart; x < xEnd; x++) 
						if( getTile(x,y).renderLater()) 
							getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
								(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
						
					
				
		
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
	
	//Teleportation links
	//(1) 9 20 		6 9
	//(2) 6 13		6 5
	//(3) 17 17		13 7
	
	
	
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

	public int getCurrentMap() {
		return currentMap;
	}

	public void setCurrentMap(int currentMap) {
		this.currentMap = currentMap;
	}
}
