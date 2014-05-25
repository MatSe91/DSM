package dhbw.karlsruhe.dsm.core.screenCommands;

import dhbw.karlsruhe.dsm.core.screens.InstructionsScreen;

public class InstructionsScreenChangeCommand extends ScreenChangeCommand {

	public InstructionsScreenChangeCommand() {
		super();
	}
	
	@Override
	public void execute() {
		baseExecute(new InstructionsScreen(game));
	}

}
