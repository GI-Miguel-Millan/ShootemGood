package team.brick.shootem.game.states;

import java.awt.Color;
import java.awt.Graphics;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.worlds.World;
import team.brick.shootem.game.ui.ScoreCounter;

/**
 *	All game-play is done in the chosen world, during the GameState.
 * 	
 *	@author 
 *	@version 1.0
 *	@since version 1.0
 */
public class GameState extends State {
	
	private World world;
	private ScoreCounter scoreCounter; //Creates a variable using the s
	
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
		scoreCounter.addToScore(50);
		String score = String.valueOf(scoreCounter.getScore());
		g.drawString(score, 240, 740);
		g.drawString("LIVES", 450, 740);	//Lives placeholder
		g.drawString("LEVEL", 25, 740);		//Level number placeholder
	}

}
