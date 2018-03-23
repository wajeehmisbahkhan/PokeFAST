package poke.fast.gfx;

import java.awt.image.BufferedImage;

//This will be used to load all the graphical assets. It will be mostly static so it can be accessed directly through the class
public class Assets {
	public static final int width = 32, height = 32; // This will be used for most of the objects
	
	
	//The Menu Items
	public static BufferedImage[] btn_start;
	public static BufferedImage menuBg, logo;
	
	
	//When the game initializes
	public static void init () {
		//Load all the sprite sheets
		SpriteSheet menuSheet = new SpriteSheet(ImageLoader.loadImage("/textures/menu_sheet.png"));
		SpriteSheet menuBack = new SpriteSheet(ImageLoader.loadImage("/textures/menu_bg.jpg")); //Change later to become part of menu_sheet
		SpriteSheet logoSheet = new SpriteSheet(ImageLoader.loadImage("/textures/logo.png")); //Change later
		
		//Crop the required items
		//Menu
		menuBg = menuBack.crop(0, 0, 615, 393);
		logo = logoSheet.crop(0, 0, 2125, 521);
		
		btn_start = new BufferedImage[2];
		btn_start[0] = menuSheet.crop(0, 0, 160, 45);
		btn_start[1] = menuSheet.crop(160, 0, 160, 45);
		
		
		//Game
	}
	
}
