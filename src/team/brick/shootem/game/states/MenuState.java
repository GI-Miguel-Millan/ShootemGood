package team.brick.shootem.game.states;

import java.awt.Graphics;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.gfx.Assets;
import team.brick.shootem.game.ui.ClickListener;
import team.brick.shootem.game.ui.UIImageButton;
import team.brick.shootem.game.ui.UIManager;

public class MenuState extends State {

	private UIManager uiManager;

	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);

		uiManager.addObject(new UIImageButton(200, 200, 128, 64, Assets.btn_start, new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
			}
		}));
	}

	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}

}
