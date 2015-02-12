package occupant.state;

import javafx.scene.paint.Color;
import grid.Grid;
import grid.Location;


public abstract class StateType {

    private Color myColor; // apparently bad to do, what's the alternative?
    private String myType;

    public Color getColor () {
        return myColor;
    }
    
    protected void setColor(Color c){
        myColor = c;
    }
    
    public String toString () {
        return myType;
    }
    
    protected void setType(String s){
        myType = s;
    }
    
    public abstract StateType update();

}
