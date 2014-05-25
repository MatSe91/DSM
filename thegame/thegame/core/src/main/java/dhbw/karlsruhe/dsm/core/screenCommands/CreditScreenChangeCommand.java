package dhbw.karlsruhe.dsm.core.screenCommands;

import dhbw.karlsruhe.dsm.core.screens.CreditScreen;

public class CreditScreenChangeCommand extends ScreenChangeCommand {

	public CreditScreenChangeCommand() {
		super();
	}
	
	@Override
	public void execute() {
		baseExecute(new CreditScreen(game));
	}

}
