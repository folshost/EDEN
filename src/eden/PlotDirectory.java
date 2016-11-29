package eden;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class PlotDirectory{
	
	private final ArrayList<Plot> plotList;
        private String PLFileDirectory = System.getProperty("user.dir") + File.separator + 
                                    "plots";
	
	public PlotDirectory() {
		plotList = new ArrayList<>();
	}
	
	public ArrayList<Plot> getList() {
		return plotList;
	}
	
	public void loadPlotList() throws FileNotFoundException, ParseException {
                
                File PLDirectoryFile = new File(PLFileDirectory);
                if (!PLDirectoryFile.exists()) {
                    PLDirectoryFile.mkdirs();
                }
		File[] plots = PLDirectoryFile.listFiles();
		for (File file : plots) {
			if (file.getName().endsWith(".txt")) {
				Scanner plotScanner = new Scanner(file);
                                plotScanner.useDelimiter(",");
                                String name = plotScanner.next();
				int width = plotScanner.nextInt();
				int height = plotScanner.nextInt();
				int m = plotScanner.nextInt();
                                int d = plotScanner.nextInt();
                                int y = plotScanner.nextInt();
                                Calendar createDate = Calendar.getInstance();
                                createDate.set(y, m, d);
                                m = plotScanner.nextInt();
                                d = plotScanner.nextInt();
                                y = plotScanner.nextInt();
                                Calendar lastWateringDate = Calendar.getInstance();
                                lastWateringDate.set(y, m, d);
				plotList.add(new Plot(name, width, height, createDate, lastWateringDate));
				plotScanner.close();
			}
		}
	}
	
        public void addNewPlot(String name, int width, int length) {
            addNewPlot(new Plot(name, width, length));
        }
        
	public void addNewPlot(Plot newPlot) {
		for (int i = 0; plotList.get(i) != null && !newPlot.getName().equals(plotList.get(i).getName()); i++) {
			if (plotList.get(i).getName() != newPlot.getName())
				plotList.add(newPlot);
			else
				return;
		}
	}

	public void removePlot(Plot plot) {
		for (int i = 0; !plot.getName().equals(plotList.get(i).getName()); i++) { //Finds the index of plot in the ArrayList<Plot>
			plotList.remove(i); //Removes plot and shifts left
		}
                File PLDirectoryFile = new File(PLFileDirectory);
                PLDirectoryFile.delete();
                File PlotPlantFile = new File (System.getProperty("user.dir") + File.separator + 
                                               "plantLists" + File.separator + 
                                               plot.getName() + ".plantlist");
                PlotPlantFile.delete();
	}
	
	public void savePlotList() throws IOException {
		for (Plot plot : plotList) {
			String name = plot.getName();
			File f = new File(PLFileDirectory + File.separator + 
                                          name + ".txt");
			f.createNewFile();
			FileWriter plotWriter = new FileWriter(f); 
			PrintWriter pW = new PrintWriter(plotWriter);
			pW.print(name + ",");
			pW.print(plot.getWidth() + ",");
			pW.print(plot.getLength() + ",");
			pW.print(plot.getCreationDate() + ",");
			pW.print(plot.getLastWatered());
			pW.println();
			pW.close();
		}
	}
}
