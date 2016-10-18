package team.brick.shootem.game.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.entities.Entity;
import team.brick.shootem.game.gfx.Assets;

/**
 *	A Projectile is a moving Entity which deals damage to creatures. 
 * 
 *	@author Miguel Millan
 *	@version 1.0
 *	@since version 1.0
 */
public class Projectile extends Creature{

	public static final int DEFAULT_PROJECTILE_WIDTH = 5,
							DEFAULT_PROJECTILE_HEIGHT = 20;
	private int orientation; // 0 = up, 1 = down, 2 = right, 3 = left
	private int counter = 0; 
	
	public Projectile(Handler handler, float x, float y, int orient) {
		super(handler, x, y, DEFAULT_PROJECTILE_WIDTH, DEFAULT_PROJECTILE_HEIGHT);
		orientation = orient;
		speed = 6.0f;
		health = 1;
		
		if(orientation == 0){
			yMove = speed;
		}else if(orientation == 1){
			yMove = -speed;
		}else if(orientation == 2){
			xMove = speed;
			width = DEFAULT_PROJECTILE_HEIGHT;
			height =DEFAULT_PROJECTILE_WIDTH;
		}else{
			xMove = -speed;
			width = DEFAULT_PROJECTILE_HEIGHT;
			height =DEFAULT_PROJECTILE_WIDTH;
		}
		
		
		
	}

	@Override
	public void tick() {
		//Ensures that a projectile is eventually killed
		if(counter == 100)
			this.hurt(1);
		
		if(orientation == 0)
			yMove = speed;
		else if(orientation == 1)
			yMove = -speed;
		else if(orientation == 2)
			xMove = speed;
		else if(orientation == 3)
			xMove = -speed;
			
		
		if(!checkEntityCollisions(xMove, yMove)){
			y -= yMove;
			x += xMove;
		}else{
			checkAttack();
		}
		
		counter++;
		
	}
	
	/**
	 *  A projectile must be able to check if it is going to collide 
	 *  with another entity, and act accordingly.
	 */
	public void checkAttack(){
		for(Entity e: handler.getWorld().getEntityManager().getEntities()){
			//No Need to check for collision if e is this projectile.
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(getCollisionBounds(0,yMove))){
			
				// If this projectile collides with the player hurt it.
				if(e.equals(handler.getWorld().getEntityManager().getPlayer())){
					e.hurt(1);
				}
					
				// If the projectile is firing upward (orientation = 0) 
				// then It will deal damage to an Enemy
				if(orientation == 0 && !e.equals(handler.getWorld().getEntityManager().getPlayer()))
					e.hurt(1);
				
				// Regardless of whether or not the projectile deals damage,
				// if it has collided with an entity it must kill itself.
				this.hurt(1);
			}
		}
	}

	@Override
	public void render(Graphics g) {
		posX = (int)(x - handler.getGameCamera().getxOffset());
		posY = (int) (y - handler.getGameCamera().getyOffset());
		/*g.setColor(Color.blue);
		g.drawRect(posX, posY, width, height);*/
		g.drawImage(Assets.projectile, posX, posY, width, height, null);
		
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

}
