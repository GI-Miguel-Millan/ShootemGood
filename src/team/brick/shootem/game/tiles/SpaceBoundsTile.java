package team.brick.shootem.game.tiles;

import team.brick.shootem.game.gfx.Assets;

public class SpaceBoundsTile extends Tile {

	public SpaceBoundsTile(int id) {
		super(Assets.stone, id);
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}

}
