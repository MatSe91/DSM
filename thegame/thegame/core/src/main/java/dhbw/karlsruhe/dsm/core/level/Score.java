package dhbw.karlsruhe.dsm.core.level;

public class Score {
	
	private String playerName;
	private int score;
	
	public Score() {
		setPlayerName("");
		setScore(0);
	}
	
	public Score(String playerName, int score) {
		this.setPlayerName(playerName);
		this.setScore(score);
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
	
}
