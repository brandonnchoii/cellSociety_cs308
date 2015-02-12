package userInterface;
import grid.Location;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cellWorld.CellWorld;

public class XMLReader {
    private CellWorld myWorld;
    public XMLReader(Document doc, CellWorld cw){
        myWorld = cw;
        myWorld.setParameterList(makeList(doc));
    }
    
    private ArrayList<Object> makeList (Document doc) {
        ArrayList<Object> parameters = new ArrayList<Object>();
        parameters.add(myWorld.getProperty(doc, "author")); // Adds author to list
        myWorld.setNumRows(Integer.parseInt(myWorld.getProperty(doc, "rows")));
        parameters.add(myWorld.getNumRows());
        myWorld.setNumCols(Integer.parseInt(myWorld.getProperty(doc, "cols")));
        parameters.add(myWorld.getNumCols()); // Adds r/c to list

//        for (int i = 0; i < getParameter(doc).size(); i++) {
//            myWorld.setProbability((double) getParameter(doc).get(i));
//            parameters.add(myWorld.getProbability());
//        }

        for (int j = 0; j < Integer.parseInt(myWorld.getProperty(doc, "states")); j++) {
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
    
    public void reset(){
        myWorld.reset(myWorld.getParameterList());
    }
}
