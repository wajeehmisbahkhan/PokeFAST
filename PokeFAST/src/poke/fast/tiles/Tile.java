package poke.fast.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0);
	public static Tile dirtTile = new DirtTile(1);
	public static Tile rockTile = new RockTile(2);
	public static Tile shadesTile = new ShadesTile(3);
	public static Tile csTile = new CSTile(4);
	public static Tile shopTile = new ShopTile(5);
	public static Tile teleportTile = new TeleportTile(6);
	public static Tile shadowTile = new ShadowTile(7);
	
	//CLASS
	
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
	
	protected BufferedImage texture;
	protected final int id;
	
	public Tile( BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		tiles[id] = this;
	}
	
	public void tick() {
		
	}
	
	public void render( Graphics g, int x, int y) {
		g.drawImage(texture,  x,  y,  TILEWIDTH,  TILEHEIGHT,  null);
	}
	
	public boolean isSolid() {		//Override for rocks, benches, and walls
		return false;
	}
	
	public boolean renderLater() { //Override for shades etc
		return false;
	}
	
	public int getId() {
		return id;
	}
}
