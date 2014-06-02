package dhbw.karlsruhe.dsm.core.screenCommands;

import com.badlogic.gdx.Screen;

import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.screens.GamePauseScreen;

public class OpenPauseScreenCommand extends OpenScreenCommand {

	Screen gameScreen;
	
	public OpenPauseScreenCommand(DSM game, Screen gameScreen) {
		super();
		this.gameScreen = gameScreen;
	}
	
	@Override
	public void execute() {
		baseExecute(new GamePauseScreen(game, gameScreen));
	}

}
