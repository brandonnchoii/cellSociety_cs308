package occupant.state.fire;

import java.util.Random;
import occupant.state.State;
import javafx.scene.paint.Color;
import grid.Grid;
import grid.location.*;

public class Tree extends State {
    private static final Color TREE_COLOR = Color.GREEN;

    private double spreadProbability;
    private static final String type = "TREE";
    private static Random generator = new Random();

    public Tree (Grid<Location> g, Location l, double p) {
        super(g, l, TREE_COLOR, type);
        spreadProbability = p;
    }

    @Override
    public void act () {
        setNeighbors();
        for (Location l : getNeighbors()) {
            if (burned(l)) {
                setNextState(new Burning(myGrid, myLocation));
                break;
            }
            else {
                setNextState(new Tree(myGrid, myLocation, spreadProbability));
            }
        }
    }

    private boolean burned (Location l) {
        double p = generator.nextDouble() * 100;
        if (l.getOccupant() instanceof Burning && p <= spreadProbability){
            return true;
        }
        else
            return false;
    }
}
