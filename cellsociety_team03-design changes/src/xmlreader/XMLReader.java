package xmlreader;

import grid.location.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.management.RuntimeErrorException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import cellWorld.FireCellWorld;
import cellWorld.GameOfLifeCellWorld;
import cellWorld.PredatorPreyCellWorld;
import cellWorld.SegregationCellWorld;


public class XMLReader {
    private ParameterList list;
    private Document myDoc;

    public XMLReader (Document doc) {
        // error should be thrown if not caSimulation
        myDoc = doc;
        list = new ParameterList();
        getProperties();
        switch (list.getTitle()) {
            case "Game of Life":
                list.setCellWorld(new GameOfLifeCellWorld(list));
                break;
            case "Predator and Prey":
                list.setCellWorld(new PredatorPreyCellWorld(list));
                break;
            case "Segregation":
                list.setCellWorld(new SegregationCellWorld(list));
                break;
            case "Spreading of Fire":
                list.setCellWorld(new FireCellWorld(list));
                break;
            default:
                throwError("Invalid Title");
        }
        list.setParamsMap(getParams());
        switch (list.getInitType()) {
            case ("locs"):
                list.setOccupantLocations(readLocations());
                break;
            case ("numbers"):
                list.setOccupantNumbers(getAllOccupantNumbers());
                break;
            default:
                throwError("Invalid Initialization");
        }
    }

    private void getProperties () {
        Element e = (Element) myDoc.getElementsByTagName("properties").item(0);
        String s = e.getAttribute("prop");
        String[] props = s.split("-");
        if (props.length != 4) {
            throwError("Invalid properties");
        }
        list.setTitle(props[0]);
        list.setWindowTitle(props[0] + " by " + props[1]);
        list.setInitType(props[2]);
        list.setCellShape(props[3]);
    }

    public ParameterList getParameterList () {
        return list;
    }

    private Map<String, Double> createIdMap (String tag) {
        Map<String, Double> map = new HashMap<String, Double>();
        NodeList list = myDoc.getElementsByTagName(tag); // gets all occupants
        for (int i = 0; i < list.getLength(); i++) {
            Element e = (Element) list.item(i);
            map.put(e.getAttribute("id"), Double.parseDouble(e.getTextContent()));
        }
        return map;
    }

    // general method to read off any xml grid representation
    // could be used for occupant mapping OR location-based parameters
    protected Map<String, ArrayList<Location>> readLocations () {
        // create gridKey to store Occupant : number relations within grid
        Map<String, Double> gridKey = createIdMap("occupant");
        // create occMap
        Map<String, ArrayList<Location>> occMap = new HashMap<String, ArrayList<Location>>();

        for (String s : gridKey.keySet()) { // i corresponds to the number in the xml grid
            ArrayList<Location> locs = new ArrayList<>();
            NodeList rows = myDoc.getElementsByTagName("row");
            if (rows.getLength() != list.getRows()) {
                throwError("Wrong number of rows");
            }
            for (int j = 0; j < rows.getLength(); j++) {
                String[] cols = rows.item(j).getTextContent().split(" ");
                if (cols.length != list.getCols()) {
                    throwError("Wrong number of cols");
                    ;
                }
                
                for (int k = 0; k < cols.length; k++) {
                    double gridKeyVal = gridKey.get(s);
                    if (Integer.parseInt(cols[k]) == gridKeyVal) {
                        switch (list.getCellShape()) { // Adds the specific shape-Location based on type specified by XML  
                            case "Square":
                                locs.add(new SquareLocation(j, k));
                                break;
                            case "Triangle":
                                locs.add(new TriangleLocation(j,k));
                                break;
                            case "Hexagon":
                                locs.add(new HexagonLocation(j,k));   
                                break;
                        }
                    }
                }
            }
            occMap.put(s, locs);
        }
        return occMap;
    }

    // method to read occupant numbers/percentages, if no occupant grid is given
    // simulation can determine if percent/number if values <1 (percent) or >1 (number)
    private Map<String, Double> getAllOccupantNumbers () {
        return createIdMap("number");
    }

    // TODO: get Param Ranges... Map<String, Integer[]>
    private Map<String, Double> getParams () {
        return createIdMap("param");
    }

    private void throwError (String s) {
        System.err.println(s);
        throw new RuntimeErrorException(new Error());
    }
}
