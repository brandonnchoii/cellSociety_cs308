package occupant;

import java.util.ArrayList;

import occupant.state.State;
import javafx.scene.paint.Color;
import grid.Grid;
import grid.Location;

public abstract class Occupant {
    protected Grid<Occupant> myGrid;
    protected Location myLocation;
    protected Color myColor; // possibly change to Image

    public Occupant (Grid<Occupant> grid, Location loc) {
        myGrid = grid;
        myLocation = loc;
        //myColor = color;
        putSelfInGrid();
    }

    public Occupant (Location loc) {
        myLocation = loc;
    }

    public abstract void act ();


    protected void putSelfInGrid () {
        myGrid.put(myLocation, this);
    }

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

    public Grid<Occupant> getGrid () {
        return myGrid;
    }

    // toString() not necessary I think and getChar() should go into location / is already in Grid
}
