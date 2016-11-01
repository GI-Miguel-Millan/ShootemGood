package team.brick.shootem.game.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.entities.creatures.projectiles.*;
import team.brick.shootem.game.gfx.Animation;
import team.brick.shootem.game.gfx.Assets;
import team.brick.shootem.game.sound.Sound;
import team.brick.shootem.game.states.State;
import team.brick.shootem.game.tiles.Tile;
import team.brick.shootem.game.worlds.World;

/**
 *	Player is a Creature controlled by the user. This class takes input from the user
 *	for movement, and is able to shoot projectiles.
 * 	
 *	@author 
 *	@version 1.0
 *	@since version 1.0
 */
public class Player extends Creature {
	
	//Animations
	private Animation animDown, animUp, animLeft, animRight,
						hurtDown, hurtUp, hurtLeft, hurtRight;
	private boolean readyFire;
	private boolean isHurt = false;
	private int counter;
	private int score = 1000;
	private int lvlCounter = 1;
	private int hurtCounter = 0;
	private static int numLevels = 4;
	private boolean isBossDead = false;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 16;
		bounds.y = 22;
		bounds.width = 32;
		bounds.height = 12;
		counter = 0;
		readyFire = true;
		health = 25000;
		handler.setPlayerHealth(health);
		handler.setPlayerScore(score);
		
		//Animatons
		animDown = new Animation(500, Assets.player_down);
		animUp = new Animation(500, Assets.player_up);
		animLeft = new Animation(500, Assets.player_left);
		animRight = new Animation(500, Assets.player_right);
		hurtDown = new Animation(50, Assets.hurt_down);
		hurtUp = new Animation(50, Assets.hurt_up);
		hurtLeft = new Animation(50, Assets.hurt_left);
		hurtRight = new Animation(50, Assets.hurt_right);
	}

	@Override
	public void tick() {
		//Animations
		animDown.tick();
		animUp.tick();
		animRight.tick();
		animLeft.tick();
		hurtDown.tick();
		hurtUp.tick();
		hurtRight.tick();
		hurtLeft.tick();
		
		//System.out.println("px: " + x + ", py: "+ y);
		
		//Movement
		getInput();
		move();
		lowerBoundCheck();
		if(!readyFire)
			counter++;
		
		if(counter == 20){
			readyFire = true;
			counter = 0;
		}
		
		if(isHurt == true)
			hurtCounter++;
		
		if (hurtCounter == 50){
			isHurt = false;
			hurtCounter = 0;
		}
		
		collisionWithGoal((int)x,(int)y);
		
		//handler.getGameCamera().centerOnEntity(this);
		handler.getGameCamera().staticCamera(this);
		
		handler.setPlayerScore(this.score);
		handler.setPlayerHealth(health);
	}
	
	/**
	 * Sets the players y position to the bottom of the game camera 
	 * if the player moves below the screen
	 */
	private void lowerBoundCheck(){
		if(y > (((handler.getGameCamera().getyOffset() + 700)))){
			this.die();
		}
	}
	
	/**
	 *  Gets input from the user and sets the players yMove and
	 *  xMove according to which key is pressed.
	 */
	private void getInput(){
		xMove = 0;
		//yMove = -2;
		yMove = -(handler.getGameCamera().getCamSpeed());
		
		if(handler.getKeyManager().up)
		{
			if (y >= (((handler.getGameCamera().getyOffset() + 1))))
			{
				yMove += -speed;
			}
			else
				yMove += 0;
		}
		
		if(handler.getKeyManager().down)
		{
			if (y < (((handler.getGameCamera().getyOffset() + 650))))
			{	
			yMove += speed;
			}
			else{
				yMove += 0;
			}
				
		}
		
		if(handler.getKeyManager().left)
		{
			xMove = -speed;
		}
			if(handler.getKeyManager().right)
			{
				xMove = speed;
			}		
		// A player is only allowed to fire a projectile whenever readyFire is true 
		// and they hit the fire key.
		if(handler.getKeyManager().fire && readyFire){
			// Spawns a projectile above the player moving upwards

			handler.getWorld().getEntityManager().addEntity(new Projectile(handler, this, 0, -3));
			// Every time a player fires a projectile they lose 10 score (accuracy is important)
			// and their guns go on cooldown (they are not ready to fire).
			score -=10;
			readyFire = false;
		}
	}
	
	/**
	 * Checks if the player is colliding with a Goal Tile.
	 * 
	 * @param x the x position of the Tile
	 * @param y the y position of the Tile
	 * @return true if the Tile is not solid
	 * @return false if the Tile is is solid
	 */
	protected void collisionWithGoal(int x, int y){
		int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
		int tx = (int) (x + bounds.x) / Tile.TILEWIDTH;
		if(handler.getWorld().getTile(tx, ty).isGoal() && isBossDead){
			handler.setPlayerScore(score);
			lvlCounter++;
			handler.getGameCamera().resetCamera();
			System.out.println(isBossDead);
			if (lvlCounter > numLevels){
				//State.setState(handler.getGame().GameOverState);
				handler.checkAndSetHighScore(score);
				handler.getGame().getGameOverState().displayState();
			}else
				handler.setWorld(new World(handler, Assets.fileNames[lvlCounter]));
		}
	}
	

	@Override
	public void render(Graphics g) {
		posX = (int)(x - handler.getGameCamera().getxOffset());
		posY = (int) (y - handler.getGameCamera().getyOffset());
		g.drawImage(getCurrentAnimationFrame(), posX, posY, width, height, null);
		
//		g.drawRect(posX, posY, width, height);
//		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//				(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//				bounds.width, bounds.height);
	}
	
	/**
	 *  @return the current animation frame based on which direction the player moves.
	 */
	private BufferedImage getCurrentAnimationFrame(){
		if(isHurt == false){
			if(xMove < 0){
				return animLeft.getCurrentFrame();
			}else if(xMove > 0){
				return animRight.getCurrentFrame();
			}else if(yMove < 0){
				return animUp.getCurrentFrame();
			}else{
				return animDown.getCurrentFrame();
			}
		}else{
			if(xMove < 0){
				return hurtLeft.getCurrentFrame();
			}else if(xMove > 0){
				return hurtRight.getCurrentFrame();
			}else if(yMove < 0){
				return hurtUp.getCurrentFrame();
			}else{
				return hurtDown.getCurrentFrame();
			}
		}
	}

	@Override
	public void die() {
		handler.getGameCamera().resetCamera();
		handler.checkAndSetHighScore(score);
		handler.getGame().getGameOverState().displayState();
		
	}
	
	
	/**
	 * The hurt method of the Player must be overridden so that 
	 * every time the player takes damage, the handler can update 
	 * the player health.
	 * @Override
	 */
	public void hurt(int amt){
		health -= amt;
		isHurt = true;
		if(health <= 0){
			active = false;
			die();
		}
	}

	
	/**
	 * Add integer to the players score.
	 * @param score
	 */
	public void addScore(int score){
		this.score += score;
	}
	
	/**
	 * 
	 * @return player score
	 */
	public int getScore(){
		return score;
	}
	
	public int getLvlCounter(){
		return lvlCounter;
	}
	
	public void setIsBossDead(boolean bool){
		isBossDead = bool;
	}
	
	public boolean isBossDead(){
		return isBossDead;
	}
	
}
