package team.brick.shootem.game.tiles;

import java.awt.image.BufferedImage;

import team.brick.shootem.game.gfx.Assets;

public class GoalTile extends Tile {

	public GoalTile(int id) {
		super(Assets.rock, id);
		// TODO Auto-generated constructor stub
	}
	
	public String toString(){
		return "Ima goal";
	}

}
