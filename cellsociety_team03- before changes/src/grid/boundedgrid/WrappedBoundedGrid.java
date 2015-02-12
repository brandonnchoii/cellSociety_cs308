package grid.boundedgrid;

import grid.Location;
import java.util.ArrayList;


public class WrappedBoundedGrid<E> extends BoundedGrid<E> {
    
    private final int FIRST_INTERIOR_ROW = 1;
    private final int FIRST_INTERIOR_COL = 1;
    private final int LAST_INTERIOR_ROW = getNumRows()-2;
    private final int LAST_INTERIOR_COL = getNumCols()-2;
    
    public WrappedBoundedGrid (int rows, int cols) {
        super(rows, cols);
    }

    /**
     * Assigns appropriate adjacent locations to corners and edges
     * Assumes that all 8 neighbors are desired 
     */
    @Override
    public ArrayList<Location> handleSpecialLocations (Location loc) {
        ArrayList<Location> locs = new ArrayList<Location>();
        if (getCornerLocations().contains(loc)) {
            for (Location l : getCornerLocations()) {
                locs.add(l);
                locs.remove(loc); // don't add self
            }
            if (isTopLeftCorner(loc)) {
                locs.add(new Location(FIRST_INTERIOR_ROW, LAST_COL));
                locs.add(new Location(LAST_ROW, FIRST_INTERIOR_COL));
            }
            else if (isTopRightCorner(loc)) {
                locs.add(new Location(FIRST_INTERIOR_ROW, FIRST_COL));
                locs.add(new Location(LAST_ROW, LAST_INTERIOR_COL));
            }
            else if (isBottomLeftCorner(loc)) {
                locs.add(new Location(FIRST_ROW, FIRST_INTERIOR_COL));
                locs.add(new Location(LAST_INTERIOR_ROW, LAST_COL));
            }
            else if (isBottomRightCorner(loc)) {
                locs.add(new Location(FIRST_ROW, LAST_INTERIOR_COL));
                locs.add(new Location(LAST_INTERIOR_ROW, FIRST_COL));
            }
        }
        else if (getEdgeLocations().contains(loc)) {
            if (isTopEdge(loc)) {
                locs.add(new Location(LAST_ROW, loc.getCol() - 1));
                locs.add(new Location(LAST_ROW, loc.getCol()));
                locs.add(new Location(LAST_ROW, loc.getCol() + 1));
            }
            else if (isLeftEdge(loc)) {
                locs.add(new Location(loc.getRow() - 1, LAST_COL));
                locs.add(new Location(loc.getRow(), LAST_COL));
                locs.add(new Location(loc.getRow() - 1, LAST_COL));
            }
            else if (isBottomEdge(loc)) {
                locs.add(new Location(FIRST_ROW, loc.getCol() - 1));
                locs.add(new Location(FIRST_ROW, loc.getCol()));
                locs.add(new Location(FIRST_ROW, loc.getCol() + 1));
            }
            else if (isRightEdge(loc)) {
                locs.add(new Location(loc.getRow() - 1, FIRST_COL));
                locs.add(new Location(loc.getRow(), FIRST_COL));
                locs.add(new Location(loc.getRow() - 1, FIRST_COL));
            }
        }
        return locs;
    }
}
