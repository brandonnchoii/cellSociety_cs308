package occupant.state.gol;

import grid.Grid;
import grid.Location;
import javafx.scene.paint.Color;
import occupant.Occupant;
import occupant.state.StateType;

public class Alive extends LifeState {
    private static final Color ALIVE_COLOR = Color.AQUAMARINE;
    private static final String type = "ALIVE";

    public Alive (Grid<Occupant> g, Location l) {
        super(g, l, ALIVE_COLOR, type);
    }

    @Override
    public StateType update () {
        if (getLivingNum() == 2 || getLivingNum() == 3)
            return new Alive(getGrid(), getLocation());
        else
            return new Dead(getGrid(), getLocation());
    }
}
