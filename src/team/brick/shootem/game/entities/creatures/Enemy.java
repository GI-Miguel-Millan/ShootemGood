package team.brick.shootem.game.entities.creatures;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.utils.Utils;

/**
 *	Enemies are creatures controlled by the system and not the user. There are 
 *	different types of Enemies.
 * 	
 *	@author Miguel Millan
 *	@version 1.0
 *	@since version 1.0
 */
public abstract class Enemy extends Creature{
	protected int counter =0;
	protected int readyCount = 0;
	protected boolean ready = true;
	
	public Enemy(Handler handler, float x, float y) {
		super(handler, x, y, 30, 50);
		
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
	
	// This method should be overwritten in child classes
	// Default is movement from left to right in random time intervals.
	public void AIMove(){
		if(counter <50){
			xMove = 5;
		}else if(counter < 100){
			xMove = -5;
		}else{
			counter = 0;
		}
		counter += Utils.randomNum(0, 3);
	}
	
	// This method should be overwritten in child classes
	// Default is a random projectile spawn.
	public void attack(){
		int randAttack = Utils.randomNum(0, 50);
		if(randAttack == 0){
			handler.getWorld().getEntityManager().addEntity(new Projectile(handler, 
					(int) ((x) + width/2), 
					(int) (y + height + 6), 1));
		}
		
		//Deals damage to the player if they intersect this Enemy
		if(intersectWithPlayer() && ready){
			handler.getWorld().getEntityManager().getPlayer().hurt(1);
			ready = false;
		}
		
		if (!ready){
			readyCount++;
		}
		
		if(readyCount >= 5){
			ready = true;
			readyCount =0;
		}
	}
	
	public boolean isOnScreen(){
		if (y >= (((handler.getGameCamera().getyOffset() - height))) 
				&& y < (((handler.getGameCamera().getyOffset() + handler.getGame().getHeight())))){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * @return true if this enemy intersects with the player.
	 */
	public boolean intersectWithPlayer(){
		if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xMove, yMove))){
			return true;
		}
		
		return false;
	}
}
