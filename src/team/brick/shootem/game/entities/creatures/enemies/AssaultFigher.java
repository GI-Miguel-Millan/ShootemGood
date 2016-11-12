package team.brick.shootem.game.entities.creatures.enemies;

import java.awt.Color;
import java.awt.Graphics;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.entities.creatures.Creature;
import team.brick.shootem.game.gfx.Assets;
import team.brick.shootem.game.sound.Sound;
import team.brick.shootem.game.utils.Utils;

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
		/*g.setColor(Color.blue);
		g.drawRect(posX, posY, width, height);*/
		g.drawImage(Assets.assault, posX, posY, width, height, null);
		drawHealthBar(posX, posY, width, height, Creature.DEFAULT_HEALTH, health, 10,1, g);
		
	}
	
	@Override
	public void AIMove(){
		if(counter <100){
			xMove = 4;
		}else if(counter < 200){
			xMove = -4;
		}else{
			counter = 0;
		}
		counter += Utils.randomNum(1, 2);
	}

	@Override
	public void die() {
		// Add 150 to the players score whenever an AssaultFighter dies.
		handler.getWorld().getEntityManager().getPlayer().addScore(150);
		Sound.explosion.execute();//New jon edit
		
	}
}
