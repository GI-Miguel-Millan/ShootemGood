package team.brick.shootem.game.ui;

import java.awt.Graphics;

public class UILabel extends UIObject
{

	private String label;
	
	public UILabel(float x, float y, int width, int height, String label)
	{
		super(x, y, width, height);
		this.label = label;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g)
	{
		g.drawString(label, (int) x, (int) y); 
		
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		
	}
	
}
