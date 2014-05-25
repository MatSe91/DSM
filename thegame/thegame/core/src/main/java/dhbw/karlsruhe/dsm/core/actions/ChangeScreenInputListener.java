package dhbw.karlsruhe.dsm.core.actions;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import dhbw.karlsruhe.dsm.core.screenCommands.ScreenChangeCommand;

public class ChangeScreenInputListener extends InputListener {

	protected final ScreenChangeCommand screenChangeCommand;
	
	public ChangeScreenInputListener(final ScreenChangeCommand screenChangeCommand) {
		this.screenChangeCommand = screenChangeCommand;
	}
	

	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		screenChangeCommand.execute();
		return false;
	}


}
