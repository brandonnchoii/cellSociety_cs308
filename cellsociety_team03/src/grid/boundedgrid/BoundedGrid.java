package grid.boundedgrid;

import grid.AbstractXYGrid;
import grid.Location;
import java.util.ArrayList;


/**
 * A <code>BoundedGrid</code> is a rectangular grid with a finite number of
 * rows and columns. It is derived from the WatorWorld
 * Starter code. <br />
 */

public class BoundedGrid<E> extends AbstractXYGrid<E> {

    private Object[][] occupantArray;

    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * 
     * @param rows number of rows in BoundedGrid
     * @param cols number of columns in BoundedGrid
     */
    
    protected final int FIRST_ROW = 0;
    protected final int FIRST_COL = 0;
    protected final int LAST_ROW = getNumRows()-1;
    protected final int LAST_COL = getNumCols()-1;
    
    public BoundedGrid (int rows, int cols) {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        occupantArray = new Object[rows][cols];
    }

    public int getNumRows () {
        return 10;
        //return occupantArray.length;
    }

    public int getNumCols () {
        // Note: according to the constructor precondition, numRows() > 0, so
        // theGrid[0] is non-null.
        return 10;
        //return occupantArray[0].length;
    }

    public boolean isValid (Location loc) {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
               && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations () {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++) {
            for (int c = 0; c < getNumCols(); c++) {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }
        return theLocations;
    }
    
    public ArrayList<Location> getUnoccupiedLocations () {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++) {
            for (int c = 0; c < getNumCols(); c++) {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) == null)
                    theLocations.add(loc);
            }
        }
        return theLocations;
    }

    public ArrayList<Location> getEdgeLocations () {
        ArrayList<Location> locs = new ArrayList<Location>();
        for (int r = 0; r < getNumRows(); r++) {
            for (int c = 0; c < getNumCols(); c++) {
                Location loc = new Location (r,c);
                if (isTopEdge(loc) || isLeftEdge(loc) || isBottomEdge(loc) || isRightEdge(loc))
                    locs.add(new Location(r, c));
            }
        }
        return locs;
    }
    
    public boolean isTopEdge(Location loc){
        return loc.getRow()==FIRST_ROW;
    }
    
    public boolean isLeftEdge(Location loc){
        return loc.getCol()==FIRST_COL;
    }
    
    public boolean isBottomEdge(Location loc){
        return loc.getRow()==LAST_ROW ;
    }
    
    public boolean isRightEdge(Location loc){
        return loc.getRow()==LAST_COL ;
    }
    
    public ArrayList<Location> getCornerLocations () {
        ArrayList<Location> locs = new ArrayList<Location>();
        for (Location loc : getEdgeLocations()) {
            if (isTopLeftCorner(loc) || isTopRightCorner(loc) || isBottomLeftCorner(loc) ||
                isBottomRightCorner(loc))
                locs.add(loc);
        }
        return locs;
    }

    public boolean isTopLeftCorner (Location loc) {
        return (loc.getRow() == FIRST_ROW && loc.getCol() == FIRST_COL);
    }

    public boolean isTopRightCorner (Location loc) {
        return (loc.getRow() == FIRST_ROW && loc.getCol() == LAST_COL);
    }

    public boolean isBottomLeftCorner (Location loc) {
        return (loc.getRow() == LAST_ROW && loc.getCol() == FIRST_COL);
    }

    public boolean isBottomRightCorner (Location loc) {
        return (loc.getRow() == LAST_ROW && loc.getCol() == LAST_COL);
    }

    public E get (Location loc) {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                                               + " is not valid");
        return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
    }

    public E put (Location loc, E obj) {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                                               + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }
    public void clear(){
        for (Location loc : getOccupiedLocations()){
            remove(loc);
        }
    }

    public E remove (Location loc) {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                                               + " is not valid");

        // Remove the object from the grid.
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
}
