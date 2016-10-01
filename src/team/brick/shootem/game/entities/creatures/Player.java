package team.brick.shootem.game.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.gfx.Animation;
import team.brick.shootem.game.gfx.Assets;

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
	private Animation animDown, animUp, animLeft, animRight;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 22;
		bounds.y = 44;
		bounds.width = 19;
		bounds.height = 19;
		
		//Animatons
		animDown = new Animation(500, Assets.player_down);
		animUp = new Animation(500, Assets.player_up);
		animLeft = new Animation(500, Assets.player_left);
		animRight = new Animation(500, Assets.player_right);
	}

	@Override
	public void tick() {
		//Animations
		animDown.tick();
		animUp.tick();
		animRight.tick();
		animLeft.tick();
		//Movement
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
	}
	
	/**
	 *  Gets input from the user and sets the players yMove and
	 *  xMove according to which key is pressed.
	 */
	private void getInput(){
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		
//		g.setColor(Color.red);
//		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//				(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//				bounds.width, bounds.height);
	}
	
	/**
	 *  @return the current animation frame based on which direction the player moves.
	 */
	private BufferedImage getCurrentAnimationFrame(){
		if(xMove < 0){
			return animLeft.getCurrentFrame();
		}else if(xMove > 0){
			return animRight.getCurrentFrame();
		}else if(yMove < 0){
			return animUp.getCurrentFrame();
		}else{
			return animDown.getCurrentFrame();
		}
	}

}
