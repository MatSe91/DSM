package dhbw.karlsruhe.dsm.core.screenCommands;

import dhbw.karlsruhe.dsm.core.level.Level;
import dhbw.karlsruhe.dsm.core.screens.HighscoreScreen;

public class HighscoreScreenChangeCommand extends ScreenChangeCommand {

	protected final Level level;
	
	public HighscoreScreenChangeCommand(final Level level) {
		super();
		this.level = level;
	}

	@Override
	public void execute() {
		baseExecute(new HighscoreScreen(game, level));
	}

}
