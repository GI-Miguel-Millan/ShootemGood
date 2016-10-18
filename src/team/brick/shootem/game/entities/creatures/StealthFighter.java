package team.brick.shootem.game.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.gfx.Assets;
import team.brick.shootem.game.utils.Utils;

/**
 *	A StealthFigher is an enemy which is harder to see, and prone to sneak attacks.
 * 	
 *	@author Miguel Millan
 *	@version 1.0
 *	@since version 1.0
 */
public class StealthFighter extends Enemy{

	private boolean isStealthed = true;
	private boolean isAttacking = false;
	private int stealthBreakDistance = 250;
	private int holdDistance = 100;
	private int playerY;
	private int playerX;
	
	public StealthFighter(Handler handler, float x, float y) {
		super(handler, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick(){
		if (isOnScreen()){
			playerX = (int)handler.getWorld().getEntityManager().getPlayer().getX();
			playerY = (int)handler.getWorld().getEntityManager().getPlayer().getY();
			//Set x and y movement values
			AIMove();
			
			//Movement of the enemy
			move();
			
			//Attack of the enemy
			if(isAttacking)
				attack();
		}
		
	}

	private boolean canMove(){
		int holdX1 = (int)x + holdDistance;
		int sBreakX1 = (int)x + stealthBreakDistance;
		int holdY1 = (int)y + holdDistance;
		int sBreakY1 = (int)y + stealthBreakDistance;
		int holdX2 = (int)x - holdDistance;
		int sBreakX2 = (int)x - stealthBreakDistance;
		int holdY2 = (int)y - holdDistance;
		int sBreakY2 = (int)y - stealthBreakDistance;
		
		if((playerX > holdX1) && (playerX < sBreakX1)){
			return true;
		}
		if((playerX < holdX2) && (playerX > sBreakX2)){
			return true;
		}
		if((playerY > holdY1) && (playerY < sBreakY1)){
			return true;
		}
		
		if((playerY < holdY2) && (playerY > sBreakY2)){
			System.out.println(true);
			return true;
			
		}
		
		return false;
		
	}
	
	public void AIMove(){
		if((x <= playerX + stealthBreakDistance || x > playerX - stealthBreakDistance) && 
				 (y >= playerY + stealthBreakDistance || y < playerY - stealthBreakDistance)){
			isStealthed = true;
			isAttacking = false;
		}else{
			
			isStealthed = false;
			isAttacking = true;
		}
		//System.out.println("X: " + x + ", Px: " + playerX + ", Px + Dis: " + (playerX + holdDistance));
		
		if(isStealthed || !canMove()){
			xMove = 0;
			yMove = 0;
		}else{
			if(counter % 2 == 0){
				if(handler.getWorld().getEntityManager().getPlayer().getX() > x){
				xMove = 1;
				}else if (handler.getWorld().getEntityManager().getPlayer().getX() < x){
					xMove = -1;
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
			
				
			
			
		}
		counter += Utils.randomNum(0, 3);
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
		g.setColor(Color.DARK_GRAY);
		
		if(isStealthed)
			g.drawRect(posX, posY, width, height);
		else
			g.drawImage(Assets.stealth, posX, posY, width, height, null);
		
	}

	@Override
	public void die() {
		// A StealthFighter awards 150 score to the player upon death.
		handler.getWorld().getEntityManager().getPlayer().addScore(150);
		
	}
}
