package team.brick.shootem.game.worlds;

import java.awt.Graphics;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.entities.EntityManager;
import team.brick.shootem.game.entities.creatures.Interceptor;
import team.brick.shootem.game.entities.creatures.Enemy;
import team.brick.shootem.game.entities.creatures.Player;
import team.brick.shootem.game.entities.statics.Tree;
import team.brick.shootem.game.tiles.Tile;
import team.brick.shootem.game.utils.Utils;

/**
 *	A world is made of Tiles and Entities. A world is what the player
 *	sees and interacts with to play the game.
 * 	
 *	@author 
 *	@version 1.0
 *	@since version 1.0
 */
public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	//Entities
	private EntityManager entityManager;
	
	public World(Handler handler, String path){
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		// Temporary entity code!
		//entityManager.addEntity(new Interceptor(handler, 100, 750));
		
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}
	
	/**
	 *  Calls the tick() of the entityManager, 
	 *  resulting in the tick() of all Entities.
	 */
	public void tick(){
		entityManager.tick();
	}
	
	/**
	 * Renders all entities and all visible portions of the world.
	 * 
	 * @param g
	 */
	public void render(Graphics g){
		//Ensures efficient rendering of the world by only rendering
		//what is currently visible.
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		for(int y = yStart;y < yEnd;y++){
			for(int x = xStart;x < xEnd;x++){
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		//Entities
		entityManager.render(g);
	}
	
	/**
	 * Gets a Tile at the specified position (x,y).
	 * 
	 * @param x the x position of the Tile
	 * @param y the y position of the Tile
	 * @return t the Tile at position (x,y)
	 * @return Tile.grassTile if (x,y) is out of bounds
	 */
	public Tile getTile(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.dirtTile;
		return t;
	}
	
	/**
	 * Loads the world from a file at the specified location.
	 * 
	 * @param path the location of the world file.
	 */
	private void loadWorld(String path){
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0;y < height;y++){
			for(int x = 0;x < width;x++){
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
				
				if(getTile(x,y).isPSpawn()){
					System.out.println("Hi");
					spawnX = x * Tile.TILEWIDTH;
					spawnY = (y-1) * Tile.TILEHEIGHT;
				}else if(getTile(x,y).isESpawn()){
					int randomSpawn = Utils.randomNum(1,3);
					entityManager.spawnEnemy(handler, x * Tile.TILEWIDTH, (y) * Tile.TILEHEIGHT, randomSpawn);
				}
						
			}
		}
		
		
	}
	
	
	
	/**
	 * @return width the width of the world
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * @return height the height of the world
	 */
	public int getHeight(){
		return height;
	}

	/**
	 * @return entityManager the EntityManager of the world
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
}








