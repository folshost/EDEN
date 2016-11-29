package eden;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Plot {
	private String name;
	private int width, length;
	private Date creationDate;
	private Date lastWatered;
	private SimpleDateFormat sdf;
	
	public Plot(String name, int width, int length, Date creationDate, Date lastWatered) {
		this.name = name;
		this.width = width;
		this.length = length;
		this.creationDate = creationDate;
		this.lastWatered = lastWatered;
		sdf = new SimpleDateFormat("MM dd yyyy");
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
		return sdf.format(creationDate);
	}
	
	public String getLastWatered() {
		return sdf.format(lastWatered);
	}
}
