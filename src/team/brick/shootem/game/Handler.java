package team.brick.shootem.game;

import team.brick.shootem.game.gfx.GameCamera;
import team.brick.shootem.game.input.KeyManager;
import team.brick.shootem.game.input.MouseManager;
import team.brick.shootem.game.worlds.World;

/**
 *	Handler stores references to objects which need to be
 *  referenced throughout the game. 
 * 	
 *	@author 
 *	@version 1.0
 *	@since version 1.0
 */
public class Handler {
	
	private Game game;
	private World world;
	private int playerScore;
	private int playerHealth;
	
	public Handler(Game game){
		this.game = game;
	}
	
	/**
	 * @return the gameCamera object stored in game.
	 */
	public GameCamera getGameCamera(){
		return game.getGameCamera();
	}
	
	/**
	 * @return the KeyManager stored in game.
	 */
	public KeyManager getKeyManager(){
		return game.getKeyManager();
	}
	
	/**
	 * @return the mouseManager stored in game.
	 */
	public MouseManager getMouseManager(){
		return game.getMouseManager();
	}
	
	/**
	 * @return the width of the game.
	 */
	public int getWidth(){
		return game.getWidth();
	}
	
	/**
	 * @return the height of the game.
	 */
	public int getHeight(){
		return game.getHeight();
	}

	/**
	 * @return the game 
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * @param game 
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * @return the current world
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * @param world the new world 
	 */
	public void setWorld(World world) {
		this.world = world;
	}
	
	/**
	 * @param score the new score
	 */
	public void setPlayerScore(int score){
		playerScore = score;
	}
	
	public int getPlayerScore(){
		return playerScore;
	}

	public void setPlayerHealth(int hp){
		playerHealth = hp;
	}
	
	public int getPlayerHealth(){
		return playerHealth;
	}
}
