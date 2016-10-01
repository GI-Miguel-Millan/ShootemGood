package team.brick.shootem.game.entities.statics;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.entities.Entity;

/**
 *	A StaticEntity is simply an Entity which does not move or have health.
 * 	
 *	@author Miguel Millan
 *	@version 1.0
 *	@since version 1.0
 */
public abstract class StaticEntity extends Entity {
	
	public StaticEntity(Handler handler, float x, float y, int width, int height){
		super(handler, x, y, width, height);
	}

}
