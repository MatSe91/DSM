package dhbw.karlsruhe.dsm.core.screenCommands;

import dhbw.karlsruhe.dsm.core.screens.GamePauseScreen;

public class GamePauseScreenCommand extends ScreenChangeCommand {

	public GamePauseScreenCommand() {
		super();
	}
	
	@Override
	public void execute() {
		baseExecute(new GamePauseScreen());
	}

}
