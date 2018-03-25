package poke.fast.gfx;

import java.awt.image.BufferedImage;

//This will be used to load all the graphical assets. It will be mostly static so it can be accessed directly through the class
public class Assets {
	public static final int width = 32, height = 32; // This will be used for most of the objects
	
	//GameItems
	public static BufferedImage player, grass, rock, dirt, tree;	//spritesheet needed
	
	//The Menu Items
	public static BufferedImage[] btn_start;
	public static BufferedImage menuBg, logo;
	
	
	//When the game initializes
	public static void init () {
		//Load all the sprite sheets
		SpriteSheet menuSheet = new SpriteSheet(ImageLoader.loadImage("/textures/menu_sheet.png"));
		SpriteSheet menuBack = new SpriteSheet(ImageLoader.loadImage("/textures/menu_bg.jpg"));
		
		//Crop the required items
		//Menu
		menuBg = menuBack.crop(0, 0, 400, 400);
		
		btn_start = new BufferedImage[2];
		btn_start[0] = menuSheet.crop(0, 0, 160, 45);
		btn_start[1] = menuSheet.crop(160, 0, 160, 45);
		
		
		//Game
	}
	
}
