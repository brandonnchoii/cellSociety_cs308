package grid.location;

import grid.Grid;
import occupant.Occupant;

public class TriangleLocation extends Location {
    /**
     * Turn angles for turning x degrees to specified direction
     */
    public static final int LEFT = -120;
    public static final int HALF_LEFT = -60;
    public static final int HALF_RIGHT = 60;
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

    public TriangleLocation (int r, int c) {
        super(r, c);
        RIGHT = 120; // Turning interval for accessing neighbors
    }

    /**
     * Gets the adjacent location in any one of the eight compass directions.
     * 
     * @param direction the direction in which to find a neighbor location
     * @return the adjacent location in the direction that is closest to <tt>direction</tt>
     */
    public Location getAdjacentLocation (int direction, Grid g) {
        int dx = 0;
        int dy = 0;
        if (getCol() % 2 == 0) { // Location will take on LEFT Sub-triangle shape
            if (direction == 0) {
                dy += 1;
            }
            else if (direction == 120) {
                dx += 1;
            }
            else if (direction == 360) {
                dx -= 1;
            }
        }
        else {
            direction += RIGHT / 2;
            if (direction == 180) {
                dy -= 1;
            }
            else if (direction == 300) {
                dx -= 1;
            }
        }

        Location adjacent = null;
        Grid<Occupant> grid = (Grid<Occupant>) g;

        for (Location loc : grid.getLocations()) {
            if (loc.getRow() == getRow() + dx && loc.getCol() == getCol() + dy)
                adjacent = loc;
        }

        return adjacent;
    }

    /**
     * TODO: Implement getDirectionToward
     */

    @Override
    public int getDirectionToward (Location target) {
        // TODO Auto-generated method stub
        return 0;
    }

}
