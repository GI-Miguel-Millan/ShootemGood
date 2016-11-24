package team.brick.shootem.game.states;

import java.awt.Graphics;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.display.Display;
import team.brick.shootem.game.gfx.Assets;
import team.brick.shootem.game.input.MouseManager;
import team.brick.shootem.game.sound.Sound;
import team.brick.shootem.game.ui.ClickListener;
import team.brick.shootem.game.ui.UIImageButton;
import team.brick.shootem.game.ui.UILabel;
import team.brick.shootem.game.ui.UIManager;

public class GameOverState extends State
{
	private UIManager uiManager;
	private UILabel lblScore;
	private UILabel lblHighScore;
	
	UILabel lblGameOver = new UILabel(150, 150, 1, 1, "GAME OVER", null);
	UIImageButton btnRestart = new UIImageButton(150, 350, 200, 100, Assets.btn_start, new ClickListener()
			{
				public void onClick()
				{
					handler.getMouseManager().setUIManager(null);
					handler.getGame().getMenuState().displayState();
				}
			});
	
	public GameOverState(Handler handler)
	{
		super(handler);

		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
	}

	@Override
	public void tick() {
		uiManager.tick();
		
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}
	
	/**
	 * Sets the state to the game over state and sets
	 * the ui manager from null to uiManager
	 * @Override
	 */
	public void displayState(){
		State.setState(handler.getGame().getGameOverState());
		
		Sound.stopAll();
		if(handler.isVictorious())
			Sound.victorious.play();
		else
			Sound.failure.play();
		
		handler.getMouseManager().setUIManager(uiManager);
		uiManager.removeObject(lblHighScore);
		uiManager.removeObject(lblScore);
		lblScore = new UILabel(150, 200, 10, 20, "SCORE: " + handler.getPlayerScore(), null);
		lblHighScore = new UILabel(150, 250, 10, 20, "HIGH SCORE: " + handler.getHighScore(), null);
		uiManager.addObject(lblGameOver);
		uiManager.addObject(lblScore);
		uiManager.addObject(lblHighScore);
		uiManager.addObject(btnRestart);
	}
}
