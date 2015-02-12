package cellWorld;

import java.util.ArrayList;

import occupant.Occupant;
import occupant.state.State;
import occupant.state.fire.Burning;
import occupant.state.fire.Empty;
import occupant.state.fire.Tree;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import grid.Grid;
import grid.Location;
import grid.boundedgrid.BoundedGrid;


public class FireCellWorld extends StateCellWorld {
    private static final String WORLD_NAME = "Spreading of Fire";

    private double spreadProbability;

    public FireCellWorld (Document doc) {
        super();
        setParameterList(makeList(doc));
        setWindowTitle(String.format("%s by %s", WORLD_NAME, (String) parameterList.get(0)));
    }

    private ArrayList<Object> makeList (Document doc) {
        ArrayList<Object> parameters = new ArrayList<Object>();
        parameters.add(getProperty(doc, "author")); // Adds author to list
        setNumRows(Integer.parseInt(getProperty(doc, "rows")));
        parameters.add(getNumRows());
        setNumCols(Integer.parseInt(getProperty(doc, "cols")));
        parameters.add(getNumCols()); // Adds r/c to list

        for (int i = 0; i < getParameter(doc).size(); i++) {
            setProbability((double) getParameter(doc).get(i));
            parameters.add(getProbability());
        }

        for (int j = 0; j < Integer.parseInt(getProperty(doc, "states")); j++) {
            parameters.add(getInitial(doc, j));
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

    /**
     * Fetches from Document and returns a 2D ArrayList representing the initial states of the grid
     */
    private static ArrayList<Location> getInitial (Document doc, int tag) {
        int i = 1;
        ArrayList<Location> states = new ArrayList<Location>();
        Element eLement = (Element) doc.getElementsByTagName("Initialization").item(0);
        NodeList rows = eLement.getElementsByTagName("Row");
        for (int j = 0; j < rows.getLength(); j++) {
            for (int k = 0; k < rows.item(j).getTextContent().split(" ").length; k++) {
                if (Integer.parseInt(rows.item(j).getTextContent().split(" ")[k]) == tag) {
                    states.add(new Location(k, j));
                }
            }
        }
        return states;
    }

    public Grid<Occupant> reset (ArrayList<Object> parameters) {
        setParameterList(parameters);
        setAuthor((String) parameterList.get(0));
        allOccupants.clear();
        myGrid = new BoundedGrid<Occupant>(Integer.parseInt(parameterList.get(1).toString()),
                                 Integer.parseInt(parameterList.get(2).toString()));
        // setProbability(Double.parseDouble(parameterList.get(3).toString()));
        setProbability((double)Double.parseDouble(parameterList.get(3).toString())); //changes?
        createFires((ArrayList<Location>) parameterList.get(4));
        createEmpties((ArrayList<Location>) parameterList.get(5));
        createTrees((ArrayList<Location>) parameterList.get(6));
        // setExteriorEmpty();

        return myGrid;
    }

    @Override
    public boolean checkFinished () {
        for (Occupant o: allOccupants){
            State s = (State) o;
            if (s.getCurrentState().toString().equals("BURNING")){
                return false;
            }
        }
        return true;
    }

    private void setProbability (double p) {
        spreadProbability = p;
    }

    public double getProbability () {
        return spreadProbability;
    }


    private void createFires (ArrayList<Location> list) {
        for (Location l : list) {
            Occupant o = new State(myGrid, l, new Burning());
            myGrid.put(l, o);
            addToOccupants(o);
        }
    }

    private void createEmpties (ArrayList<Location> list) {
        for (Location l : list) {
            Occupant o = new State(myGrid, l, new Empty());
            myGrid.put(l, o);
            addToOccupants(o);
        }
    }

    private void createTrees (ArrayList<Location> list) {
        for (Location l : list) {
            Occupant o = new State(myGrid, l, new Tree(myGrid, l, spreadProbability));
            myGrid.put(l, o);
            addToOccupants(o);
        }
    }
}
    // private void setExteriorEmpty () {
    // ArrayList<Location> borders = new ArrayList<>();
    //
    // for (int i = 0; i < myGrid.getNumCols(); i++) {
    // Location l = new Location(0, i);
    // borders.add(l);
    // Location l2 = new Location(myGrid.getNumRows(), i);
    // borders.add(l2);
    // System.out.println("borders");
    // }
    //
    // for (int i = 0; i < myGrid.getNumRows(); i++) {
    // Location l = new Location(i, 0);
    // borders.add(l);
    // Location l2 = new Location(i, myGrid.getNumCols());
    // borders.add(l2);
    // System.out.println("borders");
    // }
    //
    // createEmpties(borders);
    // }

