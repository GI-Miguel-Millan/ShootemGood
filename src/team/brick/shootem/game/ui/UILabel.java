package team.brick.shootem.game.ui;

import java.awt.Graphics;

public class UILabel extends UIObject {

	private String text;
	
	public UILabel(float x, float y, int width, int height, String text, ClickListener clicker) {
		super(x, y, width, height);
		this.text = text;
	}

	@Override
	public void tick() {
		
	}
	
	@Override
	public void render(Graphics g) {
		g.drawString(text, (int)x, (int)y);
	}

	@Override
	public void onClick()
	{

	}
	
	public void setText(String txt){
		text = txt;
	}

}
