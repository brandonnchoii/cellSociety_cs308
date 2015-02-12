package grid.location;

import grid.Grid;
import occupant.Occupant;

public class HexagonLocation extends Location{

    /**
     * Turn angles for turning x degrees to specified direction
     */
    public static final int LEFT = -60;
    public static final int HALF_LEFT = -30;
    public static final int HALF_RIGHT = 30;
    public static final int AHEAD = 0;
    public static final int NORTHEAST = 45;
    public static final int EAST = 90;
    public static final int SOUTHEAST = 135;
    public static final int SOUTH = 180;
    public static final int SOUTHWEST = 225;
    public static final int WEST = 270;
    public static final int NORTHWEST = 315;

    /**
     * Constructs a location with given row and column coordinates.
     * 
     * @param r the row
     * @param c the column
     */
    
    public HexagonLocation (int i, int j) {
        super(i, j);
        RIGHT = 60; //Turning interval for accessing neighbors
    }

    public Location getAdjacentLocation (int direction, Grid g){
        int dx = 0;
        int dy = 0;
        if (direction != 0 && direction != 180){
            dx = ((0 < direction && direction < 180) ? 1 : -1);
        }
        if (direction != 90 && direction != 270){
            dy = ((90 < direction && direction <270) ? -1 : 1);
        }
        
        Location adjacent = null;
        Grid<Occupant> grid = (Grid<Occupant>) g;
        
        for (Location loc: grid.getLocations()){
            if (loc.getRow() == getRow() + dx && loc.getCol() == getCol() + dy)
                adjacent = loc;
        }
        
        return adjacent;
    }
    

    public int getDirectionToward (Location target) { //Need to implement correctly
        int dx = target.getCol() - getCol();
        int dy = target.getRow() - getRow();
        // y axis points opposite to mathematical orientation
        int angle = (int) Math.toDegrees(Math.atan2(-dy, dx));

        // mathematical angle is counterclockwise from x-axis,
        // compass angle is clockwise from y-axis
        int compassAngle = 90
                - angle;
        // prepare for truncating division by 45 degrees
//        compassAngle += HALF_RIGHT / 2;
//        // wrap negative angles
//        if (compassAngle < 0)
//            compassAngle += FULL_CIRCLE;
//        // round to nearest multiple of 45
//        return (compassAngle / HALF_RIGHT) * HALF_RIGHT;
        return 0;
    }
    
    public boolean equals (Object other) {
        if (!(other instanceof Location))
            return false;
        Location otherLoc = (Location) other;
        return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
    }
    
    /**
     * @return a hash code for this location
     */
    public int hashCode() {
        return getRow() * 2015 + getCol() * 3;
    }


    @Override
    public String toString () {
        return "(" + getRow() + ", " + getCol() + ")";
    }

//    /**
//     * Compares this location to <code>other</code> for ordering. Returns a
//     * negative integer, zero, or a positive integer as this location is less
//     * than, equal to, or greater than <code>other</code>. Locations are
//     * ordered in row-major order. <br />
//     * (Precondition: <code>other</code> is a <code>Location</code> object.)
//     * 
//     * @param other the other location to test
//     * @return a negative integer if this location is less than <code>other</code>, zero if the two
//     *         locations are equal, or a positive
//     *         integer if this location is greater than <code>other</code>
//     */
//    
//    
//    public int compareTo (Location other) {
//        Location otherLoc = (Location) other;
//        if (getRow() < otherLoc.getRow() || getCol() < otherLoc.getCol()){
//            return -1;
//        }
//        if (getRow() > otherLoc.getRow() || getCol() > otherLoc.getCol()){
//            return 1;
//        }
//        return 0;
//    }
    

}
