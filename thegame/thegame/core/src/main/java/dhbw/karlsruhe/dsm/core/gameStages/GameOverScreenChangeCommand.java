package dhbw.karlsruhe.dsm.core.gameStages;

import dhbw.karlsruhe.dsm.core.screenCommands.OpenScreenCommand;
import dhbw.karlsruhe.dsm.core.screens.GameOverScreen;
import dhbw.karlsruhe.dsm.core.screens.GameScreen;

public class GameOverScreenChangeCommand extends OpenScreenCommand {
	
	private int score;
	
	public GameOverScreenChangeCommand(int score) {
		super();
		this.score = score;
	}
	
	@Override
	public void execute() {
		baseExecute(new GameOverScreen(game, (GameScreen) game.getScreen(), score));
	}

}
