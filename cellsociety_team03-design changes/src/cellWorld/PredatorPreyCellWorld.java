package cellWorld;

import java.util.ArrayList;

import java.util.Collections;

import occupant.Occupant;
import occupant.character.predatorprey.Fish;
import occupant.character.predatorprey.Shark;
import occupant.state.State;
import occupant.state.fire.Burning;
import occupant.state.fire.Empty;
import occupant.state.fire.Tree;
import xmlreader.ParameterList;
import grid.Grid;
import grid.location.Location;


public class PredatorPreyCellWorld extends CellWorld {

    public PredatorPreyCellWorld (ParameterList list) {
        super(list);
    }

    @Override
    public Grid<Location> step () {
        ArrayList<Occupant> allOccs = myGrid.getAllOccupants();
        Collections.shuffle(allOccs);
        for (Occupant o : allOccs) {
            o.act();
        }
        return myGrid;
    }

    @Override
    public boolean checkFinished () {
        return false;
    }

}
