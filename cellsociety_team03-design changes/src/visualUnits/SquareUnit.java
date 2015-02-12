package visualUnits;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SquareUnit extends Units{

    public SquareUnit (int r, int c, double width, double height, Color col) {
        super (r ,c);
        create(width, height);
        set(col, unit);    
    }
    
    public void create(double width, double height){
        unit = new Rectangle((double) (width * x), (double) (height * y), width , height );
    }
}