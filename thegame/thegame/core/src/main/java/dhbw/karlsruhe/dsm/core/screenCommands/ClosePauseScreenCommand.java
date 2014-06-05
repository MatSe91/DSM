package dhbw.karlsruhe.dsm.core.screenCommands;

import com.badlogic.gdx.Screen;

import dhbw.karlsruhe.dsm.core.DSM;

public class ClosePauseScreenCommand extends ScreenChangeCommand {
	
	private Screen next;
	
	public ClosePauseScreenCommand(DSM game, Screen next) {
		super();
		this.next = next;
	}
	
	@Override
	public void execute() {
		baseExecute(next);
	}

}
