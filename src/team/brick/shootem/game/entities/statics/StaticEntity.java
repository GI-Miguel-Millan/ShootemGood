package team.brick.shootem.game.entities.statics;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.entities.Entity;

public abstract class StaticEntity extends Entity {
	
	public StaticEntity(Handler handler, float x, float y, int width, int height){
		super(handler, x, y, width, height);
	}

}
