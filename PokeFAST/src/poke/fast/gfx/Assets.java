package poke.fast.gfx;

import java.awt.image.BufferedImage;

//This will be used to load all the graphical assets. It will be mostly static so it can be accessed directly through the class
public class Assets {
	public static final int width = 32, height = 32; // This will be used for most of the objects
	
	//GameItems
	public static BufferedImage player, grass, rock, dirt, tree;	
	
	//The Menu Items
	public static BufferedImage[] btn_start;
	public static BufferedImage menuBg, logo;
	
	
	//When the game initializes
	public static void init () {
		//Load all the sprite sheets
		SpriteSheet menuSheet = new SpriteSheet(ImageLoader.loadImage("/textures/menu_sheet.png"));
		SpriteSheet menuBack = new SpriteSheet(ImageLoader.loadImage("/textures/menu_bg.jpg")); //Change later to become part of menu_sheet
		SpriteSheet logoSheet = new SpriteSheet(ImageLoader.loadImage("/textures/logo.png")); //Change later
		
		//player and tiles
		SpriteSheet boxSheet = new SpriteSheet(ImageLoader.loadImage("/textures/box_sheet.png"));
		
		//Crop the required items
		//Menu
		menuBg = menuBack.crop(0, 0, 615, 393);
		logo = logoSheet.crop(0, 0, 2125, 521);
		
		btn_start = new BufferedImage[2];
		btn_start[0] = menuSheet.crop(0, 0, 160, 45);
		btn_start[1] = menuSheet.crop(160, 0, 160, 45);
		
		
		//Game
		player = boxSheet.crop(0, 0, width, height);		//replace this with array of player textures
		grass = boxSheet.crop(width, 0, width, height);
		rock = boxSheet.crop(width*2, height*3, width, height);
		dirt = boxSheet.crop(0, height*3, width, height);
		tree = boxSheet.crop(width, height, width, height);
		
		
	}
	
}
