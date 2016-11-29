package eden.PlotEditor;

import java.util.ArrayList;

public class POAddPlant implements PlantOperation {
    private final Plant plant;
    private final PlotGrid plotGrid;
    private final int x, y, spacing;
    private final ArrayList<Plant> plantList;
    
    public POAddPlant(ActivePlot plot, Plant plant) {
        this.plant = plant;
        this.plotGrid = plot.getPlotGrid();
        this.x = plant.getXCoordinate();
        this.y = plant.getYCoordinate();
        this.spacing = plant.getSpacing();
        this.plantList = plot.getPlantList();
    }
    
    @Override
    public boolean execute() {
        if (plotGrid.isVacant(x, y, spacing)) {
            plotGrid.add(x, y, spacing);
            plantList.add(plant);
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public boolean undo() {
        plotGrid.remove(x, y, spacing);
        plantList.remove(plant);
        return true;
    }
}
