package dhbw.karlsruhe.dsm.core.screenCommands;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import dhbw.karlsruhe.dsm.core.DSM;

public abstract class ScreenChangeCommand {
	protected final DSM game = (DSM) Gdx.app.getApplicationListener();
	
	public ScreenChangeCommand() {
	}
	
	public abstract void execute();
	
	protected void baseExecute(Screen next) {
		Screen previous = game.getScreen();
		game.setScreen(next);
		previous.dispose();
	}
}
