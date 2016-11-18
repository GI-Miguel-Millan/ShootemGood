package team.brick.shootem.game.entities.statics;

import java.awt.Graphics;
import java.util.ArrayList;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.entities.Entity;
import team.brick.shootem.game.gfx.Animation;
import team.brick.shootem.game.gfx.Assets;

public class DeadEntity extends StaticEntity{
	
	public static ArrayList<DeadEntity> deadEntities = new ArrayList<DeadEntity>();
	
	private Entity entity;
	private Animation explosion, BExp1, BExp2, BExp3, BExp4;
	private int counter;
	private int BossCounter =0;
	private boolean canDraw1 = false;
	private boolean canDraw2 = false;
	private boolean canDraw3 = false;
	private boolean canDraw4 = false;
	
	public DeadEntity(Handler handler, Entity e) {
		super(handler, e.getX(), e.getY(), e.getWidth(), e.getHeight());
		
		entity = e;
		explosion = new Animation(50, Assets.explosion);
		BExp1 = new Animation(50, Assets.explosion);
		BExp2 = new Animation(50, Assets.explosion);
		BExp3 = new Animation(50, Assets.explosion);
		BExp4 = new Animation(50, Assets.explosion);
	}

	public static void addDeadEntity(Handler handler, Entity e){
		deadEntities.add(new DeadEntity(handler, e));
	}
	
	@Override
	public void tick() {
		if(canDraw1)
			BExp1.tick();
		if(canDraw2)
			BExp2.tick();
		if(canDraw3)
			BExp3.tick();
		if(canDraw4)
			BExp4.tick();
		
		explosion.tick();
		
		
		
		counter++;
		
		if(!entity.isBoss() && explosion.onLastFrame())
			die();
		else if(entity.isBoss() && BExp4.onLastFrame())
			die();
		
	}

	@Override
	public void render(Graphics g) {
		posX = (int)(x - handler.getGameCamera().getxOffset());
		posY = (int) (y - handler.getGameCamera().getyOffset());
		if(counter < 5)
			entity.render(g);
		
		if(!entity.isBoss())
			g.drawImage(explosion.getCurrentFrame(), posX-(32-width/2), posY-(32-height/2), 64, 64, null);
		else
			drawBossExplosion(g);
		
	}
	
	private void drawBossExplosion(Graphics g){
		
		BossCounter++;
		
		if(BossCounter == 1)
			canDraw1 = true;
		else if(BossCounter == 10)
			canDraw2 = true;
		else if(BossCounter == 20)
			canDraw3 =true;
		
		if(canDraw1){
			g.drawImage(BExp1.getCurrentFrame(), posX, posY, 64, 64, null);
			if(BExp1.onLastFrame())
				canDraw1 = false;
		}
		
		if(canDraw2){
			g.drawImage(BExp2.getCurrentFrame(), posX + 16, posY+16, 64, 64, null);
			if(BExp2.onLastFrame())
				canDraw2 = false;
		}
		if(canDraw3){
			g.drawImage(BExp3.getCurrentFrame(), posX, posY + 16, 64, 64, null);
			if(BExp2.onLastFrame()){
				canDraw3 = false;
				canDraw4 = true;
			}
				
		}
		
		if(canDraw4)
			g.drawImage(BExp4.getCurrentFrame(), posX-(64-width/2), posY-(64-height/2) , 128, 128, null);
	}

	@Override
	public void die() {
		active = false;
	}

}
