package poke.fast.gfx;

import java.awt.image.BufferedImage;

//This will be used to load all the graphical assets. It will be mostly static so it can be accessed directly through the class
public class Assets {
	public static final int width = 32, height = 32; // This will be used for most of the objects
	
	//GameItems
	public static BufferedImage player, grass, rock, dirt, tree;
	
	public static BufferedImage[] player_still, player_down, player_up, player_left, player_right;
	
	//The Menu Items
	public static BufferedImage[] btn_start;
	public static BufferedImage menuBg, logo;
	
	
	//When the game initializes
	public static void init () {
		//Load all the sprite sheets
		SpriteSheet menuSheet = new SpriteSheet(ImageLoader.loadImage("/textures/menu_sheet.png"));
		SpriteSheet menuBack = new SpriteSheet(ImageLoader.loadImage("/textures/menu_bg.jpg"));
		
		//player and tiles
		SpriteSheet boxSheet = new SpriteSheet(ImageLoader.loadImage("/textures/box_sheet.png"));
		SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/player_sheet.png"));
		
		//Crop the required items
		//Menu
		menuBg = menuBack.crop(0, 0, 400, 400);
		
		btn_start = new BufferedImage[2];
		btn_start[0] = menuSheet.crop(0, 0, 160, 45);
		btn_start[1] = menuSheet.crop(160, 0, 160, 45);
		
		//Game
		
		player_still = new BufferedImage[4];
		player_up = new BufferedImage[3];
		player_down = new BufferedImage[3];
		player_left = new BufferedImage[3];
		player_right = new BufferedImage[3];
		
		for(int i=0;i<4;i++)
			player_still[i] = playerSheet.crop(0,  height*i, width, height);
		for(int i=0;i<3;i++) {
			player_down[i] = playerSheet.crop(width*(i+1), 0, width, height);
			player_left[i] = playerSheet.crop(width*(i+1), height, width, height);
			player_right[i] = playerSheet.crop(width*(i+1), height*2, width, height);
			player_up[i] = playerSheet.crop(width*(i+1), height*3, width, height);
		}
		player = boxSheet.crop(0, 0, width, height);		//replace this with array of player textures
		grass = boxSheet.crop(width, 0, width, height);	
		rock = boxSheet.crop(width*2, height*3, width, height);
		dirt = boxSheet.crop(0, height*3, width, height);
		tree = boxSheet.crop(width, height, width, height);
		
	}
	
}
