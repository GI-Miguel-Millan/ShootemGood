package team.brick.shootem.game.states;

import java.awt.Color;
import java.awt.Graphics;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.entities.creatures.Player;
import team.brick.shootem.game.gfx.Assets;
import team.brick.shootem.game.worlds.World;

/**
 *	All gameplay is done in the chosen world, during the GameState.
 * 	
 *	@author 
 *	@version 1.0
 *	@since version 1.0
 */
public class GameState extends State {
	
	private World world;
	public GameState(Handler handler){
		super(handler);
		displayState();
	}
	
	@Override
	public void tick() {
		if (world != handler.getWorld())
			world = handler.getWorld();
		world.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		String tmpScore = "SCORE: " + handler.getPlayerScore();
		String tmpHealth = "Health: " + handler.getPlayerHealth();
		String tmpHighScore = "HighScore: " + handler.getHighScore();
		String level = "Level: " + handler.getPlayer().getLvlCounter();
		
		//UI placeholders
//		g.setColor(Color.black);
//		g.drawRect(0, 0, 500, 30);
		g.setColor(Color.red);
		g.drawString(tmpScore , 260, 20);	//Score placeholder
		g.drawString(tmpHealth, 410, 20);	//Lives placeholder
		g.drawString(level, 25, 20);		//Level number placeholder
		g.drawString(tmpHighScore, 120, 20);
		
		if(handler.getGame().getPAUSED()){
			g.drawImage(Assets.paused, 0, 0, null);
		}
		
	}
	/**
	 * Sets the state to the game state 
	 * and starts the game over at level 1
	 */
	public void displayState(){
		State.setState(handler.getGame().getGameState());
		handler.setPlayer(new Player(handler, 100, 100));
		world = new World(handler, Assets.fileNames[1]); // fileNames[1] = world1.txt
		
		handler.setWorld(world);
	}
}
