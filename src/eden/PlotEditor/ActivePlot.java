package eden.PlotEditor;

import eden.Plot;
import eden.PlotEditor.Plant.PlantType;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class ActivePlot {
    private final Plot currentPlot;
    private final PlotGrid plotGrid;
    private final ArrayList<Plant> plantList = new ArrayList<>();
    private final OperationHistory opHistory = new OperationHistory();
    
    public ActivePlot(Plot plot) {
        currentPlot = plot;
        plotGrid = new PlotGrid(plot.getWidth(), plot.getLength());
    }
    
    public void loadPlot() {
        // to be implemented
    }
    
    public void savePlot() {
        // to be implemented
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
