package team.brick.shootem.game.states;

import java.awt.Color;
import java.awt.Graphics;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.worlds.World;

/**
 *	All game-play is done in the chosen world, during the GameState.
 * 	
 *	@author 
 *	@version 1.0
 *	@since version 1.0
 */
public class GameState extends State {
	
	private World world;
	
	public GameState(Handler handler){
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
	}
	
	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		
		//UI placeholders
		g.setColor(Color.green);
		g.fillRect(0, 720, 500, 30);
		g.setColor(Color.black);
		g.drawString("SCORE", 240, 740);	//Score placeholder
		g.drawString("LIFES", 450, 740);	//Lifes placeholder
		g.drawString("LEVEL", 25, 740);		//Level number placeholder
	}

}
