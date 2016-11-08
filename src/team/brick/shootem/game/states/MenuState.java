package team.brick.shootem.game.states;

import java.awt.Graphics;

import team.brick.shootem.game.Handler;
import team.brick.shootem.game.entities.creatures.Player;
import team.brick.shootem.game.gfx.Assets;
import team.brick.shootem.game.ui.ClickListener;
import team.brick.shootem.game.ui.UIImageButton;
import team.brick.shootem.game.ui.UIManager;
import team.brick.shootem.game.worlds.World;

/**
 *	The MenuState is the pre-game state, giving the option to start the game.
 * 	
 *	@author 
 *	@version 1.0
 *	@since version 1.0
 */
public class MenuState extends State {

	private UIManager uiManager;

	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);

		// adds a button that switches the current state to the gamestate when pressed.
		uiManager.addObject(new UIImageButton(100, 50, 275, 225, Assets.btn_start, new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				handler.getGame().getGameState().displayState();
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
		g.drawImage(Assets.menu, 0, 0, handler.getWidth(), handler.getHeight(), null);
	}
	
	/**
	 * Sets the state to the menu state and sets
	 * the ui manager from null to uiManager
	 * @Override
	 */
	public void displayState() {
		State.setState(handler.getGame().getMenuState());
		handler.getMouseManager().setUIManager(uiManager);
	}

}
