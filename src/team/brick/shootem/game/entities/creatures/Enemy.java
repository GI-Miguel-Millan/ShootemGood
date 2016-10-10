package team.brick.shootem.game.entities.creatures;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.utils.Utils;

/**
 *	Enemies are creatures controlled by the system and not the user. There are 
 *	different types of Enemies, denoted by an integer.
 * 	
 *	@author Miguel Millan
 *	@version 1.0
 *	@since version 1.0
 */
public abstract class Enemy extends Creature{
	
	private int counter =0;
	public Enemy(Handler handler, float x, float y) {
		super(handler, x, y, 30, 50);
		
	}
	
	@Override
	public void tick(){
		//Set x and y movement values
		AIMove();
		
		//Movement of the enemy
		move();
		
		//Attack of the enemy
		attack();
	}
	
	// This method should be overwritten in child classes
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
					(int) ((x + 64) - handler.getGameCamera().getxOffset()), 
					(int) (y - 25), 1));
		}
	}
	
}
