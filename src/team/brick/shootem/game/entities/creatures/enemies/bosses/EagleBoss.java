package team.brick.shootem.game.entities.creatures.enemies.bosses;

import java.awt.Color;
import java.awt.Graphics;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.entities.creatures.projectiles.FireBall;
import team.brick.shootem.game.entities.creatures.projectiles.Projectile;
import team.brick.shootem.game.gfx.Assets;
import team.brick.shootem.game.utils.Utils;

public class EagleBoss extends Boss{
	private static final int EAGLE_WIDTH = 96;
	private static final int EAGLE_HEIGHT = 64;
	private int holdDistance = 100;
	private boolean canMove =false;
	private int reverseCounter =0;
	
	public EagleBoss(Handler handler, float x, float y) {
		super(handler, x, y);
		this.width = EAGLE_WIDTH;
		this.height = EAGLE_HEIGHT;
		this.bounds.width = this.width;
		this.bounds.height = this.height;
		this.speed = 3;
	}

	@Override
	public void render(Graphics g) {
		posX = (int)(x - handler.getGameCamera().getxOffset());
		posY = (int) (y - handler.getGameCamera().getyOffset());
		g.setColor(Color.blue);
		//g.drawRect(posX, posY, width, height);
		g.drawImage(Assets.eagle, posX, posY, width, height, null);
//		g.drawLine((int)posX+width/2, (int)posY+height/2, (int)(handler.getPlayer().getX() + handler.getPlayer().getWidth()/2 - handler.getGameCamera().getxOffset())
//				, (int)(handler.getPlayer().getY() + handler.getPlayer().getHeight()/2 - handler.getGameCamera().getyOffset()));
	}
	
	@Override
	public void tick(){
		if (isOnScreen()){
			canMove = true;
		}
		if(canMove){
			playerX = (int)handler.getWorld().getEntityManager().getPlayer().getX();
			playerY = (int)handler.getWorld().getEntityManager().getPlayer().getY();
			
			//Set x and y movement values
			AIMove();
			
			//Movement of the enemy
			move();
			
			//Attack of the enemy
			attack();
		}
		
		
	}
	
	@Override
	public void AIMove(){
		int centeredPlayerX = (int)(playerX + handler.getPlayer().getWidth()/2);
		int centeredPlayerY = (int)(playerY + handler.getPlayer().getHeight()/2);
		int centeredX = (int)(x + width/2);
		int centeredY = (int)(y + height/2);
		
		System.out.println("xMove: " + xMove + ", yMove: " + yMove);
//		if(reverseCounter <= 400){
//				//down right
//			if(centeredPlayerX <= centeredX && centeredPlayerY >= centeredY + holdDistance){
//				moveDownRight();
//				
//				//down left
//			}else if( centeredPlayerX <= centeredX - holdDistance && centeredPlayerY <= centeredY){
//				moveDownLeft();
//				
//				//up left
//			}else if( centeredPlayerX >= centeredX && centeredPlayerY <= centeredY - holdDistance){
//				moveUpLeft();
//				
//				//up right
//			}else if( centeredPlayerX >= centeredX + holdDistance && centeredPlayerY >= centeredY){
//				moveUpRight();
//			}
//		}else if(reverseCounter <= 800){
//			if(centeredPlayerX <= centeredX && centeredPlayerY >= centeredY + holdDistance){
//				moveUpRight();
//				
//			}else if( centeredPlayerX <= centeredX - holdDistance && centeredPlayerY <= centeredY){
//				moveUpLeft();
//				
//			}else if( centeredPlayerX >= centeredX && centeredPlayerY <= centeredY - holdDistance){
//				moveDownLeft();
//				
//			}else if( centeredPlayerX >= centeredX + holdDistance && centeredPlayerY >= centeredY){
//				moveDownRight();
//			}
//		}else{
//			reverseCounter =0;
//		}
		
		if(centeredPlayerX <= centeredX && centeredPlayerY >= centeredY + holdDistance){
			moveDownRight();
			
			//down left
		}else if( centeredPlayerX <= centeredX - holdDistance && centeredPlayerY <= centeredY){
			moveDownLeft();
			
			//up left
		}else if( centeredPlayerX >= centeredX && centeredPlayerY <= centeredY - holdDistance){
			moveUpLeft();
			
			//up right
		}else if( centeredPlayerX >= centeredX + holdDistance && centeredPlayerY >= centeredY){
			moveUpRight();
		}
		
		reverseCounter++;
	}
	
	public void moveDownRight(){
		xMove = speed;
		if(y < (((handler.getGameCamera().getyOffset() + 650))))
			yMove = speed;
		else
			yMove =0;
	}
	
	public void moveDownLeft(){
		xMove = -speed;
		yMove = speed;
		if(y < (((handler.getGameCamera().getyOffset() + 600))))
			yMove = speed;
		else
			yMove = 0;
	}
	
	public void moveUpLeft(){
		xMove = -speed;
		yMove = -speed;
	}
	public void moveUpRight(){
		xMove = speed;
		yMove = -speed;
	}
	
	@Override
	public void attack(){
		int randAttack = Utils.randomNum(0, 25);
		if(randAttack == 0){
			handler.getWorld().getEntityManager().addEntity(new Projectile(handler, this, getProjectileOrientation(),0));
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
	
}
