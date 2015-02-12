package occupant.state;

import occupant.Occupant;
import grid.Grid;
import grid.Location;
import javafx.scene.paint.Color;

public class State extends Occupant {
    private StateType currentState;
    private StateType nextState;
    private Location myLoc;

    public State (Grid<Occupant> grid, Location loc, StateType current) {
        super(grid, loc);
        myLoc=loc;
        currentState = current;
        setColor(current.getColor());
    }
    
    public State(Location loc){
        super(loc);        
    }

    
    @Override
    public void act () {
        setNextState(currentState.update());
        setColor(nextState.getColor());
    }
    
    // to be overridden in subclasses
    protected StateType update () {
        return currentState;
    }

    protected void setNextState (StateType s) {
        nextState = s;
        
    }

    public StateType getNextState () {
        return nextState;
    }

    public void setCurrentState (StateType s) {
        currentState = s;
    }

    public StateType getCurrentState () {
        return currentState;
    }

    //public abstract String getType();  


}
