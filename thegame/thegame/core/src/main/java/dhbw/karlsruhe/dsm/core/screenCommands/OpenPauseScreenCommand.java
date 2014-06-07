package dhbw.karlsruhe.dsm.core.screenCommands;

import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.screens.GamePauseScreen;
import dhbw.karlsruhe.dsm.core.screens.GameScreen;

public class OpenPauseScreenCommand extends OpenScreenCommand {
	
	public OpenPauseScreenCommand(DSM game) {
		super();
	}
	
	@Override
	public void execute() {
		baseExecute(new GamePauseScreen(game, (GameScreen) game.getScreen()));
	}

}
