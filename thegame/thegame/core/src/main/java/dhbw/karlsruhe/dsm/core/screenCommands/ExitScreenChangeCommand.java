package dhbw.karlsruhe.dsm.core.screenCommands;

import com.badlogic.gdx.Screen;

import dhbw.karlsruhe.dsm.core.screens.ExitGameScreen;

public class ExitScreenChangeCommand extends ScreenChangeCommand {

	public ExitScreenChangeCommand() {
		super();
	}
	
	@Override
	public void execute() {
		Screen previous = game.getScreen();
		game.setScreen(new ExitGameScreen(game, previous));
	}

}
