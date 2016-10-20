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
		
		//UI placeholders
		g.setColor(Color.green);
		g.fillRect(0, 720, 500, 30);
		g.setColor(Color.black);
		g.drawString(tmpScore , 240, 740);	//Score placeholder
		g.drawString(tmpHealth, 450, 740);	//Lives placeholder
		g.drawString("LEVEL: 1", 25, 740);		//Level number placeholder
	}
	
	public void displayState(){
		State.setState(handler.getGame().gameState);
		handler.setPlayer(new Player(handler, 100, 100));
		world = new World(handler, Assets.fileNames[1]); // fileNames[1] = world1.txt
		
		handler.setWorld(world);
	}
}
