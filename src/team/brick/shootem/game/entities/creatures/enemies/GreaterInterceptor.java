package team.brick.shootem.game.entities.creatures.enemies;

import team.brick.shootem.game.Handler;

public class GreaterInterceptor extends Interceptor{

	public GreaterInterceptor(Handler handler, float x, float y) {
		super(handler, x, y);
		health = 2;
	}

}
