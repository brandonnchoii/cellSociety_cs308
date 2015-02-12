package cellWorld;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import occupant.Occupant;
import occupant.state.State;
import occupant.state.fire.Burning;
import occupant.state.fire.Empty;
import occupant.state.fire.Tree;
import occupant.character.*;
import occupant.character.Character;
import occupant.character.segregation.AgentEmpty;
import occupant.character.segregation.AgentO;
import occupant.character.segregation.AgentX;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import grid.Grid;
import grid.Location;
import grid.boundedgrid.BoundedGrid;



public class SegregationCellWorld extends CellWorld {
    private static final String WORLD_NAME = "Schelling's Segregation";

    private int ProbSimilar;
    private double PercentAgentO;
    private double PercentEmpty;
    private Random myRandom;
    

    public SegregationCellWorld (Document doc) {
        super(WORLD_NAME);
        myRandom = new Random();
        setParameterList(makeList(doc));
    }

    private ArrayList<Object> makeList (Document doc) {
        ArrayList<Object> parameters = new ArrayList<Object>();
        parameters.add(getProperty(doc, "author")); // Adds author to list
        setNumRows(Integer.parseInt(getProperty(doc, "rows")));
        parameters.add(getNumRows());
        setNumCols(Integer.parseInt(getProperty(doc, "cols")));
        parameters.add(getNumCols()); // Adds r/c to list

        for (int i = 0; i < getParameter(doc).size(); i++) {
            parameters.add((double) getParameter(doc).get(i));
        }
        return parameters;
    }

    private static ArrayList<Integer> getParameter (Document doc) {
        Element eLement = (Element) doc.getElementsByTagName("Parameters").item(0);
        NodeList parameters = eLement.getElementsByTagName("Par");
        ArrayList<Integer> paraList = new ArrayList<Integer>();
        for (int j = 0; j < parameters.getLength(); j++) {
            paraList.add(Integer.parseInt(parameters.item(j).getTextContent()));
        }
        return paraList;
    }

    public Grid<Occupant> reset (ArrayList<Object> parameters) {
        setParameterList(parameters);
        setAuthor((String) parameterList.get(0));
        allOccupants.clear();
        myGrid = new BoundedGrid<Occupant>(Integer.parseInt(parameterList.get(1).toString()),
                                 Integer.parseInt(parameterList.get(2).toString()));
        createEmpties((Double) parameterList.get(5));
        createAgentO((100.0 - (Double) parameterList.get(5))*(Double) parameterList.get(4)/100.0);
        createAgentX((100.0 - (Double) parameterList.get(5))*(100.0-(Double) parameterList.get(4))/100.0);
        
        
        setProbSimilar((Integer) parameterList.get(3));
        setPercentAgentO((Double) parameterList.get(4));
        setPercentEmpty((Double) parameterList.get(5));
        return myGrid;
    }

    /**
     * Performs a step in the simulation
     * Stores the results of act() in a copy so iteration of allOccupants can occur simultaneously
     */
    public Grid<Occupant> step () {
        ArrayList<Occupant> copy = new ArrayList<Occupant>(allOccupants.size());
        ArrayList<Occupant> empty = new ArrayList<Occupant>();
        for (Occupant o: allOccupants){
            if (((Character) o).getType() == "EMPTY"){
                empty.add(o);
            }
        }
        for (Occupant o : allOccupants) {
            Character agent = (Character) o;
            o.act();
            if(!agent.getSatisfied() && empty.size() != 0){
                Character replaced = (Character) empty.get(0);
                agent.setNextLocation(replaced.getLocation());
                replaced.setNextLocation(agent.getLocation());
                empty.remove(replaced);
            }
        }
        for (Occupant o : allOccupants) {
            Character agent = (Character) o;
            agent.moveTo(agent.getNextLocation());
        }
        return myGrid;
    }

    @Override
    public boolean checkFinished () {
        for (Occupant o: allOccupants){
            Character agent = (Character) o;
            if (!agent.getSatisfied()){
                return false;
            }
        }
        return true;
    }

    private double getProbSimilar(){
        return ProbSimilar;
    }
    private double getPercentAgentO(){
        return PercentAgentO;
    }
        
    private double getPercentEmpty(){
        return PercentEmpty;
    }
    
    private void setProbSimilar(int p){
        ProbSimilar = p;
    }
    private void setPercentAgentO(double p){
        PercentAgentO = p;
    }
        
    private void setPercentEmpty(double p){
        PercentEmpty = p;
    }
    
    private void createAgentO(Double d){
        int AgentSize = (int) (d * getNumCols() * getNumRows() / 100.0);
        for (int i = 0 ; i < AgentSize; i++){
            Location l = myGrid.getUnoccupiedLocations().get(myRandom.nextInt(myGrid.getUnoccupiedLocations().size()));
            Character o = new AgentO(myGrid, l, ProbSimilar);
            myGrid.put(l, o);
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
            myGrid.put(l, o);
            addToOccupants(o);
        }
    }

    private void createEmpties (Double d) {
        int EmptySize = (int) (d * getNumCols() * getNumRows() / 100.0);
        for (int i = 0 ; i < EmptySize ; i++){
            Location l = myGrid.getUnoccupiedLocations().get(myRandom.nextInt(myGrid.getUnoccupiedLocations().size()));
            Character o = new AgentEmpty(myGrid, l, ProbSimilar);
            myGrid.put(l, o);
            addToOccupants(o);
        }
    }

}