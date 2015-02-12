package occupant.state.fire;

import grid.Grid;
import grid.location.Location;
import occupant.state.State;
import javafx.scene.paint.Color;

public class Burning extends State {
    private static final Color BURNING_COLOR = Color.RED;
    private static final String type = "BURNING";

    public Burning (Grid<Location> g,Location l) {
        super (g, l, BURNING_COLOR, type);
    }

    @Override
    public void act () {
        setNextState(new Empty(myGrid, myLocation));
    }
}
