package poke.fast.gfx;

import java.awt.image.BufferedImage;

//This class will be created to load the full sprite sheets and crop them to pieces
public class SpriteSheet {
	
	private BufferedImage sheet;
	
	public SpriteSheet (BufferedImage sheet) {
		this.sheet = sheet;
	}
	
	//Crops the sheet like a rectangle and returns the buffered image
	public BufferedImage crop (int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}
	
}
