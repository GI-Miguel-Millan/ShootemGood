package team.brick.shootem.game.gfx;

import java.awt.image.BufferedImage;

import resources.ResourceLoader;

/**
 *	Assets stores all the assets for the game.
 * 	
 *	@author 
 *	@version 1.0
 *	@since version 1.0
 */
public class Assets {
	
	//Dimensions of tiles in the SpriteSheet
	private static final int width = 32, height = 32;
	
	// Images that will be loaded into the game.
	public static BufferedImage dirt, grass, stone, tree, rock;
	public static BufferedImage[] player_down, player_up, player_left, player_right;
	public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right;
	public static BufferedImage[] btn_start;
	
	//Array of names stored in the Paths.txt of the files we want to load
	public static String[] fileNames; // [0] = "sheets.png" 
								      // [1] = "world1.txt"

	/**
	 *  Loads all assets into the game. This should only be called once.
	 */
	public static void init(){
		
		String regex = "\\s+";
		String pathsFile = ResourceLoader.loadFileAsString("Paths.txt");
		//Splits up each file name into the fileNames array
		fileNames = pathsFile.split(regex);
		
		SpriteSheet sheet = new SpriteSheet(ResourceLoader.loadImage(fileNames[0]));
		
		btn_start = new BufferedImage[2];
		btn_start[0] = sheet.crop(width * 6, height * 4, width * 2, height);
		btn_start[1] = sheet.crop(width * 6, height * 5, width * 2, height);
		
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_right = new BufferedImage[2];
		
		player_down[0] = sheet.crop(width * 4, 0, width, height);
		player_down[1] = sheet.crop(width * 5, 0, width, height);
		player_up[0] = sheet.crop(width * 6, 0, width, height);
		player_up[1] = sheet.crop(width * 7, 0, width, height);
		player_right[0] = sheet.crop(width * 4, height, width, height);
		player_right[1] = sheet.crop(width * 5, height, width, height);
		player_left[0] = sheet.crop(width * 6, height, width, height);
		player_left[1] = sheet.crop(width * 7, height, width, height);
		
		zombie_down = new BufferedImage[2];
		zombie_up = new BufferedImage[2];
		zombie_left = new BufferedImage[2];
		zombie_right = new BufferedImage[2];
		
		zombie_down[0] = sheet.crop(width * 4, height * 2, width, height);
		zombie_down[1] = sheet.crop(width * 5, height * 2, width, height);
		zombie_up[0] = sheet.crop(width * 6, height * 2, width, height);
		zombie_up[1] = sheet.crop(width * 7, height * 2, width, height);
		zombie_right[0] = sheet.crop(width * 4, height * 3, width, height);
		zombie_right[1] = sheet.crop(width * 5, height * 3, width, height);
		zombie_left[0] = sheet.crop(width * 6, height * 3, width, height);
		zombie_left[1] = sheet.crop(width * 7, height * 3, width, height);
		
		dirt = sheet.crop(width, 0, width, height);
		grass = sheet.crop(width * 2, 0, width, height);
		stone = sheet.crop(width * 3, 0, width, height);
		tree = sheet.crop(0, 0, width, height * 2);
		rock = sheet.crop(0, height * 2, width, height);
	}
	
}
