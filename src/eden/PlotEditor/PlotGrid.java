package eden.PlotEditor;

public class PlotGrid {

    private final static double COSINE_OF_45DEG = 0.70710678118654752440084436210485;

    private final int width, length;
    private final int[][] plantSpacing;
    private final boolean[][] plantLocations;

    public PlotGrid(int width, int length) {
        this.width = width;
        this.length = length;
        plantSpacing = new int[width][length];
        plantLocations = new boolean[width][length];
    }

    /* 
    Below are the 4 public methods for manipulating the grid when adding,
    moving, and removing plants from a plot.
    */
    
    /**
     * Returns whether or not a plant can be placed at a specified location
     * given the spacing it requires.
     * 
     * @param x the X coordinate of the plant.
     * @param y the Y coordinate of the plant.
     * @param radius the spacing required by the plant that is to be added.
     * @return true if the plant can be planted there; otherwise, false.
     */
    public boolean isVacant(int x, int y, int radius) {
        return (plantSpacing[x][y] < 1 && // Check that this location isn't within another plant's required spacing
                !plantLocations[x][y] && // Check that there isn't a plant already located here
                traverseGrid(x, y, radius, (a, b) -> isSpaceAvailable(a, b))); // Check that there aren't any other plants within the specified radius
    }

    /**
     * Adds the plant's required spacing to the spacing grid and sets its 
     * location in the plantLocations grid to occupied.
     * 
     * @param x the X coordinate of the plant to be added to the grid.
     * @param y the Y coordinate of the plant to be added to the grid.
     * @param radius the spacing of the plant to be added.
     * @return true if the operation is successfully complete.
     */
    public boolean add(int x, int y, int radius) {
        plantLocations[x][y] = true;
        return traverseGrid(x, y, radius, (a, b) -> setSpaceUnavailable(a, b));
    }
    
    /**
     * Removes the plant's required spacing from the spacing grid and sets its 
     * location in the plantLocations grid to vacant.
     * 
     * @param x the X coordinate of the plant to be removed from the grid.
     * @param y the Y coordinate of the plant to be removed from the grid.
     * @param radius the spacing of the plant to be removed.
     * @return true if the operation is successfully complete.
     */
    public boolean remove(int x, int y, int radius) {
        plantLocations[x][y] = false;
        return traverseGrid(x, y, radius, (a, b) -> setSpaceAvailable(a, b));
    }

    /*
    Below are some helper methods for the methods above.
    */
    
    /**
     * Returns whether or not the specified location is available.
     * 
     * @param x the X coordinate of the location.
     * @param y the Y coordinate of the location.
     * @return true if the location is available; false if it is within a
     * plant's required spacing area.
     */
    private boolean isSpaceAvailable(int x, int y) {
        if (OutOfBounds(x, y)) {
            return true;
        } else {
            return !plantLocations[x][y];
        }
    }

    /**
     * Sets the specified location to unavailable due to the spacing requirement
     * of a plant.
     * 
     * @param x the X coordinate of the location.
     * @param y the Y coordinate of the location.
     * @return true when the operation is successful (always).
     */
    private boolean setSpaceUnavailable(int x, int y) {
        if (!OutOfBounds(x, y)) {
            plantSpacing[x][y]++;
        }
        return true;
    }
    
    /**
     * Increases the specified location's availability in regards to the spacing
     * requirements of other plants.
     * 
     * @param x the X coordinate of the location.
     * @param y the Y coordinate of the location.
     * @return true when the operation is successful (always).
     */
    private boolean setSpaceAvailable(int x, int y) {
        if (!OutOfBounds(x, y)) {
            plantSpacing[x][y]--;
        }
        return true;
    }

    private boolean OutOfBounds(int x, int y) {
        return x < 0 || y < 0 || x >= width || y >= length;
    }
    
    /*
    The methods below handle traversals of the grid.
    */

    /**
     * Traverse the plot grid within a specified radius from a given point,
     * performing a given operation at each point.
     *
     * @param x the X coordinate of the origin point from which to traverse
     * within a given radius.
     * @param y the Y coordinate of the origin point from which to traverse
     * within a given radius.
     * @param radius the radius from which to traverse.
     * @param gridOp the operation to perform on all points within a specified
     * radius from a given point.
     * @return false if the operation returns false for any point on which it
     * was performed; else, true.
     */
    private boolean traverseGrid(int x, int y, int radius, GridOperation gridOp) {
        gridOp.perform(x, y);
        return traverseNSEW(x, y, radius, gridOp) &&
               traverseDiagonal(x, y, radius, gridOp) &&
               traverseOctant(x, y, radius, gridOp);
    }

