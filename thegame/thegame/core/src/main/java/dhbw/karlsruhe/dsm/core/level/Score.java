package dhbw.karlsruhe.dsm.core.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import dhbw.karlsruhe.dsm.core.DSM;
public class Score implements Json.Serializable {
	
	private String playerName;
	private int score;
	private long startTime;
	private long pauseTime;
	private static final String SCORE_STRING = "SCORE | ";
	
	private DSM game;
	
	
	public Score() {
		this("", 0);
	}
	
	public Score(String playerName, int score) {
		this.setPlayerName(playerName);
		this.setScore(score);
		this.game = (DSM) Gdx.app.getApplicationListener();
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
	public void draw(SpriteBatch batch){
		this.score=(int) ((System.nanoTime()-this.startTime)/100000000);
		game.buttonFont.draw(batch, SCORE_STRING + score, 625, game.getHeight()-10);	
	}

	@Override
	public void write(Json json) {
		json.writeValue("playerName", playerName);
		json.writeValue("score", score);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		setPlayerName(json.readValue("playerName", String.class, jsonData));
		setScore(json.readValue("score", int.class, jsonData));
	}

}