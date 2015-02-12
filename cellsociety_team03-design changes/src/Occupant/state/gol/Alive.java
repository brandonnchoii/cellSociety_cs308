package occupant.state.gol;

import grid.Grid;
import grid.location.*;

public class Alive extends LifeState {

    public Alive (Grid<Location> g, Location l) {
        super(g, l, ALIVE_COLOR, ALIVE);
    }
    
    @Override
    public void act () {
        if (getLivingNum() == 2 || getLivingNum() == 3)
            setNextState(new Alive(myGrid, myLocation));
        else
            setNextState(new Dead(myGrid, myLocation));
    }
}
