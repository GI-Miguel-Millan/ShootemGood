package team.brick.shootem.game.gfx;

import java.awt.Image;
import java.awt.image.BufferedImage;

import resources.ResourceLoader;

/**
 *	Assets loads in and stores all the assets for the game.
 * 	
 *	@author 
 *	@version 1.0
 *	@since version 1.0
 */
public class Assets {
	
	//Dimensions of tiles in the SpriteSheet
	private static final int width = 64, height = 64;
	
	// Images that will be loaded into the game.
	public static BufferedImage space, space2, stone, tree, spaceBound, stealth, 
								projectile, interceptor, assault, stealthed, giantHead,
								motherShip,darkTumorRang, fireBall, eagle, darkLaser, menu;
	public static BufferedImage[] player_down, player_up, player_left, player_right;
	public static BufferedImage[] hurt_down, hurt_up, hurt_left, hurt_right;
	public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right;
	public static BufferedImage[] btn_start;
	
	//Array of names stored in the Paths.txt of the files we want to load
	public static String[] fileNames; 	// [0] = "sheets.png" 
								      	// [1] = "world1.txt"
									  	// [2] = "world2.txt"
									  	// [3] = "world3.txt"
										// [4] = "world4.txt"
										// [5] = "world5.txt"
										// [6] = "background.au"
	  									// [7] = "explosion.au"
									  	// [8] = "hawkscreech.au"
										// [9] = "lazer-sound.au"
										// [10] = "victory.au"
										// [11] = "world0.txt"
										// [12] = "menu_graphic.png"

	/**
	 *  Loads all assets into the game. This should only be called once.
	 */
	public static void init(){
		
		String regex = "\\s+";
		String pathsFile = ResourceLoader.loadFileAsString("Paths.txt");
		//Splits up each file name into the fileNames array
		fileNames = pathsFile.split(regex);
		
//		for(String i: fileNames)
//			System.out.println(i);
		//System.out.println("/resources/sounds/" + ResourceLoader.loadSounds(fileNames[6]));
		
		SpriteSheet sheet = new SpriteSheet(ResourceLoader.loadImage(fileNames[0]));
		menu = ResourceLoader.loadImage(fileNames[12]);
		
		btn_start = new BufferedImage[2];
		btn_start[0] = sheet.crop(width * 6, height * 4, width * 2, height);
		btn_start[1] = sheet.crop(width * 6, height * 5, width * 2, height);
		
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_right = new BufferedImage[2];
		hurt_down = new BufferedImage[2];
		hurt_up = new BufferedImage[2];
		hurt_left = new BufferedImage[2];
		hurt_right = new BufferedImage[2];
		
		player_down[0] = sheet.crop(width * 4, 0, width, height);
		player_down[1] = sheet.crop(width * 5, 0, width, height);
		player_up[0] = sheet.crop(width * 6, 0, width, height);
		player_up[1] = sheet.crop(width * 7, 0, width, height);
		player_right[0] = sheet.crop(width * 4, height, width, height);
		player_right[1] = sheet.crop(width * 5, height, width, height);
		player_left[0] = sheet.crop(width * 6, height, width, height);
		player_left[1] = sheet.crop(width * 7, height, width, height);
		
		hurt_down[0] = sheet.crop(width * 4, 0, width, height);
		hurt_down[1] = sheet.crop(0, height * 6, width, height);
		hurt_up[0] = sheet.crop(width * 6, 0, width, height);
		hurt_up[1] = sheet.crop(0, height * 6, width, height);
		hurt_right[0] = sheet.crop(width * 4, height, width, height);
		hurt_right[1] = sheet.crop(0, height * 6, width, height);
		hurt_left[0] = sheet.crop(width * 6, height, width, height);
		hurt_left[1] = sheet.crop(0, height * 6, width, height);
		
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
		
		space = sheet.crop(width, 0, width, height);
		space2 = sheet.crop(width * 2, 0, width, height);
		stone = sheet.crop(width * 3, 0, width, height);
		tree = sheet.crop(0, 0, width, height * 2);
		spaceBound = sheet.crop(0, height * 2, width, height);
		stealth = sheet.crop(width, height, width, height);
		projectile = sheet.crop(width, height * 2, width, height);
		interceptor = sheet.crop(width * 2, height, width, height * 2);
		assault = sheet.crop(width * 3, height, width, height);
		stealthed = sheet.crop(width * 3, height * 2, width, height);
		giantHead = sheet.crop(0, height*3, width, height);
		motherShip = sheet.crop(0, height * 4, width * 2, height * 2);
		darkTumorRang = sheet.crop(width * 2, height * 3, width, height);
		fireBall = sheet.crop(width * 3, height * 3, width, height);
		darkLaser = sheet.crop(width, height * 3, width, height);
		eagle = sheet.crop(width * 4, height * 4, width* 2, height* 2);
		//test comment
		
	}
	
}
