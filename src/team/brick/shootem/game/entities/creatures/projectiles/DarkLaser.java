package team.brick.shootem.game.entities.creatures.projectiles;

import java.awt.Color;
import java.awt.Graphics;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.entities.Entity;

/**
 *	A DarkLaser is a faster projectile with a different texture.
 * 
 *	@author Miguel Millan
 *	@version 1.0
 *	@since version 1.0
 */
public class DarkLaser extends Projectile {

	public DarkLaser(Handler handler, Entity e, int orient, int offset) {
		super(handler, e, orient, offset);
		speed = 6.0f;
	}
	
	@Override
	public void render(Graphics g) {
		posX = (int)(x - handler.getGameCamera().getxOffset());
		posY = (int) (y - handler.getGameCamera().getyOffset());
		g.setColor(Color.red);
		g.drawRect(posX, posY, width, height);
		//g.drawImage(Assets.projectile, posX, posY, width, height, null);
		
	}

}
