package team.brick.shootem.game.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *	A Tile is the basic building block of each World. 
 * 	
 *	@author 
 *	@version 1.0
 *	@since version 1.0
 */
public class Tile {
	
	//STATIC STUFF HERE
	
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0);
	public static Tile dirtTile = new DirtTile(1);
	public static Tile rockTile = new RockTile(2);
	public static Tile playerSpawnTile = new pSpawnTile(3);
	public static Tile enemySpawnTile = new eSpawnTile(4);
	
	//CLASS
	
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
	
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id){
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick(){
		
	}
	
	/**
	 * Renders the Tile at the given position (x,y)
	 */
	public void render(Graphics g, int x, int y){
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	/**
	 * @return false indicating a solid tile.
	 */
	public boolean isSolid(){
		return false;
	}
	
	/**
	 * Checks if this tile is a player spawn Tile
	 * @return true if it IS a player spawn Tile
	 * @return false if it IS NOT a player spawn Tile
	 */
	public boolean isPSpawn(){
		if(this.getId() == playerSpawnTile.getId())
			return true;
		else
			return false;
	}
	
	/**
	 * Checks if this Tile is an Enemy spawn Tile
	 * @return true if it IS an Enemy spawn Tile
	 * @return false if it IS Not an Enemy Spawn Tile
	 */
	public boolean isESpawn(){
		if(this.getId() == enemySpawnTile.getId())
			return true;
		else
			return false;
	}
	
	/**
	 * @return id the unique id of a given Tile.
	 */
	public int getId(){
		return id;
	}
	
}
