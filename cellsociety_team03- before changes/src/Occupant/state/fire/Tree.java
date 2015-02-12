package occupant.state.fire;

import java.util.ArrayList;
import java.util.Random;
import occupant.Occupant;
import occupant.state.State;
import occupant.state.StateType;
import javafx.scene.paint.Color;
import grid.Grid;
import grid.Location;


public class Tree extends StateType {
    private static final Color TREE_COLOR = Color.GREEN;

    private double spreadProbability;
    private ArrayList<Location> myNeighbors;
    private static final String type = "TREE";
    private Random generator = new Random();
    private Grid<Occupant> myGrid;
    private Location myLocation;

    public Tree (Grid<Occupant> g, Location l, double p) {
        setColor(TREE_COLOR);
        setType(type);
        myGrid = g;
        myLocation = l;
        spreadProbability = p;
    }

    // looking to replace if statements with ?: shorthand or switch statements like in recitation
    @Override
    public StateType update () {
        myNeighbors = myGrid.getOccupiedAdjacentLocations(myLocation);
        for (Location l : myNeighbors) {
            double p = generator.nextDouble() * 100;
            State neighboringState = (State) myGrid.get(l);
            if (neighboringState.getCurrentState() instanceof Burning &&
                p <= spreadProbability) {
                return new Burning();        
            }
        }
        return this;
    }
}
