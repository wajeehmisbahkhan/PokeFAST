package poke.fast.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

//This class will handle the loading of all images
public class ImageLoader {
	
	//Using the path, return the loaded image
	public static BufferedImage loadImage (String path) {
		try { //Load Image
			return ImageIO.read(ImageLoader.class.getResource(path)); //We have set the res folder as a class file of the project
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null; //Required by Java
	}
}
