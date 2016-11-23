package team.brick.shootem.game.tiles;

import java.awt.image.BufferedImage;

import team.brick.shootem.game.gfx.Assets;

/**
 *	When a player interacts with this tile the boss fight starts.
 * 	
 *	@author 
 *	@version 1.0
 *	@since version 1.0
 */
public class BossFightStartTile extends Tile{

	public BossFightStartTile(int id) {
		super(Assets.space2, id);
		// TODO Auto-generated constructor stub
	}

	public boolean isBFight(){
		return true;
	}
	
}
