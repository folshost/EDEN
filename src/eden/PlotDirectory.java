package eden;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class PlotDirectory{
	
	public PlotDirectory() {
		plotList = new ArrayList<Plot>();
	}
	
	ArrayList<Plot> plotList;
	
	public ArrayList<Plot> getList() {
		return plotList;
	}
	
	public void loadPlotList() throws FileNotFoundException {
		File[] plots = new File("C:\\path\\to\\files").listFiles();
		
        for(File file : plots) {
            if (file.getName().endsWith(".txt")) {
            	Scanner plotScanner = new Scanner(file);
            	plotList.add(new Plot(plotScanner.next(), plotScanner.nextInt(), plotScanner.nextInt(), new Date(plotScanner.nextInt(), plotScanner.nextInt(), plotScanner.nextInt(), 0, 0), new Date(plotScanner.nextInt(), plotScanner.nextInt(), plotScanner.nextInt(), 0, 0)));
            }
        }
	}
	
	public void addNewPlot(Plot currentPlot) {
        for(int i = 0; !currentPlot.getName().equals(plotList.get(i).getName()) && plotList.get(i) != null; i++)
        	
        if (plotList.get(i).getName() != currentPlot.getName())
		    plotList.add(currentPlot);
        else
            return;
	}
  
    public void removePlot(Plot plot) {
        for (int i = 0; !plot.getName().equals(plotList.get(i).getName()); i++) //Finds the index of plot in the ArrayList<Plot>
        plotList.remove(i); //Removes plot and shifts left
        
    }
	
	public void savePlotList() throws IOException {
		for(Plot plot : plotList) {
			String name = plot.getName();
            File f = new File("path/to/file/" + name + ".txt");
            f.createNewFile();
            FileWriter plotWriter = new FileWriter(f); 
		    PrintWriter pW = new PrintWriter(plotWriter);
			pW.print(name + " ");
			pW.print(plot.getWidth() + " ");
			pW.print(plot.getHeight() + " ");
			pW.print(plot.getDate() + " ");
			pW.print(plot.getLastWatered());
			pW.println();
		}
	}
}