package team.brick.shootem.game.entities.creatures.enemies;

import java.awt.Color;
import java.awt.Graphics;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.entities.creatures.Creature;
import team.brick.shootem.game.gfx.Assets;
import team.brick.shootem.game.sound.Sound;

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
			xMove = speed;
		}else if (handler.getWorld().getEntityManager().getPlayer().getX() < x){
			xMove = -speed;
		}else {
			xMove =0;
		}
		
		if(handler.getWorld().getEntityManager().getPlayer().getY() > y){
			yMove = speed;
		}else if (handler.getWorld().getEntityManager().getPlayer().getY() < y){
			yMove = -speed;
		}else {
			yMove =0;
		}
		
	}
	
	public void attack(){
		if(intersectWithPlayer()){
			handler.getWorld().getEntityManager().getPlayer().hurt(5);
			this.hurt(this.health);
		}
	}
	
	
	@Override
	public void render(Graphics g) {
		posX = (int)(x - handler.getGameCamera().getxOffset());
		posY = (int) (y - handler.getGameCamera().getyOffset());
		/*g.setColor(Color.blue);
		g.drawRect(posX, posY, width, height);*/
		g.drawImage(Assets.interceptor, posX, posY, width, height, null);
		drawHealthBar(posX, posY, width, height, Creature.DEFAULT_HEALTH, health, 10,1, g);
	}

	@Override
	public void die() {
		// An Interceptor death adds 100 to the player score
		handler.getWorld().getEntityManager().getPlayer().addScore(50);
		Sound.explosion.execute();//New jon edit
	}

}
