package dhbw.karlsruhe.dsm.core.screenCommands;

import dhbw.karlsruhe.dsm.core.screens.HighScoreLevelSelectionScreen;

public class HighscoreLevelSelectionScreenChangeCommand extends ScreenChangeCommand {

	public HighscoreLevelSelectionScreenChangeCommand() {
		super();
	}

	@Override
	public void execute() {
		baseExecute(new HighScoreLevelSelectionScreen(game));
	}

}
