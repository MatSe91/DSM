package dhbw.karlsruhe.dsm.core.screens;

import com.badlogic.gdx.Screen;

import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.level.Level;

public class HighScoreLevelSelectionScreen extends BaseLevelSelectionScreen implements Screen {


	public HighScoreLevelSelectionScreen(DSM game) {
		super(game);
	}

	@Override
	protected void showNextScreen(Level level) {
		game.setScreen(new HighscoreScreen(game, level));
		super.dispose();
	}

}
