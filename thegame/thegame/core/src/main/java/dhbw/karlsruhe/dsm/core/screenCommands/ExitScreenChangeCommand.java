package dhbw.karlsruhe.dsm.core.screenCommands;

import dhbw.karlsruhe.dsm.core.screens.ExitGameScreen;

public class ExitScreenChangeCommand extends ScreenChangeCommand {

	public ExitScreenChangeCommand() {
		super();
	}
	
	@Override
	public void execute() {
		game.setScreen(new ExitGameScreen(game));
	}

}
