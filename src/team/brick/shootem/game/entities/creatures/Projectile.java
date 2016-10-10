package team.brick.shootem.game.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.entities.Entity;
import team.brick.shootem.game.tiles.Tile;

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
	private int orientation; // 0 = up, 1 = down
	private int counter = 0;
	
	public Projectile(Handler handler, float x, float y, int orient) {
		super(handler, x, y, DEFAULT_PROJECTILE_WIDTH, DEFAULT_PROJECTILE_HEIGHT);
		orientation = orient;
		speed = 6.0f;
		health = 1;
		if(orientation == 0)
			yMove = speed;
		else
			yMove = -speed;
		
		
		
	}

	@Override
	public void tick() {
		//Ensures that a projectile is eventually killed
		if(counter == 100)
			this.hurt(1);
		
		if(!checkEntityCollisions(0f, yMove)){
			y -= yMove;
		}else{
			checkAttack();
		}
		
		counter++;
		
	}
	
	public void checkAttack(){
		for(Entity e: handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0, 0).intersects(getCollisionBounds(0,yMove))){
			
				if(orientation == 1 && e.equals(handler.getWorld().getEntityManager().getPlayer())){
					e.hurt(1);
				}else if(orientation == 1){
					this.hurt(1);
					return;
				}
					
				
				if(orientation == 0)
					e.hurt(1);
			}
		}
		this.hurt(1);
	}

	@Override
	public void render(Graphics g) {
		posX = (int)(x - handler.getGameCamera().getxOffset());
		posY = (int) (y - handler.getGameCamera().getyOffset());
		g.setColor(Color.green);
		g.drawRect(posX, posY, width, height);
		
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

}
