package dhbw.karlsruhe.dsm.core.screenCommands;

import dhbw.karlsruhe.dsm.core.screens.GameLevelSelectionScreen;

public class GameLevelSelectionScreenChangeCommand extends	ScreenChangeCommand {

	public GameLevelSelectionScreenChangeCommand() {
		super();
	}

	@Override
	public void execute() {
		baseExecute(new GameLevelSelectionScreen(game));
	}

}
