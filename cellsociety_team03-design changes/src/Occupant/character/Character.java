package occupant.character;

import occupant.Occupant;
import grid.Grid;
import grid.location.*;


public abstract class Character extends Occupant {
    private Location myPreviousLocation;
    private Location myNextLocation;
    protected String type;
    protected boolean SATISFIED;

    public Character (Grid<Location> grid, Location loc, String s) {
        super(grid, loc, s);
        myPreviousLocation = loc;
        myNextLocation = loc;
    }

    public void moveTo (Location location) {
         removeSelfFromGrid();
         location.setOccupant(this);
    }

    public boolean isSatisfied () {
        return SATISFIED;
    }

    public void setSatisfied (boolean t) {
        SATISFIED = t;
    }

    public void setType (String t) {
        this.type = t;
    }

    public String getType () {
        return type;
    }

    protected void setPreviouslocation (Location location) {
        myPreviousLocation = location;
    }

    protected Location getPreviousLocation () {
        return myPreviousLocation;
    }

    public Location getNextLocation () {
        return myNextLocation;
    }

    public void setNextLocation (Location location) {
        myNextLocation = location;
    }

}
