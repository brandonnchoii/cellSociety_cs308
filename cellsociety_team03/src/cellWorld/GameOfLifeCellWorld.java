package cellWorld;

import grid.Grid;
import grid.Location;
import grid.boundedgrid.BoundedGrid;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import occupant.Occupant;
import occupant.state.State;
import occupant.state.gol.Alive;
import occupant.state.gol.Dead;


public class GameOfLifeCellWorld extends StateCellWorld {
    private static final String WORLD_NAME = "Game of Life";
    
    public GameOfLifeCellWorld (Document doc) {
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

//        for (int i = 0; i < getParameter(doc).size(); i++) {
//            parameters.add((double) getParameter(doc).get(i));
//        }
        
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

    @Override
    public Grid<Occupant> reset (ArrayList<Object> parameters) {
        setParameterList(parameters);
        setAuthor((String) parameterList.get(0));
        allOccupants.clear();
        myGrid = new BoundedGrid<Occupant>(Integer.parseInt(parameterList.get(1).toString()),
                                 Integer.parseInt(parameterList.get(2).toString()));
//        System.out.println((double)Double.parseDouble(parameterList.get(3).toString()));
//        System.out.println((double)Double.parseDouble(parameterList.get(4).toString()));
        createAliveCells((ArrayList<Location>) parameterList.get(3));
        createDeadCells((ArrayList<Location>) parameterList.get(4));
        //return getAllOccupants();
        return myGrid;
    }
        
    
    @Override
    public boolean checkFinished () {
        for (Occupant o: allOccupants){
            State s = (State) o;
            if (s.getCurrentState().toString().equals("ALIVE")){
                return false;
            }
        }
        return true;
    }
    
    private void createAliveCells(ArrayList<Location> list){
        for (Location l : list) {
            Occupant o = new State(myGrid, l, new Alive(myGrid, l));
            myGrid.put(l, o);
            addToOccupants(o);
        }
    }
    
    private void createDeadCells(ArrayList<Location> list){
//        int i = 0;
//        double max = (double)Double.parseDouble(parameterList.get(3).toString());
        for (Location l : list) {
            Occupant o = new State(myGrid, l, new Dead(myGrid, l));
            myGrid.put(l, o);
            addToOccupants(o);    
        }
    }
//           if (i < max){
//               Random generator = new Random();
//               int p = generator.nextInt(99) + 1;
//               if (p <= 10){
//                   Occupant o = new State(myGrid, l, new Alive(myGrid, l));
//                   myGrid.put(l,o);
//                   addToOccupants(o);
//           }
//               else{
//                 Occupant o = new State(myGrid, l, new Dead(myGrid, l));
//                 myGrid.put(l, o);
//                 addToOccupants(o);
//               }
//        }
//    }
//}
}
