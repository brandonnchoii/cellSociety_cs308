package cellWorld;

import java.util.ArrayList;
import grid.Grid;
import occupant.Occupant;
import occupant.state.State;
import xmlreader.ParameterList;

public class StateCellWorld extends CellWorld {

    @Override
    public Grid<Occupant> reset (ParameterList parameters) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean checkFinished () {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Performs a step in the simulation
     * Stores the the updated occupant in a copy so iteration of allOccupants can occur simultaneously
     */
    @ Override
    public Grid<Occupant> step () {
        ArrayList<Occupant> copy = new ArrayList<Occupant>(allOccupants.size());
        for (Occupant o : allOccupants) {
            o.act();
            copy.add(o);
        }
        allOccupants = new ArrayList<Occupant>(copy);
        for (Occupant o : allOccupants) {
            State s = (State) o;
            s.setCurrentState(s.getNextState());
        }
        return myGrid;
    }

}
