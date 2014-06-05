package dhbw.karlsruhe.dsm.core.screenCommands;

import dhbw.karlsruhe.dsm.core.DSM;

public class ClosePauseScreenCommand extends ScreenChangeCommand {
	
	public ClosePauseScreenCommand(DSM game) {
		super();
	}
	
	@Override
	public void execute() {
		baseExecute(game.getScreen());
	}

}
