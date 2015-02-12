package cellWorld;

import java.util.ArrayList;
import java.util.Collections;
import occupant.Occupant;
<<<<<<< HEAD
import occupant.state.oldState;
import occupant.state.fire.Burning;
import occupant.state.fire.Empty;
import occupant.state.fire.Tree;
import occupant.character.*;
=======
>>>>>>> bb350499b78ac2d8ebfad6d8efd997d60ec08271
import occupant.character.Character;
import xmlreader.ParameterList;
import grid.Grid;
import grid.location.*;



public class SegregationCellWorld extends CellWorld {
    

    public SegregationCellWorld (ParameterList list) {
        super(list);
    }

    /**
     * Performs a step in the simulation
     * Stores the the updated occupant in a copy so iteration of allOccupants can occur
     * simultaneously
     */
    public Grid<Occupant> step () {
        ArrayList<Occupant> allOccs = myGrid.getAllOccupants();
        Collections.shuffle(allOccs);
        for (Occupant o : allOccs) {
            o.act();
        }
        
        //update all together
        int index = 0;
        ArrayList<Location> locs = myGrid.getUnoccupiedLocations();
        Collections.shuffle(locs);
        for (Occupant o: allOccs){
            Character c = (Character) o;
            if (!c.isSatisfied()){
                Location oldLoc = c.getLocation();
                c.moveTo(locs.get(index));
                locs.add(oldLoc);
                index++;
            }
        }
        return myGrid;
    }

    @Override
    public boolean checkFinished () {
        for (Occupant o: myGrid.getAllOccupants()){
            Character agent = (Character) o;
            if (!agent.isSatisfied()){
                return false;
            }
        }
        return true;
    }

//   public Grid<Occupant> reset (ArrayList<Object> parameters) {
//      setParameterList(parameters);
//      setAuthor((String) params.get(0));
//      allOccupants.clear();
//      myGrid = new BoundedGrid<Occupant>(Integer.parseInt(params.get(1).toString()),
//                               Integer.parseInt(params.get(2).toString()));
//      createEmpties((Double) params.get(5));
//      createAgentO((100.0 - (Double) params.get(5))*(Double) params.get(4)/100.0);
//      createAgentX((100.0 - (Double) params.get(5))*(100.0-(Double) params.get(4))/100.0);
//      
//      
//      setProbSimilar((Integer) params.get(3));
//      setPercentAgentO((Double) params.get(4));
//      setPercentEmpty((Double) params.get(5));
//      return myGrid;
//  }
    
<<<<<<< HEAD
    private void createAgentO(Double d){
        int AgentSize = (int) (d * getNumCols() * getNumRows() / 100.0);
        for (int i = 0 ; i < AgentSize; i++){
            Location l = myGrid.getUnoccupiedLocations().get(myRandom.nextInt(myGrid.getUnoccupiedLocations().size()));
            Character o = new AgentO(myGrid, l, ProbSimilar);
            myGrid.put(o, l);
            addToOccupants(o);
        }
    }

    private void createAgentX(Double d){
        int AgentSize = (int) (d * getNumCols() * getNumRows() / 100.0);
        for (int i = 0 ; i < AgentSize; i++){
//            System.out.println(EmptySize);
//            System.out.println(myGrid.getUnoccupiedLocations().size());
            Location l = myGrid.getUnoccupiedLocations().get(myRandom.nextInt(myGrid.getUnoccupiedLocations().size()));
            Character o = new AgentX(myGrid, l, ProbSimilar);
            myGrid.put(o, l);
            addToOccupants(o);
        }
    }

    private void createEmpties (Double d) {
        int EmptySize = (int) (d * getNumCols() * getNumRows() / 100.0);
        for (int i = 0 ; i < EmptySize ; i++){
            Location l = myGrid.getUnoccupiedLocations().get(myRandom.nextInt(myGrid.getUnoccupiedLocations().size()));
            Character o = new AgentEmpty(myGrid, l, ProbSimilar);
            myGrid.put(o, l);
            addToOccupants(o);
        }
    }
=======
//    private void createAgentO(Double d){
//        int AgentSize = (int) (d * getNumCols() * getNumRows() / 100.0);
//        for (int i = 0 ; i < AgentSize; i++){
//            Location l = myGrid.getUnoccupiedLocations().get(myRandom.nextInt(myGrid.getUnoccupiedLocations().size()));
//            Character o = new AgentO(myGrid, l, ProbSimilar);
//            myGrid.put(l, o);
//            addToOccupants(o);
//        }
//    }
//
//    private void createAgentX(Double d){
//        int AgentSize = (int) (d * getNumCols() * getNumRows() / 100.0);
//        for (int i = 0 ; i < AgentSize; i++){
////            System.out.println(EmptySize);
////            System.out.println(myGrid.getUnoccupiedLocations().size());
//            Location l = myGrid.getUnoccupiedLocations().get(myRandom.nextInt(myGrid.getUnoccupiedLocations().size()));
//            Character o = new AgentX(myGrid, l, ProbSimilar);
//            myGrid.put(l, o);
//            addToOccupants(o);
//        }
//    }
//
//    private void createEmpties (Double d) {
//        int EmptySize = (int) (d * getNumCols() * getNumRows() / 100.0);
//        for (int i = 0 ; i < EmptySize ; i++){
//            Location l = myGrid.getUnoccupiedLocations().get(myRandom.nextInt(myGrid.getUnoccupiedLocations().size()));
//            Character o = new AgentEmpty(myGrid, l, ProbSimilar);
//            myGrid.put(l, o);
//            addToOccupants(o);
//        }
//    }
>>>>>>> bb350499b78ac2d8ebfad6d8efd997d60ec08271

}