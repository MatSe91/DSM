package dhbw.karlsruhe.dsm.core.actions;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class MenuBaseAction extends InputListener {

	protected final Label menuText;
	protected final String mouseOverText;
	
	public MenuBaseAction( Label menuText, String mouseOverText) {
		this.menuText = menuText;
		this.mouseOverText = mouseOverText;
	}
	
	public void enter(InputEvent event, float x, float y, int pointer, Actor actor) {
		menuText.setText(mouseOverText);
	}
	public void exit(InputEvent event, float x, float y, int pointer, Actor actor) {
		menuText.setText("");
	}
	
}
