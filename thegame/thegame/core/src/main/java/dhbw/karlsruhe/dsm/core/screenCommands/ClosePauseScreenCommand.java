package dhbw.karlsruhe.dsm.core.screenCommands;

import com.badlogic.gdx.Screen;

import dhbw.karlsruhe.dsm.core.DSM;

public class ClosePauseScreenCommand extends ScreenChangeCommand {
	
	Screen gameScreen;
	
	public ClosePauseScreenCommand(DSM game, Screen gameScreen) {
		super();
		this.gameScreen = gameScreen;
	}
	
	@Override
	public void execute() {
		baseExecute(gameScreen);
	}

}
