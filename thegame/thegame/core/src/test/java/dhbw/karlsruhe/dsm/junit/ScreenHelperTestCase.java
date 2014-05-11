package dhbw.karlsruhe.dsm.junit;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import dhbw.karlsruhe.dsm.config.ConfigurationConstants;
import dhbw.karlsruhe.dsm.core.DSM;
import dhbw.karlsruhe.dsm.core.screens.ScreenHelper;
import dhbw.karlsruhe.dsm.helpers.TestHelper;

public class ScreenHelperTestCase {

	private static final String ERROR_POS_X = "X-Position doesn't match";
	private static final String ERROR_POS_Y = "Y-Position doesn't match";
	private static final String ERROR_HEIGHT = "Height doesn't match";
	private static final String ERROR_WIDTH = "Width doesn't match";
	private static final String ERROR_TEXT = "Text doesn't match";
	
	private static LwjglApplication app; 
	private static DSM game;
	
	private ScreenHelper screenHelper;
	
	private int x, y, width, height;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		app = new LwjglApplication(new DSM(), TestHelper.createTestConfig());
		game = (DSM) Gdx.app.getApplicationListener();
		TestHelper.wait(1000);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		app.exit();
	}

	@Before
	public void setUp() throws Exception {
		screenHelper = new ScreenHelper(game);
		x = (int) Math.random() * ConfigurationConstants.SCREENWIDTH;
		width = (int) Math.random() * ConfigurationConstants.SCREENWIDTH;
		
		y = (int) Math.random() * ConfigurationConstants.SCREENHEIGHT;
		height = (int) Math.random() * ConfigurationConstants.SCREENHEIGHT;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateHeadline() {
		String headlineText = "Test Headline";
		Label headline = screenHelper.createHeadline(headlineText);
		
		verfiyHeadlineValues(headline, headlineText);
	}

	@Test
	public void testUpdateHeadlineAfterResize() {
		String headlineText = "Test Headline";
		Label headline = screenHelper.createHeadline(headlineText);
		headline.setX(x);
		headline.setY(y);
		screenHelper.updateHeadlineAfterResize(headline);
		
		verfiyHeadlineValues(headline, headlineText);
	}

	private void verfiyHeadlineValues(Label headline, String headlineText) {
		assertEquals(ERROR_TEXT, headlineText, headline.getText().toString());
		assertEquals(ERROR_HEIGHT, ScreenHelper.HEADLINE_HEIGHT, headline.getHeight(),  0.1);
		assertEquals(ERROR_POS_X, ScreenHelper.HEADLINE_POSITION_X, headline.getX(), 0.1);
		assertEquals(ERROR_POS_Y, game.getHeight() + ScreenHelper.HEADLINE_POSITION_Y, headline.getY(), 0.1);
		// Note: Alignment cannot be tested
	}
	
	@Test
	public void testCreateLabelTextXYHeight() {
		String text = "test label";
		Label testLabel = screenHelper.createLabel(text, x, y, height);
		
		assertEquals(ERROR_TEXT, text, testLabel.getText().toString());
		assertEquals(ERROR_POS_X, x, testLabel.getX(), .1);
		assertEquals(ERROR_POS_Y, y, testLabel.getY(), .1);
		assertEquals(ERROR_HEIGHT, height, testLabel.getHeight(),  0.1);
	}

	@Test
	public void testCreateLabelTextXY() {
		String text = "test label";
		Label testLabel = screenHelper.createLabel(text, x, y);
		
		assertEquals(ERROR_TEXT, text, testLabel.getText().toString());
		assertEquals(ERROR_POS_X, x, testLabel.getX(), .1);
		assertEquals(ERROR_POS_Y, y, testLabel.getY(), .1);
	}

	@Test
	public void testCreateLabelTextXYHeightAlignment() {
		String text = "test label";
		Label testLabel = screenHelper.createLabel(text, x, y, height, Align.center);
		
		assertEquals(ERROR_TEXT, text, testLabel.getText().toString());
		assertEquals(ERROR_POS_X, x, testLabel.getX(), .1);
		assertEquals(ERROR_POS_Y, y, testLabel.getY(), .1);
		assertEquals(ERROR_HEIGHT, height, testLabel.getHeight(),  0.1);
		// Note: Alignment cannot be tested
	}

	@Test
	public void testCreateTextButtonTextXYWidth() {
		String text = "test button";
		TextButton testButton = screenHelper.createTextButton(text, x, y, width);
		
		assertEquals(ERROR_TEXT, text, testButton.getText().toString());
		assertEquals(ERROR_POS_X, x, testButton.getX(), .1);
		assertEquals(ERROR_POS_Y, y, testButton.getY(), .1);
		assertEquals(ERROR_WIDTH, width, testButton.getWidth(), .1);
	}

	@Test
	public void testCreateTextButtonTextWidth() {
		String text = "test button";
		TextButton testButton = screenHelper.createTextButton(text, width);
		
		assertEquals(ERROR_TEXT, text, testButton.getText().toString());
		assertEquals(ERROR_WIDTH, width, testButton.getWidth(), .1);
	}

	@Test
	public void testCreateReturnButton() {
		TextButton testButton = screenHelper.createReturnButton();
		verfiyReturnButtonValues(testButton);
	}

	@Test
	public void testUpdateReturnButtonAfterResize() {
		TextButton testButton = screenHelper.createReturnButton();
		testButton.setX(x);
		testButton.setY(y);
		screenHelper.updateReturnButtonAfterResize(testButton);
		
		verfiyReturnButtonValues(testButton);
	}
	
	private void verfiyReturnButtonValues(TextButton returnButton) {
		assertEquals(ERROR_TEXT, ScreenHelper.RETURN_BUTTON_TEXT, returnButton.getText().toString());
		assertEquals(ERROR_POS_X, game.getWidth() - ScreenHelper.RETURN_BUTTON_POSITION_DELTA_X, returnButton.getX(), .1);
		assertEquals(ERROR_POS_Y, ScreenHelper.RETURN_BUTTON_POSITION_Y, returnButton.getY(), .1);
		assertEquals(ERROR_WIDTH, ScreenHelper.RETURN_BUTTON_WIDTH, returnButton.getWidth(), .1);
	}

	@Test
	public void testCreateTable() {
		Table table = screenHelper.createTable();
		verfiyTableValues(table);
	}

	@Test
	public void testUpdateTableAfterResize() {
		Table table = screenHelper.createTable();
		table.setWidth(20);
		table.setHeight(20);
		
		screenHelper.updateTableAfterResize(table);
		
		verfiyTableValues(table);
	}
	
	private void verfiyTableValues(Table table) {
		assertEquals("", ScreenHelper.TABLE_POSITION_X, table.getX(), 0.1);
		assertEquals("", ScreenHelper.TABLE_POSITION_Y, table.getY(), 0.1);
		assertEquals("", game.getWidth(), table.getWidth(), 20);
		assertEquals("", game.getHeight(), table.getHeight(), 20);
		assertEquals("", ScreenHelper.TABLE_PAD_LEFT, table.getPadLeft(), .1);
	}

}
