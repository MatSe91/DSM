package dhbw.karlsruhe.dsm.html;

import dhbw.karlsruhe.dsm.core.DSM;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class DSMTheGameHtml extends GwtApplication {
	@Override
	public ApplicationListener getApplicationListener () {
		return new DSM();
	}
	
	@Override
	public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(800, 600);
	}
	
	
}
