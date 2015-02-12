package grid.boundedgrid;

import grid.AbstractXYGrid;
import grid.location.*;

import java.util.ArrayList;

import occupant.Occupant;

/**
 * A <code>BoundedGrid</code> is a rectangular grid with a finite number of rows and columns. It is
 * derived from the WatorWorld Starter code. <br />
 */

public class BoundedGrid<E> extends AbstractXYGrid<E> {

    private Object[][] occupantArray;
    private ArrayList<Location> myLocations;

    /**
     * Constructs an empty bounded grid with the given dimensions. (Precondition:
     * <code>rows > 0</code> and <code>cols > 0</code>.)
     * 
     * @param rows number of rows in BoundedGrid
     * @param cols number of columns in BoundedGrid
     */

    protected final int FIRST_ROW = 0;
    protected final int FIRST_COL = 0;
    protected final int LAST_ROW;
    protected final int LAST_COL;

    public BoundedGrid (int rows, int cols, String shape) {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        occupantArray = new Object[rows][cols];
        LAST_ROW = getNumRows() - 1;
        LAST_COL = getNumCols() - 1;
        myLocations = new ArrayList<>();
        CellShape = shape;
    }

    public int getNumRows () {
        return occupantArray.length;
    }

    public int getNumCols () {
        // Note: according to the constructor precondition, numRows() > 0, so
        // theGrid[0] is non-null.
        return occupantArray[0].length;
    }

    public void setLocations (ArrayList<Location> l) {
        myLocations = l;
    }

    public void addLocation (Location l) {
        myLocations.add(l);
    }

    public ArrayList<Location> getLocations () {
        return myLocations;
    }

    public boolean isValid (Location loc) {
        if (loc == null)
            return false;
        else
            return (0 <= loc.getRow() && loc.getRow() < getNumRows() && 0 <= loc.getCol() && loc
                    .getCol() < getNumCols());
    }

    public ArrayList<Location> getOccupiedLocations () {
        ArrayList<Location> theLocations = new ArrayList<Location>();
        for (Location l : myLocations){
            if (l.containsOccupant()) 
                theLocations.add(l);
            }
        
        return theLocations;
    }

    public ArrayList<Location> getUnoccupiedLocations () {
        ArrayList<Location> theLocations = new ArrayList<Location>();
        
//        for (Location l : myLocations) {
//            if (l.containsOccupant() == false)
//                theLocations.add(l);
//        }
        
        for (int r = 0; r < getNumRows(); r++)
            for (int c = 0; c < getNumCols(); c++) {
                Location loc = (Location) new SquareLocation (r, c);
                if (!myLocations.contains(loc))
                    theLocations.add(loc);
            }
        
        return theLocations;
    }

    public ArrayList<Location> getEdgeLocations () {
        ArrayList<Location> locs = new ArrayList<Location>();
        for (int r = 0; r < getNumRows(); r++) {
            for (int c = 0; c < getNumCols(); c++) {
                Location loc = new SquareLocation(r, c);
                if (isTopEdge(loc) || isLeftEdge(loc) || isBottomEdge(loc) || isRightEdge(loc))
                    locs.add(new SquareLocation(r, c));
            }
        }
        return locs;
    }

    public boolean isTopEdge (Location loc) {
        return loc.getRow() == FIRST_ROW;
    }

    public boolean isLeftEdge (Location loc) {
        return loc.getCol() == FIRST_COL;
    }

    public boolean isBottomEdge (Location loc) {
        return loc.getRow() == LAST_ROW;
    }

    public boolean isRightEdge (Location loc) {
        return loc.getRow() == LAST_COL;
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
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
    }

    public E put (E obj, Occupant o) {
        Location loc = (Location) obj;
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + o + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;

        loc.setOccupant(o);
        addLocation(loc);

        return oldOccupant;
    }

    public void clear () {
        for (Location loc : getOccupiedLocations()) {
            remove(loc);
        }
    }

    public E remove (Location loc) {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc + " is not valid");

        // Remove the object from the grid.
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
}
