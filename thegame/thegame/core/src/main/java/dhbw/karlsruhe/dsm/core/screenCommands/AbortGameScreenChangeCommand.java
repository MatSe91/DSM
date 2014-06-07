package dhbw.karlsruhe.dsm.core.screenCommands;

import dhbw.karlsruhe.dsm.core.level.Level;
import dhbw.karlsruhe.dsm.core.screens.GameScreen;

public class AbortGameScreenChangeCommand extends ScreenChangeCommand {

	protected final Level level;
	private GameScreen gameScreen;
	
	public AbortGameScreenChangeCommand(final Level level, GameScreen gameScreen) {
		super();
		this.level = level;
		this.gameScreen = gameScreen;
	}

	@Override
	public void execute() {
		gameScreen.dispose();
		baseExecute(new GameScreen(game, level));
	}

}
