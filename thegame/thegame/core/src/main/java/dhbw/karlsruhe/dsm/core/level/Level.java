package dhbw.karlsruhe.dsm.core.level;

import java.util.ArrayList;
import java.util.List;

public class Level {
	
	private String name;
	private int index;
	private List<PatternPrototype> availablePatterns;
	
	public Level() {
		// this constructor is required for JSON object serialization
		name = "";
		index = 0;
	}
	
	public Level(String name, int index) {
		this.name = name;
		this.index = index;
	}
	
	public Pattern getRandomPattern() {
		int index = (int) (Math.random() * availablePatterns.size());
		return availablePatterns.get(index).createPattern();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<PatternPrototype> getAvailablePatterns() {
		return availablePatterns;
	}

	public void setAvailablePatterns(ArrayList<PatternPrototype> availablePatterns) {
		this.availablePatterns = availablePatterns;
	}
	
}
