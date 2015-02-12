package cellWorld;

import java.util.ArrayList;
import occupant.Occupant;
import grid.Grid;

public class PredatorPreyCellWorld extends CellWorld {
    public PredatorPreyCellWorld (String name, Grid<Occupant> grid, int rows, int columns) {
        super(name);
    }
    
    @Override
    public Grid<Occupant> reset (ArrayList<Object> parameters) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Grid<Occupant> step () {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public boolean checkFinished () {
        // TODO Auto-generated method stub
        return false;
    }


}
