package dhbw.karlsruhe.dsm.core.screenCommands;

import dhbw.karlsruhe.dsm.core.level.Level;
import dhbw.karlsruhe.dsm.core.screens.GameScreen;

public class GameScreenChangeCommand extends ScreenChangeCommand {

	protected final Level level;
	
	public GameScreenChangeCommand(final Level level) {
		super();
		this.level = level;
	}

	@Override
	public void execute() {
		baseExecute(new GameScreen(game, level));
	}

}
