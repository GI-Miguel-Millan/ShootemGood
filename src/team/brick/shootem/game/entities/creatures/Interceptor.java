package team.brick.shootem.game.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.gfx.Assets;

/**
 *	An Interceptor is an Enemy which tracks down the player
 *	and suicides on them, causing damage.
 * 	
 *	@author Miguel Millan
 *	@version 1.0
 *	@since version 1.0
 */
public class Interceptor extends Enemy{

	public Interceptor(Handler handler, float x, float y) {
		
		super(handler, x, y);
		
		
	}
	
	@Override
	public void tick(){
		if (isOnScreen()){
			//Set x and y movement values
			AIMove();
			
			//Movement of the enemy
			move();
			
			//Attack of the enemy
			attack();
		}
		
	}
	
	public void AIMove(){
		if(handler.getWorld().getEntityManager().getPlayer().getX() > x){
			xMove = 2;
		}else if (handler.getWorld().getEntityManager().getPlayer().getX() < x){
			xMove = -2;
		}else {
			xMove =0;
		}
		
		if(handler.getWorld().getEntityManager().getPlayer().getY() > y){
			yMove = 1;
		}else if (handler.getWorld().getEntityManager().getPlayer().getY() < y){
			yMove = -2;
		}else {
			yMove =0;
		}
		
	}
	
	public void attack(){
		if(intersectWithPlayer()){
			handler.getWorld().getEntityManager().getPlayer().hurt(1);
			this.hurt(3);
		}
	}
	
	
	@Override
	public void render(Graphics g) {
		posX = (int)(x - handler.getGameCamera().getxOffset());
		posY = (int) (y - handler.getGameCamera().getyOffset());
		/*g.setColor(Color.blue);
		g.drawRect(posX, posY, width, height);*/
		g.drawImage(Assets.assault, posX, posY, width, height, null);
		
	}

	@Override
	public void die() {
		// An Interceptor death adds 100 to the player score
		handler.getWorld().getEntityManager().getPlayer().addScore(100);
		
	}

}