    /**
     * Traverse the plot grid directly north, south, east, and west of the
     * specified coordinates.
     *
     * @param x the X coordinate of the origin point from which to traverse
     * within a given radius.
     * @param y the Y coordinate of the origin point from which to traverse
     * within a given radius.
     * @param radius the radius from which to traverse.
     * @param gridOp the operation to perform on all points within a specified
     * radius from a given point.
     * @return false if the operation returns false for any point on which it
     * was performed; else, true.
     */
    private boolean traverseNSEW(int x, int y, int radius, GridOperation gridOp) {
        for (int i = radius; i > 0; i--) {
            if (!(gridOp.perform(x, y - i) && // North
                  gridOp.perform(x, y + i) && // South
                  gridOp.perform(x - i, y) && // West
                  gridOp.perform(x + i, y)))  // East
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Traverse the plot grid directly northwest, northeast, southwest, and
     * southeast of the specified coordinates.
     *
     * @param x the X coordinate of the origin point from which to traverse
     * within a given radius.
     * @param y the Y coordinate of the origin point from which to traverse
     * within a given radius.
     * @param radius the radius from which to traverse.
     * @param gridOp the operation to perform on all points within a specified
     * radius from a given point.
     * @return false if the operation returns false for any point on which it
     * was performed; else, true.
     */
    private boolean traverseDiagonal(int x, int y, int radius, GridOperation gridOp) {
        int distanceTimesCosineOf45;
        if (radius > 2) {
            distanceTimesCosineOf45 = (int) (radius * COSINE_OF_45DEG);
        } else {
            distanceTimesCosineOf45 = radius;
        }
        
        for (int i = radius; i > 0; i--) {
            if (!(gridOp.perform(x - i, y - i) && // Northwest
                  gridOp.perform(x + i, y - i) && // Northeast
                  gridOp.perform(x - i, y + i) && // Southwest
                  gridOp.perform(x + i, y + i)))  // Southwest
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Traverse the plot grid surrounding the specified coordinates, excluding
     * the spaces in any of the cardinal and intercardinal directions from the
     * given coordinates.
     *
     * @param x the X coordinate of the origin point from which to traverse
     * within a given radius.
     * @param y the Y coordinate of the origin point from which to traverse
     * within a given radius.
     * @param radius the radius from which to traverse.
     * @param gridOp the operation to perform on all points within a specified
     * radius from a given point.
     * @return false if the operation returns false for any point on which it
     * was performed; else, true.
     */
    private boolean traverseOctant(int x, int y, int radius, GridOperation gridOp) {
        /* 
        I came up with 2 different solutions for determining what grid cells are
        within a given radius around a given point. 
        */
        return traverseOctantDistance(x, y, radius, gridOp);
//        return traverseOctantPartialSums(x, y, radius, gridOp);
    }
    
    /*
    Below are three algorithms for traversing the circle around a given point on
    the grid.
    */
    
    /*
    This algorithm uses the distance formula, or the Pythagoreans theorem:
    a = sqrt( c^2 - b^2 ) 
    */
    private boolean traverseOctantDistance(int x, int y, int radius, GridOperation gridOp) {
        if (radius > 1) {
            int distanceTimesCosineOf45;
            if (radius > 2) {
                distanceTimesCosineOf45 = (int) (radius * COSINE_OF_45DEG);
            } else {
                distanceTimesCosineOf45 = radius;
            }
            int spacingSquared = radius * radius; 
            // loop from axis towards diagonal
            for (int i = 1; i <= distanceTimesCosineOf45; i++) {
                // loop along axis
                for (int j = (int) Math.sqrt(spacingSquared - i * i); j > i; j--) {
                    if (!(gridOp.perform(x - i, y - j) && // North-Northwest
                          gridOp.perform(x - j, y - i) && // West-Northwest
                          gridOp.perform(x + i, y - j) && // North-Northeast
                          gridOp.perform(x + j, y - i) && // East-Northeast
                          gridOp.perform(x - i, y + j) && // South-Southwest
                          gridOp.perform(x - j, y + i) && // West-Southwest
                          gridOp.perform(x + i, y + j) && // South-Southeast
                          gridOp.perform(x + j, y + i)))  // East-Southeast
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    /*
    This alorithm doesn't use sqrt, so it may be faster. I've yet to 
    experimentally or theoretically analyze it to find out.
    */
    private boolean traverseOctantPartialSums(int x, int y, int radius, GridOperation gridOp) {
        if (radius > 1) {
            int distanceTimesCosineOf45;
            if (radius > 2) {
                distanceTimesCosineOf45 = (int) (radius * COSINE_OF_45DEG);
            } else {
                distanceTimesCosineOf45 = radius;
            }
            int cOCoefficient = 2 * (radius - distanceTimesCosineOf45) / ( distanceTimesCosineOf45 * (distanceTimesCosineOf45 - 1) ); 
            int curveOffset = 0;
            // loop from axis towards diagonal
            for (int i = 1; i <= distanceTimesCosineOf45; i++) {
                // loop along axis
                for (int j = radius - cOCoefficient * curveOffset; j > i; j--) {
                    if (!(gridOp.perform(x - i, y - j) && // North-Northwest
                          gridOp.perform(x - j, y - i) && // West-Northwest
                          gridOp.perform(x + i, y - j) && // North-Northeast
                          gridOp.perform(x + j, y - i) && // East-Northeast
                          gridOp.perform(x - i, y + j) && // South-Southwest
                          gridOp.perform(x - j, y + i) && // West-Southwest
                          gridOp.perform(x + i, y + j) && // South-Southeast
                          gridOp.perform(x + j, y + i)))  // East-Southeast
                    {
                        return false;
                    }
                }
                curveOffset += i;
            }
        }
        return true;
    }
    
    /**
     * The interface for the grid operations that are passed through grid 
     * traversal methods to the points on the grid traversed.
     */
    private interface GridOperation {

        /**
         * The operation to perform on each location accessed by
         * performGridOperation(...).
         *
         * @param x the X coordinate of the location
         * @param y the Y coordinate of the location
         * @return true if the operation is successful
         */
        boolean perform(int x, int y);
    }
    
    /* Below are the various accessor methods */
    
    /**
     * Returns the width of the plot grid.
     * @return the width of the plot grid.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the length of the plot grid.
     * @return the length of the plot grid.
     */
    public int getLength() {
        return length;
    }
    
    /**
     * Returns the 2D array that represents all areas on the plot on which a plant
     * cannot be added due to spacing requirements of existing plants.
     * 
     * @return Two-dimensional array representing the plot
     */
    public int[][] getPlantSpacing() {
        return plantSpacing;
    }
    
    /**
     * Returns the 2D array that represents all locations in the plot where a
     * plant is located.
     * 
     * @return Two-dimensional array representing the plot
     */
    public boolean[][] getPlantLocations() {
        return plantLocations;
    }
    
}
