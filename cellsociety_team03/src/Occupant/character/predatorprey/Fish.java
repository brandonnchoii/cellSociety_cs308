package occupant.character.predatorprey;

import java.util.ArrayList;
import java.util.Collections;
import grid.Grid;
import grid.Location;
import javafx.scene.paint.Color;
import occupant.Occupant;
import occupant.character.Character;


public class Fish extends Character {

    private static final String type = "FISH";
    private static final Color FISH_COLOR = Color.GREEN;
    private static final int BREED_TIME_MAX = 2; // TODO: implement XML parameter

    private int breedTime;

    public Fish (Grid<Occupant> grid, Location loc) {
        super(grid, loc);
        setColor(FISH_COLOR);
        breedTime = BREED_TIME_MAX;
        // setType(type);
    }

    @Override
    public Occupant act () {
        ArrayList<Location> emptyNeighbors = myGrid.getEmptyAdjacentLocations(myLocation);// int
                                                                                          // fishCount
                                                                                          // = 0;
        // for (Location l : myNeighbors) {
        // if (myGrid.get(l) instanceof Fish)
        // fishCount++;
        // }
        if (emptyNeighbors.size() != 0) {
            Collections.shuffle(emptyNeighbors);
            moveTo(emptyNeighbors.get(0));
            breedTime--;
            if (breedTime == 0) {
                emptyNeighbors = myGrid.getEmptyAdjacentLocations(myLocation); // at least one
                                                                               // exists (previous
                                                                               // spot)
                Collections.shuffle(emptyNeighbors);
                new Fish(myGrid, emptyNeighbors.get(0));
            }   
        }
        return this;
    }

}
