package dhbw.karlsruhe.dsm.core.level;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import dhbw.karlsruhe.dsm.config.ConfigurationConstants;

public final class ScoreDAO {
	private ScoreDAO() {
		
	}
	
	
	/**
	 * Loads locally stored Scores of a given Level
	 * @param level to fetch scores from
	 * @return sorted Scorelist
	 */
	public static List<Score> loadScores(Level level) {
		Json json = new Json();
		FileHandle handle = Gdx.files.local(ConfigurationConstants.PATH_SCORE_DIRECTORY + "/" + level.getName());
		String jsonString = handle.readString();
		List<Score> scoreList = json.fromJson(List.class, jsonString);
		if(scoreList.size() > 10) {
			scoreList = scoreList.subList(0, 9);
		}
		return sortScoreList(scoreList);
	}
	
	
	/**
	 * Saves the passed List of Scores into a local file
	 * @param scoreList to store
	 * @param level of the scores
	 */
	public static void saveScores(List<Score> scoreList, Level level) {
		if(scoreList.size() > 10) {
			scoreList = scoreList.subList(0, 9);
		}
		Json json = new Json();
		FileHandle handle = Gdx.files.local(ConfigurationConstants.PATH_SCORE_DIRECTORY + "/" + level.getName());
		String jsonString = json.toJson(scoreList, List.class);
		handle.writeString(jsonString, false);
		return;
	}
	
	private static List<Score> sortScoreList(List<Score> scoreList) {
		List<Score> resultList = new ArrayList<Score>();
		resultList.add(scoreList.get(0));
		
		int j = 0;
		
		for(int i = 1; i < scoreList.size(); i++) {
			while(j < resultList.size()) {
				
				if (scoreList.get(i).compare(resultList.get(j)) > 0) {
					resultList.add(j, scoreList.get(i));
					break;
				} else {
					j++;
					if(j == resultList.size()) {
						resultList.add(scoreList.get(i));
						break;
					}
				}
				
			}
			j = 0;
		}
		
		return resultList;
	}
	
	public static void createTestScores(Level level) {
		List<Score> scoreList = new ArrayList<Score>();
		scoreList.add(new Score("Player 1", 2500));
		scoreList.add(new Score("Player 2", 25000));
		scoreList.add(new Score("Player 4", 250));
		scoreList.add(new Score("Player 3", 2700));
		scoreList.add(new Score("Player 5", 2345));
		scoreList.add(new Score("Player 6", 25000));
		scoreList.add(new Score("Player 7", 6345));
		scoreList.add(new Score("Player 8", 26455));
		scoreList.add(new Score("Player 7", 6345));
		scoreList.add(new Score("Player 8", 26455));
		saveScores(scoreList, level);
	}
}
