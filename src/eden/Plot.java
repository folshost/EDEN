package eden;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Plot {
	private String name;
	private int width, length;
	private Calendar creationDate;
	private Calendar lastWatered;
        private static SimpleDateFormat sdf = new SimpleDateFormat("MM,dd,yyyy");
	
	public Plot(String name, int width, int length, Calendar creationDate, Calendar lastWatered) {
		this.name = name;
		this.width = width;
		this.length = length;
		this.creationDate = creationDate;
		this.lastWatered = lastWatered;
	}
        
        public Plot(String name, int width, int length) {
                this.name = name;
		this.width = width;
		this.length = length;
		this.creationDate = Calendar.getInstance();
		this.lastWatered = Calendar.getInstance();
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
		return sdf.format(creationDate.getTime());
	}
	
	public String getLastWatered() {
		return sdf.format(lastWatered.getTime());
	}
}
