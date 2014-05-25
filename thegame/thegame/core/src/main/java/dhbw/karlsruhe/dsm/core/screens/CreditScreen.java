package dhbw.karlsruhe.dsm.core.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import dhbw.karlsruhe.dsm.core.DSM;

public final class CreditScreen implements Screen {
	// Button Label Strings

	// Button Mouse-Over Event Strings
	
	private Stage stage;
	private DSM game;
	
	// Buttons
	private TextButton backButton;
	// Labels
	private Label menuText;
	private Label headline;
	// Tables
	private Table table;
	private static final int ROW_SPACE = 15;
	//CREDITS
		//Strings
	private static final String strng_Credit_1 = "Created by: ";
	private static final String strng_Credit_2 = "Maurice Angermann";
	private static final String strng_Credit_3 = "Soeren Berken-Mersmann";
	private static final String strng_Credit_4 = "Denis Gauss";
	private static final String strng_Credit_5 = "On behalf of:";
	private static final String strng_Credit_6 = "Duale Hochschule Baden-Württemberg - Karlsruhe";
	private static final String strng_Credit_7 = "Erzbergerstraße 121";
	private static final String strng_Credit_8 = "76133 Karlsruhe";
	private static final String strng_Credit_9 = "Credits to:";
	private static final String strng_Credit_10 = "Icons: http://www.icon2s.com/143/keyboard-keys-letter-icons/";

		//Labels
	private Label lbl_Credit_1;
	private Label lbl_Credit_2;
	private Label lbl_Credit_3;
	private Label lbl_Credit_4;
	private Label lbl_Credit_5;
	private Label lbl_Credit_6;
	private Label lbl_Credit_7;
	private Label lbl_Credit_8;
	private Label lbl_Credit_9;
	private Label lbl_Credit_10;
	
	public CreditScreen(DSM game) {
		this.game = game;
		this.stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		// Initialize actors
		initActors();
		
		// Add them to the stage

		stage.addActor(headline);
		stage.addActor(table);
		stage.addActor(menuText);
		
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
	}

	@Override
	public void render(float delta) {
		// Clear the screen
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		

		// Table.drawDebug(stage); // Debuglines for Tables

	}

	@Override
	public void resize(int width, int height) {
		
		stage.setViewport(width, height, true);

	}

	@Override
	public void resume() {

	}
	
	public boolean needsGL20() {
		return true;
	}

	@Override
	public void show() {

	}
	
	
	/**
	 * Initializes all Actors (delegates)
	 */
	private void initActors() {
		// Prepare everything
		initLabels();
		initButtons();
		// Initialize our Actors
		initTable();
		stage.addActor(backButton);
	}
	
	/**
	 * Initializes the table 
	 */
	private void initTable() {
		table = game.screenHelper.createTable();
		table.right().padRight(50);
		table.add();
		table.row().spaceBottom(ROW_SPACE);
		table.add();
		table.row().spaceBottom(ROW_SPACE);
		table.add(lbl_Credit_1).colspan(2).expandX();
		table.row().spaceBottom(ROW_SPACE);
		table.add(lbl_Credit_2).colspan(2).expandX();
		table.row().spaceBottom(ROW_SPACE);
		table.add(lbl_Credit_3).colspan(2).expandX();
		table.row().spaceBottom(ROW_SPACE);
		table.add(lbl_Credit_4).colspan(2).expandX();
		table.row().spaceBottom(ROW_SPACE);
		table.add();
		table.row().spaceBottom(ROW_SPACE);
		table.add();
		table.row().spaceBottom(ROW_SPACE);
		table.add();
		table.row().spaceBottom(ROW_SPACE);
		table.add(lbl_Credit_5).colspan(2).expandX();
		table.row().spaceBottom(ROW_SPACE);
		table.add(lbl_Credit_6).colspan(2).expandX();
		table.row().spaceBottom(ROW_SPACE);
		table.add(lbl_Credit_7).colspan(2).expandX();
		table.row().spaceBottom(ROW_SPACE);
		table.add(lbl_Credit_8).colspan(2).expandX();
		table.row().spaceBottom(ROW_SPACE);
		table.add();
		table.row().spaceBottom(ROW_SPACE);
		table.add();
		table.row().spaceBottom(ROW_SPACE);
		table.add();
		table.row().spaceBottom(ROW_SPACE);
		table.add(lbl_Credit_9).colspan(2).expandX();
		table.row().spaceBottom(ROW_SPACE);
		table.add(lbl_Credit_10).colspan(2).expandX();
		
		
		
			
	}
	
	/**
	 * Initializes all the buttons, including their InputListeners
	 */
	private void initButtons() {
		backButton = game.screenHelper.createReturnButton(MainMenuScreen.class);
		
		
	}
	
	/**
	 * Initializes the Label
	 */
	private void initLabels() {	
		menuText = game.screenHelper.createLabel("", 0, 160, 50, 0);
		menuText.setWidth(stage.getWidth());
		menuText.setZIndex(10);
		
		
		
		headline = game.screenHelper.createHeadline("CREDITS");
		//Initialize Credit-Labels
		lbl_Credit_1 = game.screenHelper.createLabel(strng_Credit_1, 0, 0);
		lbl_Credit_2 = game.screenHelper.createLabel(strng_Credit_2, 0, 0);
		lbl_Credit_3 = game.screenHelper.createLabel(strng_Credit_3, 0, 0);
		lbl_Credit_4 = game.screenHelper.createLabel(strng_Credit_4, 0, 0);
		lbl_Credit_5 = game.screenHelper.createLabel(strng_Credit_5, 0, 0);
		lbl_Credit_6 = game.screenHelper.createLabel(strng_Credit_6, 0, 0);
		lbl_Credit_7 = game.screenHelper.createLabel(strng_Credit_7, 0, 0);
		lbl_Credit_8 = game.screenHelper.createLabel(strng_Credit_8, 0, 0);
		lbl_Credit_9 = game.screenHelper.createLabel(strng_Credit_9, 0, 0);
		lbl_Credit_10 = game.screenHelper.createLabel(strng_Credit_10, 0, 0);
	}
	
	/**
	 * Exits the Application
	 */

}
