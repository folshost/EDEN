package eden.PlotEditor;

import eden.Plot;
import eden.PlotEditor.Plant.PlantType;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ActivePlot {
    
    private final Plot currentPlot;
    private final PlotGrid plotGrid;
    private final ArrayList<Plant> plantList = new ArrayList<>();
    private final OperationHistory opHistory = new OperationHistory();
    private String PLFilePath;
    
    public ActivePlot(Plot plot) {
        
        currentPlot = plot;
        plotGrid = new PlotGrid(plot.getWidth(), plot.getLength());
        PLFilePath = System.getProperty("user.dir") + File.separator + 
                     "plantlists" + File.separator + 
                     currentPlot.getName() + ".plantlist";
        try {
            loadPlants();
        } catch (IOException ex) {
            Logger.getLogger(ActivePlot.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void loadPlants() throws IOException {
        File PLFile = new File(PLFilePath);
        if (!PLFile.getParentFile().exists()) {
            PLFile.getParentFile().mkdirs();
        }
        PLFile.createNewFile();
        Scanner PLScanner = new Scanner(PLFile);
        String regEx = "([A-Z a-z]+),(\\d),(\\d)";
        Pattern pattern = Pattern.compile(regEx);
        Matcher m;
        while (PLScanner.hasNext(pattern)) {
            m = pattern.matcher(PLScanner.next(pattern));
            String type = m.group(0);
            int x = Integer.parseInt(m.group(1));
            int y = Integer.parseInt(m.group(2));
            loadPlant(type, x, y);
        }
    }
    
    public void savePlants() throws IOException {
        File PLFile = new File(PLFilePath);
        if (!PLFile.getParentFile().exists()) {
            PLFile.getParentFile().mkdirs();
        }
        PLFile.createNewFile();
        FileWriter plantListFW = new FileWriter(PLFile);
        try (PrintWriter plantListPW = new PrintWriter(plantListFW)) {
            for (Plant p : plantList) {
                plantListPW.printf("%s,%d,%d%n", 
                                   p.getTypeName(),
                                   p.getXCoordinate(),
                                   p.getYCoordinate());
            }
        }
    }
    
    private boolean loadPlant(String plantType, int x, int y) {
        PlantType type = PlantType.POTATOES;
        for (PlantType pt : Plant.getPlantCatalog()) {
            if (plantType.equals(pt.getName())) {
                type = pt;
                break;
            }
        }
        Plant newPlant = new Plant(type, x, y);
        PlantOperation addPlant = new POAddPlant(this, newPlant);
        return addPlant.execute();
    }
    
    public boolean AddPlant(PlantType type, int x, int y) {
        Plant newPlant = new Plant(type, x, y);
        PlantOperation addPlant = new POAddPlant(this, newPlant);
        if (addPlant.execute()) {
            opHistory.addStep(addPlant);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean RemovePlant(Plant plant) {
        PlantOperation removePlant = new PORemovePlant(this, plant);
        if (removePlant.execute()) {
            opHistory.addStep(removePlant);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean MovePlant(Plant plant, int newX, int newY) {
        PlantOperation movePlant = new POMovePlant(this, plant, newX, newY);
        if (movePlant.execute()) {
            opHistory.addStep(movePlant);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean Redo() {
        return opHistory.stepForward();
    }
    
    public boolean Undo() {
        return opHistory.stepForward();
    }
    
    public OperationHistory getHistory() {
        return opHistory;
    }
    
    public PlotGrid getPlotGrid() {
        return plotGrid;
    }
    
    public ArrayList<Plant> getPlantList() {
        return plantList;
    }
    
}
