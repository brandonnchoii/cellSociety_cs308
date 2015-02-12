package occupant.character.predatorprey;

import java.util.ArrayList;
import java.util.Collections;
import grid.Grid;
import grid.location.*;
import javafx.scene.paint.Color;
import occupant.Occupant;
import occupant.character.Character;


public class Fish extends Character {

    private static final String type = "FISH";
    private static final Color FISH_COLOR = Color.GREEN;
    private static int BREED_TIME_MAX;
   
    private boolean isDead;
    private int breedTime;
    
    public Fish (Grid<Location> grid, Location loc, int breedingTime) {
        super(grid, loc, type);
        setColor(FISH_COLOR);
        BREED_TIME_MAX = breedingTime;
        breedTime = BREED_TIME_MAX + 1;
    }

    public void die(){
        isDead = true;
    }
    
    @Override
    public void act () {
        if (!isDead) {
            breedTime--;
            ArrayList<Location> emptyNeighbors = myGrid.getEmptyAdjacentLocations(myLocation);
            if (emptyNeighbors.size() != 0) {
                Collections.shuffle(emptyNeighbors);
                moveTo(emptyNeighbors.get(0));
                if (breedTime <= 0) { // can only breed if open spot
                    // at least one empty spot must exist - previous spot
                    emptyNeighbors = myGrid.getEmptyAdjacentLocations(myLocation);
                    Collections.shuffle(emptyNeighbors);
                    new Fish(myGrid, emptyNeighbors.get(0), BREED_TIME_MAX);
                    breedTime = BREED_TIME_MAX;
                }
            }
        }
    }
}
