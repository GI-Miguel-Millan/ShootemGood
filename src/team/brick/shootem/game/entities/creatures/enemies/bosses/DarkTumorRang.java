package team.brick.shootem.game.entities.creatures.enemies.bosses;

import java.awt.Color;
import java.awt.Graphics;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.entities.creatures.projectiles.DarkLaser;
import team.brick.shootem.game.entities.creatures.projectiles.Projectile;
import team.brick.shootem.game.gfx.Assets;
import team.brick.shootem.game.utils.Utils;

public class DarkTumorRang extends Boss{
	private static final int DARKTUMORRANG_WIDTH = 64;
	private static final int DARKTUMORRANG_HEIGHT = 64;
	private boolean readyFire = true;
	private int shootCounter = 0;
	
	public DarkTumorRang(Handler handler, float x, float y) {
		super(handler, x, y);
		this.width = DARKTUMORRANG_WIDTH;
		this.height = DARKTUMORRANG_HEIGHT;
		this.bounds.width = this.width;
		this.bounds.height = this.height;
	}

	@Override
	public void render(Graphics g) {
		posX = (int)(x - handler.getGameCamera().getxOffset());
		posY = (int) (y - handler.getGameCamera().getyOffset());
		g.setColor(Color.gray);
		//g.drawRect(posX, posY, width, height);
		g.drawImage(Assets.darkTumorRang, posX, posY, width, height, null);
	}
	
	@Override
	public void tick(){
		if (isOnScreen()){
			
			if(!readyFire)
				shootCounter++;
		
			if(shootCounter == 10){
				readyFire = true;
				shootCounter = 0;
			}
			
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
		
		if(handler.getWorld().getEntityManager().getPlayer().getX() > x){
			xMove = Utils.randomNum(2, 3);
		}else if (handler.getWorld().getEntityManager().getPlayer().getX() < x){
			xMove = -(Utils.randomNum(2, 3));
		}else {
			xMove =0;
		}
	}
	
	@Override
	public void attack(){
		// DarkTumorRang spawns a DarkLaser whenever the player attacks
		if(handler.getKeyManager().fire && readyFire){
			handler.getWorld().getEntityManager().addEntity(new DarkLaser(handler, this, getProjectileOrientation(), -3));
			readyFire = false;
		}
		
		int randAttack = Utils.randomNum(0, 50);
		if(randAttack == 0){
			handler.getWorld().getEntityManager().addEntity(new DarkLaser(handler, this, getProjectileOrientation(),0));
		}else if(randAttack == 1){
			if (getProjectileOrientation() == 0 || getProjectileOrientation() == 1){
				handler.getWorld().getEntityManager().addEntity
						(new DarkLaser(handler, this, getProjectileOrientation(), -(width/2)));
				handler.getWorld().getEntityManager().addEntity
						(new DarkLaser(handler, this, getProjectileOrientation(), width/2));
			}else{
				handler.getWorld().getEntityManager().addEntity
						(new DarkLaser(handler, this, getProjectileOrientation(), -(height/2)));
				handler.getWorld().getEntityManager().addEntity
						(new DarkLaser(handler, this, getProjectileOrientation(), height/2));
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

}
