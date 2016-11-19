import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class PlotDirectory {
	
	public PlotDirectory() {
		plotList = new ArrayList<Plot>;
	}
	
	ArrayList<Plot> plotList;
	
	public void loadPlotList() {
		File[] plots = new File("path/to/file").listFiles();
		
        for(File file : plots) {
            if (file.getName().endsWith(".txt")) {
            	Scanner in = new Scanner(file);
            	plotList.add(new Plot(plotScanner.next(), plotScanner.nextInt(), plotScanner.nextInt(), plotScanner.nextInt(), plotScanner.nextInt()));
            }
        }
	}
	
	public void addNewPlot(Plot currentPlot) {
        for(int i = 0; !currentPlot.getName().equals(plotList.getIndex(i).getName()) && plotList.getIndex(i) != null; i++)
        if (plotList.getIndex(i).getName() != currentPlot.getName())
		    plotList.add(currentPlot);
        else
            //Failed to add plot b/c name already taken
	}
  
    public void removePlot(Plot plot) {
        for (int i = 0; !plot.getName().equals(plotList.getIndex(i).getName()); i++) //Finds the index of plot in the ArrayList<Plot>
        plotList.remove(i); //Removes plot and shifts left
        
    }
	
	public static void savePlotList() {
		for(Plot plot : plotList) {
            File f = new File("path/to/file/" + plot.getName() + ".txt");
            f.createNewFile();
            FileWriter plotWriter = new FileWriter(f); 
		    PrintWriter pW = new PrintWriter(plotWriter);
			pW.print(plot.getName() + " ");
			pW.print(plot.getWidth() + " ");
			pW.print(plot.getHeight() + " ");
			pW.print(plot.getCreationDate() + " ");
			pW.print(plot.getLastWatered());
			pW.println();
		}
	}
}
