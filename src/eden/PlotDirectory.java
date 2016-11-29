package eden;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class PlotDirectory{
	
	private final ArrayList<Plot> plotList;
	
	public PlotDirectory() {
		plotList = new ArrayList<>();
	}
	
	public ArrayList<Plot> getList() {
		return plotList;
	}
	
<<<<<<< HEAD
	public void loadPlotList() throws FileNotFoundException {
		File[] plots = new File("C:\\Users\\Maxwell\\Documents\\NetBeansProject\\EDEN\\plots").listFiles();
=======
	public void loadPlotList() throws FileNotFoundException, ParseException {
		File[] plots = new File("C:\\Users\\Layne\\workspace\\EDEN\\plots").listFiles();
		SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy");
>>>>>>> refs/remotes/origin/master
		
		for (File file : plots) {
			if (file.getName().endsWith(".txt")) {
				Scanner plotScanner = new Scanner(file);
				String name = plotScanner.next();
				int width = plotScanner.nextInt();
				int height = plotScanner.nextInt();
				String createDateString = plotScanner.next() + " " + plotScanner.next() + " " + plotScanner.next(); // MM DD YYYY
				String lastWateringDateString = plotScanner.next() + " " + plotScanner.next() + " " + plotScanner.next(); // MM DD YYYY
				Date createDate = sdf.parse(createDateString);
				Date lastWateringDate = sdf.parse(lastWateringDateString);
				plotList.add(new Plot(name, width, height, createDate, lastWateringDate));
				plotScanner.close();
			}
		}
	}
	
	public void addNewPlot(Plot currentPlot) {
		for (int i = 0; !currentPlot.getName().equals(plotList.get(i).getName()) && plotList.get(i) != null; i++) {
			if (plotList.get(i).getName() != currentPlot.getName())
				plotList.add(currentPlot);
			else
				return;
		}
	}

	public void removePlot(Plot plot) {
		for (int i = 0; !plot.getName().equals(plotList.get(i).getName()); i++) { //Finds the index of plot in the ArrayList<Plot>
			plotList.remove(i); //Removes plot and shifts left
		}
	}
	
	public void savePlotList() throws IOException {
		for (Plot plot : plotList) {
			String name = plot.getName();
			File f = new File("path/to/file/" + name + ".txt");
			f.createNewFile();
			FileWriter plotWriter = new FileWriter(f); 
			PrintWriter pW = new PrintWriter(plotWriter);
			pW.print(name + " ");
			pW.print(plot.getWidth() + " ");
			pW.print(plot.getLength() + " ");
			pW.print(plot.getCreationDate() + " ");
			pW.print(plot.getLastWatered());
			pW.println();
			pW.close();
		}
	}
}
