package grid.location;

import grid.Grid;
import occupant.Occupant;

/**
 * A <code>Location</code> object represents the row and column of a location
 * in a two-dimensional grid. <br />
 * 
 * @author Alyce Brady
 * @author Chris Nevison
 * @author APCS Development Committee
 * @author Cay Horstmann
 * @author Nathan Prabhu
 * @author Catherine Zhang
 */
public class SquareLocation extends Location {
    /**
     * Turn angles for turning x degrees to specified direction
     */
    public static final int LEFT = -90;
    public static final int HALF_LEFT = -45;
    public static final int HALF_RIGHT = 45;
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
    public SquareLocation (int r, int c) {
        super(r, c);
        RIGHT = 90; //Turning interval for accessing neighbors
    }
    

    /**
     * Gets the adjacent location in any one of the eight compass directions.
     * @param direction the direction in which to find a neighbor location
     * @return the adjacent location in the direction that is closest to <tt>direction</tt>
     */
    public Location getAdjacentLocation (int direction, Grid g) {
        int dx = 0;
        int dy = 0;
        if (0 < direction && direction < 180) {
            dx = 1;
        }
        else if (180 < direction && direction < 360) {
            dx = -1;
        }

        if (0 < (direction + 90) % 360 && ((direction + 90) % 360) < 180) {
            dy = 1;
        }
        else if (180 < (direction + 90) % 360 && ((direction + 90) % 360) < 360) {
            dy = -1;
        }
        
        Location adjacent = null;
        Grid<Occupant> grid = (Grid<Occupant>) g;
        
        for (Location loc: grid.getLocations()){
            if (loc.getRow() == getRow() + dx && loc.getCol() == getCol() + dy)
                adjacent = loc;
        }
        
        return adjacent;
    }

    /**
     * Returns the direction from this location toward another location. The
     * direction is rounded to the nearest compass direction.
     * 
     * @param target a location that is different from this location
     * @return the closest compass direction from this location toward <code>target</code>
     */
    public int getDirectionToward (Location target) {
        int dx = target.getCol() - getCol();
        int dy = target.getRow() - getRow();
        int angle = (int) Math.toDegrees(Math.atan2(-dy, dx));

        // mathematical angle is counterclockwise from x-axis,
        // compass angle is clockwise from y-axis
        int compassAngle = RIGHT - angle;
        // prepare for truncating division by 45 degrees
        compassAngle += HALF_RIGHT / 2;
        // wrap negative angles
        compassAngle = ((compassAngle < 0) ? compassAngle += FULL_CIRCLE : compassAngle);
        // round to nearest multiple of 45
        return (compassAngle / HALF_RIGHT) * HALF_RIGHT;
    }

}