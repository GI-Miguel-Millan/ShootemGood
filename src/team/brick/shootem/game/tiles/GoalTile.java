package team.brick.shootem.game.tiles;

import java.awt.Graphics;

import team.brick.shootem.game.gfx.Assets;

/**
 *	 A player must reach the GoalTile to end the level.
 * 	
 *	@author 
 *	@version 1.0
 *	@since version 1.0
 */
public class GoalTile extends Tile {

	public GoalTile(int id) {
		super(Assets.stone, id);
		// TODO Auto-generated constructor stub
	}
	
	public void render(Graphics g, int x, int y){
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public void showGoal(){
		texture = Assets.spaceBound;
	}
	
	public void hideGoal(){
		texture = Assets.stone;
	}

}
