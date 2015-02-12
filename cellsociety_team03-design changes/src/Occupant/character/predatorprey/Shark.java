package occupant.character.predatorprey;

import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.paint.Color;
import grid.Grid;
import grid.location.*;
import occupant.Occupant;
import occupant.character.Character;


public class Shark extends Character {

    private static final String type = "SHARK";
    private static final Color SHARK_COLOR = Color.ORANGE;
    private static int BREED_TIME_MAX;
    private static int STARVE_TIME_MAX; 

    private int breedTime;
    private int starveTime;
    private boolean isDead;

    public Shark (Grid<Location> grid, Location loc, int sharkBreed, int sharkStarve) {
        super(grid, loc, type);
        setColor(SHARK_COLOR);
        BREED_TIME_MAX = sharkBreed;
        STARVE_TIME_MAX = sharkStarve;

        // initialize breedTime/starveTime
        breedTime = BREED_TIME_MAX + 1;
        starveTime = STARVE_TIME_MAX + 1;
    }
    
    public void die(){
        isDead = true;
    }

    @Override
    public void act () {      
        if (!isDead) {
            breedTime--;
            starveTime--;
            ArrayList<Occupant> neighbors = myGrid.getNeighbors(myLocation);
            ArrayList<Location> emptyNeighbors = myGrid.getEmptyAdjacentLocations(myLocation);
            ArrayList<Character> fishNeighbors = new ArrayList<Character>();
            for (Occupant o : neighbors) {
                if (o instanceof Fish) {
                    fishNeighbors.add((Fish) o);
                }
            }
            if (fishNeighbors.isEmpty() && !emptyNeighbors.isEmpty()) { // no fish and at least one
                                                                        // empty neighbor
                Collections.shuffle(emptyNeighbors);
                moveTo(emptyNeighbors.get(0));

                emptyNeighbors = myGrid.getEmptyAdjacentLocations(myLocation); // new empty
                                                                               // neighbors
                checkBreedTime(emptyNeighbors);
                checkStarveTime();
            }
            else if (!fishNeighbors.isEmpty()) { // at least one fish neighbor
                Collections.shuffle(fishNeighbors);
                Fish f = (Fish) fishNeighbors.get(0);
                moveTo(f.getLocation());
                f.die();
                starveTime = STARVE_TIME_MAX;
                checkBreedTime(emptyNeighbors);
            }
            else { // all shark neighbors
                   // can't breed since no empty neighbors
                checkStarveTime();
            }
        }
    }

    private void checkBreedTime (ArrayList<Location> emptyNeighbors) {
        if (!emptyNeighbors.isEmpty() && breedTime <= 0) {
            Collections.shuffle(emptyNeighbors);
            new Shark(myGrid, emptyNeighbors.get(0), BREED_TIME_MAX, STARVE_TIME_MAX);
            breedTime = BREED_TIME_MAX;
        }
    }

    private void checkStarveTime () {
        if (starveTime <= 0) { // shark dies
            this.die();
            this.removeSelfFromGrid();
        }
    }
}
