package dhbw.karlsruhe.dsm.core.level;

import java.util.List;

public class Pattern {
	private int originX;
	private int originY;
	private int height;
	private int width;
	
	private List<PatternPrototype> availablePrototypes;
	
	public Pattern(int originX, int originY) {
		this.originX = originX;
		this.originY = originY;
		
		//this.pP = new PatternPrototype();
	}
	
	private void loadPrototypes() {
		
	}
	
	public int getOriginX() {
		return originX;
	}
	
	public void setOriginX(int originX) {
		this.originX = originX;
	}
	
	public int getOriginY() {
		return originY;
	}
	
	public void setOriginY(int originY) {
		this.originY = originY;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
}
