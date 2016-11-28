package eden;

import java.util.Date;

public class Plot {
	private String name;
	private int width, length;
	private Date creationDate;
	private Date lastWatered;
	
	public Plot(String name, int width, int length, Date creationDate, Date lastWatered) {
		this.name = name;
		this.width = width;
		this.length = length;
		this.creationDate = creationDate;
		this.lastWatered = lastWatered;
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
		return creationDate.getDay() + " " + creationDate.getMonth() + " " + creationDate.getYear();
	}
	
	public String getLastWatered() {
		return lastWatered.getDay() + " " + lastWatered.getMonth() + " " + creationDate.getYear();
	}
}
