package poke.fast.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

//This will be used to load all the graphical assets. It will be mostly static so it can be accessed directly through the class
public class Assets {
	public static final int width = 32, height = 32; // This will be used for most of the objects
	
	//Fonts
	public static Font dialogueFont;
	public static Font optionFont;
	
	//Intro
	public static BufferedImage bus_back;	
	
	//GameItems
	public static BufferedImage grass, rock, dirt, cs, shop, shades, teleport, shadow; //Tiles
	public static BufferedImage tree, fountain, bench[], bus, old, young; //Inanimate
	
	public static BufferedImage[] player_still, player_down, player_up, player_left, player_right;
	public static BufferedImage[] teacher_still, teacher_down, teacher_up, teacher_left, teacher_right;
	public static BufferedImage[] senior_still, senior_down, senior_up, senior_left, senior_right;
	
	//The Menu Items
	public static BufferedImage[] btn_start;
	public static BufferedImage menuBg, logo;
	
	//Battle
	public static BufferedImage student, senior, teacher, assignment, battleGround;
	
	//Outro
	public static BufferedImage freshman, sophomore;	
	
	//When the game initializes
	public static void init () {
		//Fonts
		dialogueFont = FontLoader.loadFont("res/fonts/PKMN RBYGSC.ttf", 15);
		optionFont = FontLoader.loadFont("res/fonts/Verdana.ttf", 12);
		
		//Load all the sprite sheets
		SpriteSheet menuSheet = new SpriteSheet(ImageLoader.loadImage("/textures/menu_sheet.png"));
		
		//player and tiles

		SpriteSheet tileSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tiles_sheet.png"));
		SpriteSheet inanimateSheet = new SpriteSheet(ImageLoader.loadImage("/textures/inanimates_sheet.png"));
		SpriteSheet characterSheet = new SpriteSheet(ImageLoader.loadImage("/textures/untitled.png"));
		SpriteSheet newSheet = new SpriteSheet(ImageLoader.loadImage("/textures/player_sheet.png"));
		SpriteSheet battleCharacterSheet = new SpriteSheet(ImageLoader.loadImage("/textures/character_sheet.png"));
		SpriteSheet battleSheet = new SpriteSheet(ImageLoader.loadImage("/textures/battle_sheet.png"));
		
		bus_back = ImageLoader.loadImage("/textures/bus_sheet.jpg");
		
		//Crop the required items
		student = ImageLoader.loadImage("/textures/student.png");
		senior = battleCharacterSheet.crop(0, 0, 80, 80);
		teacher = battleCharacterSheet.crop(80, 0, 80, 80);
		assignment = battleCharacterSheet.crop(160, 0, 80, 80);
		battleGround = battleSheet.crop(0, 0, 256, 144);
		
		freshman = battleCharacterSheet.crop(0, 80, 80, 80);
		sophomore = battleCharacterSheet.crop(80, 80, 80, 80);
		
		
		//Menu
		menuBg = ImageLoader.loadImage("/textures/menu_bg.jpg");
		
		btn_start = new BufferedImage[2];
		btn_start[0] = menuSheet.crop(0, 0, 160, 45);
		btn_start[1] = menuSheet.crop(160, 0, 160, 45);
		
		//Game
		
		player_still = new BufferedImage[4];
		player_up = new BufferedImage[4];
		player_down = new BufferedImage[4];
		player_left = new BufferedImage[4];
		player_right = new BufferedImage[4];
		
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
		
		bench = new BufferedImage[4];
		int i;
		for(i=0;i<3;i++) {
			
			player_still[i] = newSheet.crop(0,  height*i, width, height);
			teacher_still[i] = characterSheet.crop(0, height*i, width, height);
			senior_still[i] = characterSheet.crop(width*1, height*(i+1), width, height);
			
			player_down[i] = newSheet.crop(width*(i), 0, width, height);
			player_left[i] = newSheet.crop(width*(i), height, width, height);
			player_right[i] = newSheet.crop(width*(i), height*2, width, height);
			player_up[i] = newSheet.crop(width*(i), height*3, width, height);
			
			teacher_down[i] = characterSheet.crop(width*(i), 0, width, height);
			teacher_left[i] = characterSheet.crop(width*(i), height, width, height);
			teacher_right[i] = characterSheet.crop(width*(i), height*2, width, height);
			teacher_up[i] = characterSheet.crop(width*(i), height*3, width, height);
			
			senior_down[i] = characterSheet.crop(width*(i+1), 0, width, height);
			senior_left[i] = characterSheet.crop(width*(i+1), height, width, height);
			senior_right[i] = characterSheet.crop(width*(i+1), height*2, width, height);
			senior_up[i] = characterSheet.crop(width*(i+1), height*3, width, height);
			
		}
		player_down[i] = newSheet.crop(width*(i), 0, width, height);
		player_left[i] = newSheet.crop(width*(i), height, width, height);
		player_right[i] = newSheet.crop(width*(i), height*2, width, height);
		player_up[i] = newSheet.crop(width*(i), height*3, width, height);
		player_still[i] = newSheet.crop(0,  height*i, width, height);
		//teacher_still[i] = characterSheet.crop(0, height*i, width, height);
		//senior_still[i] = characterSheet.crop(width, height*(i+1), width, height);
		
		grass = tileSheet.crop(0, 0, width, height);	
		dirt = tileSheet.crop(width, 0, width, height);
		rock = tileSheet.crop(width*2, 0, width, height);
		shades = tileSheet.crop(width*3, 0, width, height);
		cs = tileSheet.crop(0, height, width, height);
		shop = tileSheet.crop(width, height, width, height);
		shadow = tileSheet.crop(width*2, height, width, height);
		teleport = tileSheet.crop(width*3, height, width, height);
		tree = inanimateSheet.crop(0, 0, width, height);
		fountain = inanimateSheet.crop(width*3, 0, width*5, height*3);
		bench[0] = inanimateSheet.crop(width, 0, width*2, height);
		bench[1] = inanimateSheet.crop(width, height, width*2, height);
		bench[2] = inanimateSheet.crop(0, height, width, height*2);
		bench[3] = inanimateSheet.crop(width, height, width*2, height);
		bus = inanimateSheet.crop(0, height*3, width*8, height*3);
		old = inanimateSheet.crop(width, height*2, width, height);
		young = inanimateSheet.crop(width*2, height*2, width, height);

	}
	
}
