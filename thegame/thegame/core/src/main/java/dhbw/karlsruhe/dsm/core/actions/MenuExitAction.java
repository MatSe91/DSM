package dhbw.karlsruhe.dsm.core.actions;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.screens.ExitGameScreen;

public class MenuExitAction extends MenuBaseAction {
	
	private final DSM game;
	
	public MenuExitAction(DSM game, Label targetLabel, String mouseOverText) {
		super(targetLabel, mouseOverText);
		this.game = game;
	}
	
	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
		//Methode wird in exitGame verlagert
//		showExitScreen();
		game.setScreen(new ExitGameScreen(game, game.getScreen()));
		return false;
	}
	
}
