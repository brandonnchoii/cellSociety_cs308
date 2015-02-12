package occupant.state;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import grid.Grid;
import grid.location.Location;
import occupant.Occupant;

public abstract class State extends Occupant{
    
    private Color myColor;
    private State nextState;
    private ArrayList<Location> myNeighbors;
    
    public State(Grid<Location> g, Location l, Color c, String s){
        super(g,l,s);
        myColor = c;
    }
    
    public Color getColor(){
        return myColor;
    }
    
    protected void setNextState(State s){
        nextState = s;
    }
    
    public State getNextState(){
        return nextState;
    }
    
    protected void setNeighbors () {
        myNeighbors = myGrid.getOccupiedAdjacentLocations(myLocation);
    }

    public ArrayList<Location> getNeighbors () {
        return myNeighbors;
    }
}
