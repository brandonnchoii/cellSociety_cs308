package occupant.state.gol;

import grid.location.*;
import grid.Grid;
import javafx.scene.paint.Color;
import occupant.state.State;

public abstract class LifeState extends State {
    protected static final Color ALIVE_COLOR = Color.AQUAMARINE;
    protected static final String ALIVE = "ALIVE";
    
    protected static final Color DEAD_COLOR = Color.WHITE;
    protected static final String DEAD = "DEAD";

    public LifeState (Grid<Location> g, Location l, Color c, String t) {
        super (g, l, c, t);
    }

    public int getLivingNum () {
        setNeighbors();
        int number = 0;
        for (Location l : getNeighbors()) {
            if (l.getOccupant() instanceof Alive)
                number++;
        }
        return number;
    }
}
