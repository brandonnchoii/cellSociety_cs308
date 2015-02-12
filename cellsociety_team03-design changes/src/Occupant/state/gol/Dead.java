package occupant.state.gol;

import grid.Grid;
import grid.location.*;
public class Dead extends LifeState {

    public Dead (Grid<Location> g, Location l) {
        super(g, l, DEAD_COLOR, DEAD);
    }

    @Override
    public void act () {
        if (getLivingNum() == 3)
            setNextState(new Alive(myGrid, myLocation));
        else
            setNextState(new Dead(myGrid, myLocation));
    }
}
