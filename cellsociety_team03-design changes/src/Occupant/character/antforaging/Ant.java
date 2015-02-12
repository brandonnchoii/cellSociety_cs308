package occupant.character.antforaging;

import java.util.ArrayList;

import occupant.Occupant;
import grid.Grid;
import grid.OldLocation;
import occupant.character.Character;

public class Ant extends Character{
    
    private static final String type = "ANT";
    private static final String FOOD_PHER = "Food Pheromones";
    private static final String HOME_PHER = "Home Pheromones";
    private static final int DIR_CHANGE = 45;
    
    private int orientation;
    private ArrayList<OldLocation> possibleLocations;
    private boolean hasFood;
     
    public Ant(Grid<Occupant> g, OldLocation l){
        super(g, l, type);
        orientation = OldLocation.NORTH;
        possibleLocations = new ArrayList<>();
        hasFood = false;
    }

    @Override
    public void act () {
        if (hasFood) {
            returnToNest();
        }
        else
            findFoodSource();
    }
    
    private void returnToNest() {
        
    }
    
    private void findFoodSource() {
        
    }
    
    private void dropPheromone(String pheromone, int change) {
        myLocation.addAttribute(pheromone, change);
    }
    
    private void getForwardLocations() {
        OldLocation f1, f2, f3;
        
        f1 = myLocation.getAdjacentLocation(orientation);
        
        if (orientation == OldLocation.NORTH){
            f2 = myLocation.getAdjacentLocation(orientation + DIR_CHANGE);
            f3 = myLocation.getAdjacentLocation(OldLocation.NORTHWEST);
        }
        
        else if (orientation == OldLocation.NORTHWEST){
            f2 = myLocation.getAdjacentLocation(OldLocation.NORTH);
            f3 = myLocation.getAdjacentLocation(orientation - DIR_CHANGE);
        }
        
        else {
            f2 = myLocation.getAdjacentLocation(orientation + DIR_CHANGE);
            f3 = myLocation.getAdjacentLocation(orientation - DIR_CHANGE);
        }
        
        possibleLocations.add(f1);
        possibleLocations.add(f2);
        possibleLocations.add(f3);
    }
    
    private void getOtherLocations() {
        ArrayList<OldLocation> neighbors = myGrid.getValidAdjacentLocations(myLocation);
        for (OldLocation l : neighbors) {
            if (!possibleLocations.contains(l)){
                possibleLocations.add(l);
            }
        }
    }
}
