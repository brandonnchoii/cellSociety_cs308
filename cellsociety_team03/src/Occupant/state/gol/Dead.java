package occupant.state.gol;

import grid.Grid;
import grid.Location;
import javafx.scene.paint.Color;
import occupant.Occupant;
import occupant.state.StateType;

public class Dead extends LifeState {
    private static final Color DEAD_COLOR = Color.WHITE;
    private static final String type = "DEAD";

    public Dead (Grid<Occupant> g, Location l) {
        super(g, l, DEAD_COLOR, type);
    }

    @Override
    public StateType update () {
        if (getLivingNum() == 3)
            return new Alive(getGrid(), getLocation());
        else
            return new Dead(getGrid(), getLocation());
    }
}
