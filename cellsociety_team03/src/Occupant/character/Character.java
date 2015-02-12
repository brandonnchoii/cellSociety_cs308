package occupant.character;

import occupant.Occupant;
import javafx.scene.paint.Color;
import grid.Grid;
import grid.Location;

public abstract class Character extends Occupant {
    private Location myPreviousLocation;
    private Location myNextLocation;
    protected String type;
    protected boolean SATISFIED;
    public Character (Grid<Occupant> grid, Location loc) {
        super(grid, loc);
        myPreviousLocation = loc;
        myNextLocation = loc;
    }

    public void moveTo (Location location) {
        setLocation(location);
    }
    
    public boolean getSatisfied(){
        return SATISFIED;
    }
    
    public void setSatisfied(boolean t){
        SATISFIED = t;
    }

    public void setType(String t) {
        this.type = t;   
    }
    
    public String getType(){
        return type;
    }
    protected void setPreviouslocation (Location location) {
        myPreviousLocation = location;
    }

    protected Location getPreviousLocation () {
        return myPreviousLocation;
    }
    
    public Location getNextLocation(){
        return myNextLocation;
    }
    public void setNextLocation(Location location){
        myNextLocation = location;
    }

}
