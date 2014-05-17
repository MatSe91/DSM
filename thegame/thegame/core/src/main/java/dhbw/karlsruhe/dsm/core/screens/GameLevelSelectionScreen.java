package dhbw.karlsruhe.dsm.core.screens;

import com.badlogic.gdx.Screen;

import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.level.Level;

public class GameLevelSelectionScreen extends BaseLevelSelectionScreen
		implements Screen {

	public GameLevelSelectionScreen(DSM game) {
		super(game);
	}
	
	@Override
	protected void showNextScreen(Level level) {
		game.setScreen(new GameScreen(game, level));
		super.dispose();
	}

}
