package dhbw.karlsruhe.dsm.core.screenCommands;

import dhbw.karlsruhe.dsm.core.screens.MainMenuScreen;

public class MenuScreenChangeCommand extends ScreenChangeCommand {
	public MenuScreenChangeCommand() {
		super();
	}
	
	@Override
	public void execute() {
		baseExecute(new MainMenuScreen(game));
	}
}
