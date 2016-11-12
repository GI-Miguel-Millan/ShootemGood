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
	private Animation explosion;
	private int counter;
	
	public DeadEntity(Handler handler, Entity e) {
		super(handler, e.getX(), e.getY(), e.getWidth(), e.getHeight());
		
		entity = e;
		explosion = new Animation(50, Assets.explosion);
	}

	public static void addDeadEntity(Handler handler, Entity e){
		deadEntities.add(new DeadEntity(handler, e));
	}
	
	@Override
	public void tick() {
		explosion.tick();
		counter++;
		
		if(explosion.onLastFrame())
			die();
	}

	@Override
	public void render(Graphics g) {
		posX = (int)(x - handler.getGameCamera().getxOffset());
		posY = (int) (y - handler.getGameCamera().getyOffset());
		if(counter < 5)
			entity.render(g);
		g.drawImage(explosion.getCurrentFrame(), posX-(32-width/2), posY-(32-height/2), 64, 64, null);
	}

	@Override
	public void die() {
		active = false;
	}

}
