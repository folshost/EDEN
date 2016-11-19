package eden;

import java.util.Date;

public class Plot {
	private String name;
	private int width;
	private int height;
	private Date date;
	private Date lastWatered;
	
	public Plot(String name, int width, int height, Date date, Date lastWatered) {
		this.name = name;
		this.width = width;
		this.height = height;
		this.date = date;
		this.lastWatered = lastWatered;
	}
	
	public String getName() {
		return name;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public String getDate() {
		return date.getDay() + " " + date.getMonth() + " " + date.getYear();
	}
	
	public String getLastWatered() {
		return lastWatered.getDay() + " " + lastWatered.getMonth() + " " + date.getYear();
	}
}
