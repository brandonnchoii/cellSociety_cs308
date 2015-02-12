package xmlreader;

import grid.location.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import javax.management.RuntimeErrorException;
import userInterface.UserInterface;
import cellWorld.CellWorld;


public class ParameterList {

    //  most efficient way to make package setters / public getters for each?
    private String title;
    private String windowTitle;
    private CellWorld cellWorld;

    private Map<String, ArrayList<Location>> occupantLocations;
    private Map<String, Double> occupantNumbers;
    private Map<String, Double> map;
    private String initType;
    private String cellShape;
   
    
    
    // TODO: should all methods be public!?!
    protected ParameterList(){
        //title = t;
        //cellWorld = cw;
    }
    
    protected void setCellWorld(CellWorld cw){
        cellWorld = cw;
    }

    public CellWorld getCellWorld () {
        return cellWorld;
    }

    public void setOccupantLocations (Map<String, ArrayList<Location>> map) {
        occupantLocations=map;
    }
    
    public Map<String, ArrayList<Location>> getOccupantLocations () {
        return occupantLocations;
    }
 
    public void setOccupantNumbers (Map<String, Double> map) {
        occupantNumbers = map;
    }

    public Map<String, Double> getOccupantNumbers () {
        return occupantNumbers;
    }

    public void setParamsMap (Map<String, Double> m) {
        map = m;
    }
    
    public Map<String, Double> getParamsMap () {
        return map;
        //return Collections.unmodifiableMap(map);
    }

    public String getTitle () {
        return title;
    }

    protected void setTitle (String s) {
        title = s;
    }
    
    public int getRows(){
        return (int) Math.round(map.get("rows"));
    }

//    public void setRows (int r) {
//        rows = r;
//    }
    
    public int getCols(){
        return (int) Math.round(map.get("cols"));
    }
    
//    public void setCols (int c) {
//        cols = c;
//    }
    

    public String getWindowTitle () {
        return windowTitle;
    }

    protected void setWindowTitle (String windowTitle) {
        this.windowTitle = windowTitle;
    }

    public void setInitType (String s) {
        initType = s;
    }
    
    public String getInitType () {
        return initType;
    }
    
    public String getCellShape (){
        return cellShape;
    }

    public void setCellShape (String s) {
        cellShape = s;
        
    }

    private void checkAndSetDimensions(int r, int var){
        if (r>0 && r<100){
            var = r; 
        }
        else{
           System.err.println("Invalid Dimensions");
        }     
    }
}
