package team.brick.shootem.game.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;

import team.brick.shootem.game.Handler;

/**
 *	An Assault Fighter is a standard space fighter which shoots projectiles at the player.
 * 	
 *	@author Miguel Millan
 *	@version 1.0
 *	@since version 1.0
 */
public class AssaultFigher extends Enemy{

	public AssaultFigher(Handler handler, float x, float y) {
		super(handler, x, y);
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	public void render(Graphics g) {
		posX = (int)(x - handler.getGameCamera().getxOffset());
		posY = (int) (y - handler.getGameCamera().getyOffset());
		g.setColor(Color.blue);
		g.drawRect(posX, posY, width, height);
		
	}

	@Override
	public void die() {
		// Add 150 to the players score whenever an AssaultFighter dies.
		handler.getWorld().getEntityManager().getPlayer().addScore(150);
		
	}
}
