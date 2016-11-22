package team.brick.shootem.game.states;

import java.awt.Color;
import java.awt.Graphics;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.entities.creatures.Player;
import team.brick.shootem.game.gfx.Animation;
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
	private Animation transIn, transOut;
	private boolean tIn = false, tOut = false, stillTransitioning = false;
	
	public GameState(Handler handler){
		super(handler);
		displayState();
		
		transIn = new Animation(100, Assets.transIn);
		transOut = new Animation(200, Assets.transOut);
	}
	
	@Override
	public void tick() {
		if(tIn)
			transIn.tick();
		if(tOut)
			transOut.tick();
		
		if (world != handler.getWorld())
			world = handler.getWorld();
		
		if(handler.getKeyManager().mute){
			handler.setIsTransitioning(true);
			
		}
		if(!handler.IsTransitioning())
			world.tick();
		else
			checkTransition();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		
		if(tIn)
			g.drawImage(transIn.getCurrentFrame(), 0, 0, handler.getWidth(), handler.getHeight(), null);
		if(tOut)
			g.drawImage(transOut.getCurrentFrame(), 0, 0, handler.getWidth(), handler.getHeight(), null);
		
		String tmpScore = "SCORE: " + handler.getPlayerScore();
		String tmpHealth = "Health: " + handler.getPlayerHealth();
		String tmpHighScore = "HighScore: " + handler.getHighScore();
		String level = "Level: " + handler.getLvlCounter();
		
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
	
	public void checkTransition(){
		if(!stillTransitioning){
			tOut = true;
			transitionOut();
		}else{
			tIn = true;
			transitionIn();
		}
	}
	
	public void transitionIn(){
		world.tick();
		if(transIn.onLastFrame()){
			stillTransitioning = false;
			handler.setIsTransitioning(false);
			tIn = false;
		}
	}

	public void transitionOut(){
		if(transOut.onLastFrame()){
			stillTransitioning = true;
			tIn = true;
			tOut = false;
			handler.changeWorld();
			handler.getGameCamera().resetCamera();
			transOut = new Animation(200, Assets.transOut);
		}
		
	}
	
	/**
	 * Sets the state to the game state 
	 * and starts the game over at level 1
	 */
	public void displayState(){
		State.setState(handler.getGame().getGameState());
		handler.setPlayer(new Player(handler, 100, 100));
		handler.setLvlCounter(1);
		world = new World(handler, Assets.fileNames[1]); // fileNames[1] = world1.txt
		handler.setWorld(world);
	}
}
