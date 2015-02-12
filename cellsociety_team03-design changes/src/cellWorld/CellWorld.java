package cellWorld;

import java.util.ArrayList;


import occupant.Occupant;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.paint.Color;
import xmlreader.ParameterList;
import grid.Grid;
import grid.location.*;
import grid.boundedgrid.BoundedGrid;
import grid.boundedgrid.WrappedBoundedGrid;

public abstract class CellWorld {
    protected Grid<Location> myGrid;
    protected ParameterList params;
    protected ArrayList<Location> myLocations;

    
    public static final int P_WIDTH = 200; //dimensions of the GridPane representing the Grid
    public static final int P_HEIGHT = 200;

    public CellWorld (ParameterList list) {
        params = list;
    }

    public Color[][] reset () {
        myGrid = new BoundedGrid<Location>(params.getRows(), params.getCols(), params.getCellShape());
        createOccupants();
        return getColors();
    }
    
    public String getShape(){
        return params.getCellShape();
    }

    /**
     * creates occupants either with given locations or given numbers
     * 
     * @param map
     * @return
     */
    protected void createOccupants () {
        OccupantFactory factory = new OccupantFactory();
        if (params.getInitType().equals("locs")) {
            Map<String, ArrayList<Location>> locsMap = params.getOccupantLocations();
            factory.validateParams(params.getTitle(), locsMap.keySet(), params.getParamsMap());
            for (String s : locsMap.keySet()) {
                for (Location l : locsMap.get(s)) {
                    factory.create(s, myGrid, l, params.getParamsMap());
                }
            }
        }
        else { // must be percent or hard number
            Map<String, Double> numsMap = params.getOccupantNumbers();
            factory.validateParams(params.getTitle(), numsMap.keySet(), params.getParamsMap());
            if (containsPercent(numsMap)) {
                numsMap = convertToNumbers(numsMap);
            }
            ArrayList<Location> locs = myGrid.getUnoccupiedLocations();
            Collections.shuffle(locs);
            int index = 0;
            for (String s : numsMap.keySet()) {
                for (int i = 0; i < numsMap.get(s); i++) {
                    factory.create(s, myGrid, locs.get(index), params.getParamsMap());
                    index++;
                }
            }
        }
    }

    /**
     * determines whether occupant numbers were given as percents
     * 
     * @param map
     * @return
     */
    private boolean containsPercent (Map<String, Double> map) {
        for (String s : map.keySet()) {
            if (0 < map.get(s) && map.get(s) < 1)
                return true;
        }
        return false;
    }

    /**
     * converts percentages of occupants to numbers
     * 
     * @param percents percentage array of characters p[0] = fish percentage, p[1] = shark
     *            percentage
     * @param original mapping of occupants to percents
     * 
     * @return a new map, mapping occupants to numbers within grid. A copy is made as to not destroy
     *         the original percents
     * 
     */
    private Map<String, Double> convertToNumbers (Map<String, Double> percentMap) {
        double sum = 0;
        Map<String, Double> copyMap = new HashMap<String, Double>(percentMap);
        for (String s : percentMap.keySet()) {
            sum += percentMap.get(s);
        }
        if (sum > 1) { // reassigns percentages if greater than 1
            for (String s : copyMap.keySet())
                copyMap.put(s, percentMap.get(s) / sum);
        }
        for (String s : copyMap.keySet()) { // convert to number
            copyMap.put(s,
                           (double) Math.round(copyMap.get(s) *
                                               myGrid.getUnoccupiedLocations().size()));
        }
        return copyMap;
    }
    
    public Color[][] getColors() {
        Color[][] units = new Color[myGrid.getNumRows()][ myGrid.getNumCols()];
        for (Location l: myGrid.getLocations()){
            Occupant o = l.getOccupant();
            units[l.getRow()][l.getCol()] =  ((o == null) ? (Color.AQUA) : (o.getColor()));
        }
        return units;
    }

    public abstract Grid<Location> step ();

    public abstract boolean checkFinished ();

    public void setParameterList (ParameterList parameters) {
        params = parameters;
    }

    public ParameterList getParameterList () {
        return params;
    }
}
