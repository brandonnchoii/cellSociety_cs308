package occupant.character.segregation;

import java.util.ArrayList;
import grid.Grid;
import grid.Location;
import javafx.scene.paint.Color;
import occupant.Occupant;
import occupant.character.Character;

public class AgentX extends Character {
    private ArrayList<Location> myNeighbors;
    private Grid<Occupant> myGrid;
    private Location myLocation;
    private Double ProbSimilar;
    
    public AgentX (Grid<Occupant> grid, Location loc, double p) {
        super(grid, loc);
        myGrid = grid;
        myLocation = loc;
        
        ProbSimilar = p;
        setSatisfied(false);
        setColor(Color.BLUE);
        setType("AGENTX");
    }

    // the next state of any and all burning locations is always Empty
    @Override
    public void act () {
        myNeighbors = myGrid.getOccupiedAdjacentLocations(myLocation);
        
        int size = 0;
        int ally = 0;
        for (Location l : myNeighbors) {
            Character neighboringChar = (Character) myGrid.get(l);
            if (neighboringChar.getType() != "EMPTY"){
                size += 1;
                if(neighboringChar.getType() == "AGENTX"){
                    ally += 1;
                }
            }
        }
        double threshold = size * ProbSimilar / 100.0;
        if((double) ally > threshold){
            setSatisfied(true);
        }else{
            setSatisfied(false); //rewrite this
        }

    }
}