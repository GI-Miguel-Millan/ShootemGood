package team.brick.shootem.game.states;

import java.awt.Graphics;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.gfx.Assets;
import team.brick.shootem.game.ui.ClickListener;
import team.brick.shootem.game.ui.UIImageButton;
import team.brick.shootem.game.ui.UILabel;
import team.brick.shootem.game.ui.UIManager;

public class VictoryState extends State {

	private UIManager uiManager;
	UILabel lblScore;
	UIImageButton btnRestart;
	
	public VictoryState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		btnRestart = new UIImageButton(200, 400, 200,100, Assets.btn_start, new ClickListener(){
			@Override
			public void onClick(){
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().menuState);
			}
		});
		
		lblScore = new UILabel(200, 200, 60, 50, "Score: " + handler.getPlayerScore(), new ClickListener(){
			@Override
			public void onClick(){
				
			}
		});
		
		uiManager.addObject(lblScore);
		uiManager.addObject(btnRestart);
		
	}

	@Override
	public void tick() {
		lblScore.setText("Score: " + handler.getPlayerScore());
		//System.out.println(btnRestart.isHovering());
		uiManager.tick();
		
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}
	

}
