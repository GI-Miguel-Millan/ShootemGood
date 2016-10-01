package team.brick.shootem.game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 *	The ImageLoader loads images into the game.
 * 	
 *	@author 
 *	@version 1.0
 *	@since version 1.0
 */
public class ImageLoader {

	/**
	 * Loads an image from the specified path.
	 * 
	 * @param path the path to the image
	 * @return a buffered image of the specified image
	 * @return null if the image could not be found at the path.
	 */
	public static BufferedImage loadImage(String path){
		try {
			//returns the buffered image located at the specified path
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
}
