package cellWorld;

import java.util.ArrayList;

import occupant.Occupant;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import grid.Grid;
import javafx.scene.paint.Color;

public abstract class CellWorld {
    protected Grid<Occupant> myGrid;
    private static final Color emptyColor = Color.WHITE; // or whatever color we want this to be
    private int numRows;
    private int numColumns;
    private boolean finished;
    private String myName; // add methods
    protected ArrayList<Object> parameterList; 
    protected ArrayList<Occupant> allOccupants;
    private String windowTitle;
    private String author;

    public CellWorld (String name) {
        myName = name;
        finished = false;
        allOccupants = new ArrayList<Occupant>();
    }

    public abstract Grid<Occupant> reset (ArrayList<Object> parameters);

    public abstract boolean checkFinished ();

    public abstract Grid<Occupant> step();

    public void setNumRows (int r) {
        numRows = r;
    }

    public int getNumRows () {
        return numRows;
    }

    public void setNumCols (int c) {
        numColumns = c;
    }
    
    /**
     * Fetches from Document and returns value, one of the properties of the CA simulation, as
     * specified by a tag
     */
    public static String getProperty (Document doc, String tag) {
        Element eLement = (Element) doc.getElementsByTagName("Properties").item(0);
        return eLement.getAttribute(tag);
    }

    public int getNumCols () {
        return numColumns;
    }

    public void setParameterList (ArrayList<Object> parameters) {
        parameterList = parameters;
    }

    public ArrayList<Object> getParameterList () {
        return parameterList;
    }

    public String getWindowTitle () {
        return windowTitle;
    }
    
    protected void setWindowTitle (String s) {
        windowTitle = s;
    }

    public void setFinished () {
        finished = true;
    }

    public String getName () {
        return myName;
    }
    
    public void setAuthor(String n){
        author = n;
    }
    
    public String getAuthor(){
        return author;
    }
    
    public void addToOccupants(Occupant o){
        allOccupants.add(o);
    }
    
    public ArrayList<Occupant> getAllOccupants(){
        return allOccupants;
    }
}
