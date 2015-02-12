package occupant.state.gol;

import java.util.ArrayList;

import grid.Location;
import occupant.Occupant;
import grid.Grid;
import javafx.scene.paint.Color;
import occupant.state.State;
import occupant.state.StateType;

public abstract class LifeState extends StateType {

    private Grid<Occupant> myGrid;
    private ArrayList<Location> myNeighbors;
    private Location myLocation;

    public LifeState (Grid<Occupant> g, Location l, Color c, String t) {
        setColor(c);
        setType(t); // need this method?!
        myGrid = g;
        myLocation = l;
    }

    public Location getLocation () {
        return myLocation;
    }

    public Grid<Occupant> getGrid () {
        return myGrid;
    }

    private void setNeighbors () {
        myNeighbors = myGrid.getOccupiedAdjacentLocations(myLocation);
    }

    public ArrayList<Location> getNeighbors () {
        return myNeighbors;
    }

    public int getLivingNum () {
        setNeighbors();
        int number = 0;
        for (Location l : myNeighbors) {
            State neighboringState = (State) myGrid.get(l);
            if (neighboringState.getCurrentState() instanceof Alive)
                number++;
        }
        return number;
    }
}
