package occupant.state.fire;

import grid.Grid;
import grid.location.Location;
import occupant.state.State;
import javafx.scene.paint.Color;

public class Empty extends State {
    private static final Color EMPTY_COLOR = Color.YELLOW;
    private static final String type = "EMPTY";

    public Empty (Grid<Location> g, Location l) {
        super (g, l, EMPTY_COLOR, type);
    }

    @Override
    public void act () {
        setNextState(new Empty(myGrid, myLocation));
    }
}
