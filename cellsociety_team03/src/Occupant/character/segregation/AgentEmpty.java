package occupant.character.segregation;

import java.util.ArrayList;
import grid.Grid;
import grid.Location;
import javafx.scene.paint.Color;
import occupant.Occupant;
import occupant.character.Character;
import occupant.state.fire.Burning;


public class AgentEmpty extends Character {

    public AgentEmpty (Grid<Occupant> grid, Location loc, double p) {
        super(grid, loc);
        myGrid = grid;
        myLocation = loc;
        setSatisfied(true);
        setType("EMPTY");
        setColor(Color.WHITE);
    }

    private static final Color EMPTY = Color.WHITE;
    private static final String type = "EMPTY";


    // the next state of any and all burning locations is always Empty
    @Override
    public void act () {
    }
}