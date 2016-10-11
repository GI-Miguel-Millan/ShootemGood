package team.brick.shootem.game.ui;

import java.awt.Color;
import java.awt.Graphics;

public class ScoreCounter
{
	protected int scoreCount = 0;
	
	public ScoreCounter(int scoreCount)
	{
		this.scoreCount = scoreCount;
	}
	
	public int getScore()
	{
		return scoreCount;
	}
	
	public void addToScore(int pointValue)
	{
		scoreCount = scoreCount + pointValue;
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
				g.setColor(Color.green);
				g.fillRect(0, 720, 500, 30);
				g.setColor(Color.black);
				g.drawString(String.valueOf(scoreCount), 240, 740); //Displays the score
				g.drawString("LIVES", 450, 740);	//Lives placeholder
				g.drawString("LEVEL", 25, 740);		//Level number placeholder
	}
}
