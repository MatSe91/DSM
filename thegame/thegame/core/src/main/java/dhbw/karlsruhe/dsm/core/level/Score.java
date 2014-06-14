package dhbw.karlsruhe.dsm.core.level;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.TimeUtils;

import dhbw.karlsruhe.dsm.core.DSM;
public class Score {
	
	private String playerName;
	private int score;
	private long startTime;
	private long pauseTime;
	private Label labelScore;
	private Label ScoreValue;
	private static final String ScoreString = "SCORE |";
	private String ScoreValueString = "";
	
	private DSM game;
	private Stage stage;
	
	
	public Score(DSM game) {
		setPlayerName("");
		setScore(0);
		this.game = game;
		this.stage= new Stage();
	}
	
	public Score(String playerName, int score, DSM game) {
		this.setPlayerName(playerName);
		this.setScore(score);
		this.game = game;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		if(playerName.length() > 25) {
			playerName = playerName.substring(0, 49);
		}
		this.playerName = playerName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Compares this score with the given score<br>
	 * this = A; score = B
	 * @param score to compare with
	 * @return 
	 * A <  B => -1 <br>
	 * A == B =>  0 <br>
	 * A >  B => +1	<br>
	 */
	public int compare(Score score) {
		if(this.score > score.score) {
			return 1;
		} else if(this.score == score.score) {
			return 0;
		} else {
			return -1;
		}
	}
	
	public void start(){
		this.startTime=System.nanoTime();
	}
	public void pause(){
		this.pauseTime=System.nanoTime();
	}
	public void resume(){
		if(this.pauseTime!=0){
		this.startTime= this.startTime+(System.nanoTime()-this.pauseTime);
		this.pauseTime=0;
		}
	}
	public int stop(){
		
		return this.score;
	}
	public void draw(){
		stage.clear();
		this.score=(int) ((System.nanoTime()-this.startTime)/100000000);
		this.labelScore = game.screenHelper.createLabel(ScoreString, 625,550, 50, 0);
		this.labelScore.setZIndex(10);
		this.ScoreValueString = Integer.toString(this.score);
		this.ScoreValue = game.screenHelper.createLabel(ScoreValueString,720,550,50,0);
		stage.addActor(labelScore);
		stage.addActor(ScoreValue);
		stage.draw();
		
	}

}