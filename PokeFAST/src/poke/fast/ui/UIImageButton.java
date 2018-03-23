package poke.fast.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {
	
	//For the definition of the click event
	private ClickListener clickListener;
	
	//The images that are necessary such as normal and hovered
	private BufferedImage[] images;
	
	public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clickListener) {
		super(x, y, width, height);
		this.clickListener = clickListener;
		this.images = images; 
	}

	//The click event will be defined at the time of the object's instantiation
	public void onClick() {
		clickListener.onClick();
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		if (hovering)
			g.drawImage(images[1], (int) x, (int) y, width, height, null);
		else
			g.drawImage(images[0], (int) x, (int) y, width, height, null);
	}

}
