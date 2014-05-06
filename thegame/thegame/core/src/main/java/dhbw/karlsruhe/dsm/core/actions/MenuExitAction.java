package dhbw.karlsruhe.dsm.core.actions;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.ExitGameScreen;

public class MenuExitAction extends MenuBaseAction {
	
	private final DSM game;
	
	public MenuExitAction(DSM game, Label targetLabel, String mouseOverText) {
		super(targetLabel, mouseOverText);
		this.game = game;
	}
	
	public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
		//TODO @Maurice: Diese Methode wird aufgerufen wenn der Exit Button gedrueckt wird.
		//TODO @Maurice: Diese Methode soll nur noch den ExitGameScreen aufrufen und den MainMenuScreen schlie�en
		//Methode wird in exitGame verlagert
//		showExitScreen();
		game.setScreen(new ExitGameScreen(game, game.getScreen()));
		return false;
	}
	
}
