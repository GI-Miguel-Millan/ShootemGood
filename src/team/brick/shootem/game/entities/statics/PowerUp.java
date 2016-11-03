package team.brick.shootem.game.entities.statics;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.entities.Entity;

public abstract class PowerUp extends StaticEntity{

	public static final int DEFAULT_CREATURE_WIDTH = 20,
							DEFAULT_CREATURE_HEIGHT = 20,
							;
	
	public PowerUp(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		
		// TODO Auto-generated constructor stub
	}

	
}
