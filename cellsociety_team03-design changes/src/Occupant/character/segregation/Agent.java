package occupant.character.segregation;

import java.util.ArrayList;
import grid.Grid;
import grid.location.*;
import javafx.scene.paint.Color;
import occupant.Occupant;
import occupant.character.Character;


public class Agent extends Character {
    
    private Double similarity;

    public Agent (Grid<Location> grid, Location loc, double sim, String type) {
        super(grid, loc);
        myLocation = loc;
        similarity = sim;
        setColor((type == "O") ? Color.RED : Color.BLUE);
        setType((type == "O") ? "O" : "X");
        setSatisfied(false);
    }

    // determine whether characters are satisfied with current locations 
    @Override
    public void act () {
        ArrayList<Occupant> myNeighbors = myGrid.getNeighbors(myLocation, myGrid);
        int size = myGrid.getOccupiedAdjacentLocations(myLocation).size();
        int friendCount = 0;
        for (Occupant o : myNeighbors) {
            Character neighboringChar = (Character) o;
            if (neighboringChar.getType().equals(getType())) {
                friendCount++;
            }
        }
        double threshold = size * similarity / 100.0;
        setSatisfied(((double) friendCount > threshold) ? true : false);
    }
}
