package grid;

import java.util.ArrayList;
import grid.location.*;

/**
 * <code>AbstractXYGrid</code> contains the methods that are common to rectangular grid
 * implementations. It is derived from the WatorWorld Starter code. <br />
 * 
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 * @author Nathan Prabhu
 */
public abstract class AbstractXYGrid<E> implements Grid<E> {
    protected String CellShape;
    
    public ArrayList<E> getNeighbors (Location loc) {
        ArrayList<E> neighbors = new ArrayList<E>();
        for (Location neighborLoc : getOccupiedAdjacentLocations(loc))
            neighbors.add(get(neighborLoc));
        return neighbors;
    }

    // TODO: implement flexible way to get 4 neighbors and 8 neighbors
    // RIGHT = 4 , HALF_RIGHT = 8;
    // implement way to get diagonal neighbors
    public ArrayList<Location> getValidAdjacentLocations (Location loc) { //FIX THIS ASAP!!!
        ArrayList<Location> locs = new ArrayList<Location>();
        locs.addAll(handleSpecialLocations(loc));
        int d = Location.NORTH;
        for (int i = 0; i < Location.FULL_CIRCLE /Location.RIGHT; i++) {
            Location neighborLoc = loc.getAdjacentLocation(d, this);
            if (isValid(neighborLoc))
                locs.add(neighborLoc);
            d = d + Location.RIGHT;
        }
        return locs;
    }

    /**
     * Handles locations that don't conform to basic rules in
     * getValidAdjacentLocations (e.g. Corners and Edges in WrappedBoundedGrid)
     * 
     * @param loc a location in this grid
     * @return void
     */
    protected ArrayList<Location> handleSpecialLocations (Location loc) {
        return new ArrayList<Location>();
    }

    public ArrayList<Location> getEmptyAdjacentLocations (Location loc) {
        ArrayList<Location> locs = new ArrayList<Location>();
        for (Location neighborLoc : getValidAdjacentLocations(loc)) {
            if (loc.getOccupant() == null)
                locs.add(neighborLoc);
        }
        return locs;
    }

    public ArrayList<Location> getOccupiedAdjacentLocations (Location loc) {
        ArrayList<Location> locs = new ArrayList<Location>();
        for (Location neighborLoc : getValidAdjacentLocations(loc)) {
            if (neighborLoc.containsOccupant())
                locs.add(neighborLoc);
        }
        return locs;
    }
    
    public ArrayList<E> getAllOccupants (){
        ArrayList<E> list = new ArrayList<E>();
        for (Location loc: getOccupiedLocations()){
            list.add(get(loc));
        }
        return list;
    }

    /**
     * Creates a string that describes this grid.
     * 
     * @return a string with descriptions of all objects in this grid (not
     *         necessarily in any particular order), in the format {loc=obj, loc=obj,
     *         ...} 
     */
    public String toString () {
        String s = "{";
        for (Location loc : getOccupiedLocations()) {
            if (s.length() > 1)
                s += ", ";
            s += loc + "=" + get(loc);
        }
        return s + "}";
    }
    
    public String getCellShape(){
        return CellShape;
    }
    
    public void setCellShape(String s){
        CellShape = s;
    }
}
