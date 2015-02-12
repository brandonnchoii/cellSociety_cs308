package visualUnits;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class Units{
    int x;
    int y;
    Shape unit;
    
    public Units (int r, int c) {
        x = r;
        y = c;   
    }   

    public void set(Color c, Shape shape){
        shape.setFill(c);
        shape.setStroke(Color.BLACK);
    }
    
    public Shape getShape(){
        return unit;
    }
}
