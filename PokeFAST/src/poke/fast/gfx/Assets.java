package poke.fast.gfx;

import java.awt.image.BufferedImage;

//This will be used to load all the graphical assets. It will be mostly static so it can be accessed directly through the class
public class Assets {
	public static final int width = 32, height = 32; // This will be used for most of the objects
	
	//GameItems
	public static BufferedImage player, grass, rock, dirt, tree;
	
	public static BufferedImage[] player_still, player_down, player_up, player_left, player_right;
	public static BufferedImage[] teacher_still, teacher_down, teacher_up, teacher_left, teacher_right;
	public static BufferedImage[] senior_still, senior_down, senior_up, senior_left, senior_right;
	
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
		SpriteSheet characterSheet = new SpriteSheet(ImageLoader.loadImage("/textures/character_sheet.png"));
		
		
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
		
		teacher_still = new BufferedImage[4];
		teacher_up = new BufferedImage[3];
		teacher_down = new BufferedImage[3];
		teacher_left = new BufferedImage[3];
		teacher_right = new BufferedImage[3];
		
		senior_still = new BufferedImage[4];
		senior_up = new BufferedImage[3];
		senior_down = new BufferedImage[3];
		senior_left = new BufferedImage[3];
		senior_right = new BufferedImage[3];
		
		
		int i;
		for(i=0;i<3;i++) {
			
			player_still[i] = characterSheet.crop(0,  height*i, width, height);
			teacher_still[i] = characterSheet.crop(width*9, height*i, width, height);
			senior_still[i] = characterSheet.crop(width*6, height*(i+4), width, height);
			
			player_down[i] = characterSheet.crop(width*(i), 0, width, height);
			player_left[i] = characterSheet.crop(width*(i), height, width, height);
			player_right[i] = characterSheet.crop(width*(i), height*2, width, height);
			player_up[i] = characterSheet.crop(width*(i), height*3, width, height);
			
			teacher_down[i] = characterSheet.crop(width*(i+9), 0, width, height);
			teacher_left[i] = characterSheet.crop(width*(i+9), height, width, height);
			teacher_right[i] = characterSheet.crop(width*(i+9), height*2, width, height);
			teacher_up[i] = characterSheet.crop(width*(i+9), height*3, width, height);
			
			senior_down[i] = characterSheet.crop(width*(i+6), 0, width, height);
			senior_left[i] = characterSheet.crop(width*(i+6), height, width, height);
			senior_right[i] = characterSheet.crop(width*(i+6), height*2, width, height);
			senior_up[i] = characterSheet.crop(width*(i+6), height*3, width, height);
			
		}
		player_still[i] = characterSheet.crop(0,  height*i, width, height);
		teacher_still[i] = characterSheet.crop(width*9, height*i, width, height);
		senior_still[i] = characterSheet.crop(width*6, height*(i+4), width, height);
		
		grass = boxSheet.crop(width, 0, width, height);	
		rock = boxSheet.crop(width*2, height*3, width, height);
		dirt = boxSheet.crop(0, height*3, width, height);
		tree = boxSheet.crop(width, height, width, height);
		
	}
	
}
