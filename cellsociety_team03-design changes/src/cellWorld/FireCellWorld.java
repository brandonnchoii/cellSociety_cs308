package cellWorld;

import java.util.ArrayList;

import occupant.Occupant;
import occupant.state.State;
import xmlreader.ParameterList;
import grid.Grid;
import grid.location.*;
import grid.boundedgrid.BoundedGrid;

public class FireCellWorld extends CellWorld {

    private static final String WORLD_NAME = "Spreading of Fire";

    public FireCellWorld (ParameterList list) {
        super(list);
    }

    /**
     * Performs a step in the simulation Stores the the updated occupant in a copy so iteration of
     * allOccupants can occur simultaneously
     */
    public Grid<Location> step () {        
        myLocations = myGrid.getOccupiedLocations();
        ArrayList<Location> copy = new ArrayList<Location>();
        
        for (Location l : myLocations) {
            l.updateOccupant();
            copy.add(l);
        }

        for (Location l : copy) {
            State s = (State) l.getOccupant();
            State newState = s.getNextState();
            l.setOccupant(newState);
        }
        
        myGrid.setLocations(copy);
        
        return myGrid;
    }

    @Override
    public boolean checkFinished () {
        for (Location l : myGrid.getOccupiedLocations()) {
            if (l.getOccupant().getType().equals("BURNING")) {
                return false;
            }
        }
        return true;
    }
}
