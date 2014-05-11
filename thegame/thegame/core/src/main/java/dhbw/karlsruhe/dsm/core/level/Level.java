package dhbw.karlsruhe.dsm.core.level;

public class Level {
	
	private String name;
	private int index;
	
	public Level() {
		// this constructor is required for JSON object serialization
		name = "";
		index = 0;
	}
	
	public Level(String name, int index) {
		this.setName(name);
		this.setIndex(index);
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
	
}
