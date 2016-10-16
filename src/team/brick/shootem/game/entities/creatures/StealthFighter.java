package team.brick.shootem.game.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.utils.Utils;

/**
 *	A StealthFigher is an enemy which is harder to see, and prone to sneak attacks.
 * 	
 *	@author Miguel Millan
 *	@version 1.0
 *	@since version 1.0
 */
public class StealthFighter extends Enemy{

	
	public StealthFighter(Handler handler, float x, float y) {
		super(handler, x, y);
		// TODO Auto-generated constructor stub
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

	
	public void attack(){
		int randAttack = Utils.randomNum(0, 50);
		if(randAttack == 0){
			handler.getWorld().getEntityManager().addEntity(new Projectile(handler, 
					(int) ((x) + width/2), 
					(int) (y + height + 6), 1));
		}
		
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
	
	@Override
	public void render(Graphics g) {
		posX = (int)(x - handler.getGameCamera().getxOffset());
		posY = (int) (y - handler.getGameCamera().getyOffset());
		g.setColor(Color.red);
		g.drawRect(posX, posY, width, height);
		
	}

	@Override
	public void die() {
		// A StealthFighter awards 150 score to the player upon death.
		handler.getWorld().getEntityManager().getPlayer().addScore(150);
		
	}
}
