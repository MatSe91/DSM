package dhbw.karlsruhe.dsm.core.screens;

import com.badlogic.gdx.Screen;

import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.actions.ChangeScreenInputListener;
import dhbw.karlsruhe.dsm.core.level.Level;
import dhbw.karlsruhe.dsm.core.screenCommands.HighscoreScreenChangeCommand;

public class HighScoreLevelSelectionScreen extends BaseLevelSelectionScreen implements Screen {

	public HighScoreLevelSelectionScreen(DSM game) {
		super(game);
	}

	@Override
	protected ChangeScreenInputListener createChangeScreenListener(final Level level) {
		return new ChangeScreenInputListener(new HighscoreScreenChangeCommand(level));
	}


}
