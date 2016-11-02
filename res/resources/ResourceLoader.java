package resources;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;


public class ResourceLoader {
	static ResourceLoader rl = new ResourceLoader();
	
	/**
	 * Loads the specified image from the textures folder relative to the ResourceLoader.
	 * 
	 * @param fileName the name of the image to be loaded
	 * @return a buffered image of the specified image
	 * @return null if the image could not be found at the path.
	 */
	public static BufferedImage loadImage(String fileName){
		try {
			//returns the buffered image located at the specified path
			return ImageIO.read(rl.getClass().getResource("textures/" + fileName));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	/**
	 * Loads the specified world file in as a string. This file needs to be a text file.
	 * 
	 * @param fileName
	 * @return String of 
	 */
	public static String loadWorldFile(String fileName){
		StringBuilder builder = new StringBuilder();
		
		try{
			BufferedReader br = new BufferedReader(new 
					InputStreamReader(rl.getClass().getResource("worlds/" + fileName).openStream()));
			String line;
			while((line = br.readLine()) != null)
				builder.append(line + "\n");
			
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return builder.toString();
	}
	
	/**
	 * Loads a file from the files folder into the game as a string. This is primarily used for loading
	 * pre-made worlds into the game.
	 * 
	 * @param path the location of the target file to be loaded
	 * @return a string containing the information of the file.
	 */
	public static String loadFileAsString(String fileName){
		StringBuilder builder = new StringBuilder();
		
		try{
			BufferedReader br = new BufferedReader(new 
					InputStreamReader(rl.getClass().getResource("files/" + fileName).openStream()));
			String line;
			while((line = br.readLine()) != null)
				builder.append(line + "\n");
			
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return builder.toString();
	}
	
	public static String loadSounds(String fileName){
		
		return "/resources/sounds/" + fileName;
	}
}
