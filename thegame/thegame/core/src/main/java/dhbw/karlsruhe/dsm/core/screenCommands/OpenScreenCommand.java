package dhbw.karlsruhe.dsm.core.screenCommands;

import com.badlogic.gdx.Screen;

public abstract class OpenScreenCommand extends ScreenChangeCommand {
	
	protected void baseExecute(Screen next) {
		game.getScreen().hide();
		game.setScreen(next);
	}
}
