package team.brick.shootem.game.entities.creatures.enemies.bosses;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.entities.creatures.enemies.Enemy;
import team.brick.shootem.game.entities.creatures.projectiles.Projectile;
import team.brick.shootem.game.utils.Utils;

public abstract class Boss extends Enemy {
	protected static final int DEFAULT_BOSS_WIDTH = 64;
	protected static final int DEFAULT_BOSS_HEIGHT = 96;
	protected static final int DEFAULT_BOSS_HEALTH = 20;
	protected boolean isMoving = true;
	protected int waitMoveCounter = 0;
	
	public Boss(Handler handler, float x, float y) {
		super(handler, x, y);
		this.width = DEFAULT_BOSS_WIDTH;
		this.height = DEFAULT_BOSS_HEIGHT;
		this.bounds.width = this.width;
		this.bounds.height = this.height;
		
		this.health = DEFAULT_BOSS_HEALTH;
	}
	
	@Override
	public void AIMove(){
		int rndMove = Utils.randomNum(2, 3);
		int newMove = 0;
		
		if(isMoving){
			newMove = 5;
		}
			
		
		if(handler.getWorld().getEntityManager().getPlayer().getX() > x + width/2){
			xMove = newMove;
		}else if (handler.getWorld().getEntityManager().getPlayer().getX() < x + width/2){
			xMove = -(newMove);
		}else {
			xMove =0;
			isMoving = false;
		}
		
		if(!isMoving){
			waitMoveCounter++;
		}
		
		if(waitMoveCounter >= 70){
			isMoving = true;
			waitMoveCounter = 0;
		}
		
		
	}
	
	@Override
	public void attack(){
		int randAttack = Utils.randomNum(0, 50);
		if(randAttack == 0){
			handler.getWorld().getEntityManager().addEntity(new Projectile(handler, this, getProjectileOrientation(),0));
		}else if(randAttack == 1){
			if (getProjectileOrientation() == 0 || getProjectileOrientation() == 1){
				handler.getWorld().getEntityManager().addEntity
						(new Projectile(handler, this, getProjectileOrientation(), -(width/2)));
				handler.getWorld().getEntityManager().addEntity
						(new Projectile(handler, this, getProjectileOrientation(),0));
				handler.getWorld().getEntityManager().addEntity
						(new Projectile(handler, this, getProjectileOrientation(), width/2));
			}else{
				handler.getWorld().getEntityManager().addEntity
				(new Projectile(handler, this, getProjectileOrientation(), -(height/2)));
				handler.getWorld().getEntityManager().addEntity
						(new Projectile(handler, this, getProjectileOrientation(),0));
				handler.getWorld().getEntityManager().addEntity
						(new Projectile(handler, this, getProjectileOrientation(), height/2));
			}
			
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

	@Override
	public void die() {
		handler.getPlayer().setIsBossDead(true);
		handler.getPlayer().addScore(1000);
	}

}
