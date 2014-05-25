package dhbw.karlsruhe.dsm.core.screens;

import com.badlogic.gdx.Screen;

import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.actions.ChangeScreenInputListener;
import dhbw.karlsruhe.dsm.core.level.Level;
import dhbw.karlsruhe.dsm.core.screenCommands.GameScreenChangeCommand;

public class GameLevelSelectionScreen extends BaseLevelSelectionScreen
		implements Screen {

	public GameLevelSelectionScreen(DSM game) {
		super(game);
	}
	
	@Override
	protected ChangeScreenInputListener createChangeScreenListener(final Level level) {
		return new ChangeScreenInputListener(new GameScreenChangeCommand(level));
	}

}
