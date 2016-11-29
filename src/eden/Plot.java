package eden;

public class Plot {
	private String name;
	private int width, length;
	private int monthC;
	private int dayC;
	private int yearC;
	private int monthW;
	private int dayW;
	private int yearW;
	
	public Plot(String name, int width, int length, int monthC, int dayC, int yearC, int monthW, int dayW, int yearW) {
		this.name = name;
		this.width = width;
		this.length = length;
		this.monthC = monthC;
		this.dayC = dayC;
		this.yearC = yearC;
		this.monthW = monthW;
		this.dayW = dayW;
		this.yearW = yearW;
	}
	
	public String getName() {
		return name;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getLength() {
		return length;
	}
	
	public String getCreationDate() {
		return monthC + " " + dayC + " " + yearC;
	}
	
	public String getLastWatered() {
		return monthW + " " + dayW + " " + yearW;
	}
}
