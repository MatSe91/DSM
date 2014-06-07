package dhbw.karlsruhe.dsm.core.screenCommands;

import dhbw.karlsruhe.dsm.core.screens.GameLevelSelectionScreen;
import dhbw.karlsruhe.dsm.core.screens.GameScreen;

public class AbortGameLevelSelectionScreenChangeCommand extends	ScreenChangeCommand {

	private GameScreen gameScreen;
	
	public AbortGameLevelSelectionScreenChangeCommand(GameScreen gameScreen) {
		super();
		this.gameScreen = gameScreen;
	}

	@Override
	public void execute() {
		gameScreen.dispose();
		baseExecute(new GameLevelSelectionScreen(game));
	}

}
