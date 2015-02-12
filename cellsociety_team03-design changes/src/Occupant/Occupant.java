package occupant;

import javafx.scene.paint.Color;
import grid.Grid;
import grid.location.*;

public abstract class Occupant {
    protected Grid<Location> myGrid;
    protected Location myLocation;
    protected Color myColor;
    protected String myType;

    public Occupant (Grid<Location> grid, Location loc, String s) {
        myGrid = grid;
        myLocation = loc;
        myType = s;
    }

    public Occupant (Location loc) {
        myLocation = loc;
    }

    public abstract void act ();

    protected void removeSelfFromGrid () {
        myGrid.remove(myLocation);
    }

    protected void setColor (Color c) {
        myColor = c;
    }

    public Color getColor () {
        return myColor;
    }

    protected void setLocation (Location l) {
        myLocation = l;
    }

    public Location getLocation () {
        return myLocation;
    }

    public Grid<Location> getGrid () {
        return myGrid;
    }
    
    public String getType(){
        return myType;
    }

}
