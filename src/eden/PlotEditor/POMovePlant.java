package eden.PlotEditor;

import java.util.ArrayList;

public class POMovePlant implements PlantOperation {
    private final Plant plant;
    private final PlotGrid plotGrid;
    private final int oldX, oldY, newX, newY, spacing;
    private final ArrayList<Plant> plantList;
    
    public POMovePlant(ActivePlot plot, Plant plant, int newX, int newY) {
        this.plant = plant;
        this.plotGrid = plot.getPlotGrid();
        this.oldX = plant.getXCoordinate();
        this.oldY = plant.getYCoordinate();
        this.newX = newX;
        this.newY = newY;
        this.spacing = plant.getSpacing();
        this.plantList = plot.getPlantList();
    }
    
    @Override
    public boolean execute() {
        if (plotGrid.isVacant(newX, newY, spacing)) {
            plotGrid.remove(oldX, oldY, spacing);
            plotGrid.add(newX, newY, spacing);
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public boolean undo() {
        plotGrid.remove(newX, newY, spacing);
        plotGrid.add(oldX, oldY, spacing);
        return true;
    }
}
