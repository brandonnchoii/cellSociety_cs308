package visualUnits;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class HexagonUnit extends Units {
    double[][] vector = new double[][]{
                                { 0.577, 0},
                                { 0.289, -0.5},
                                { -0.289, -0.5},
                                { -0.577, 0},
                                { -0.289, 0.5},
                                { 0.289, 0.5}
                              }; //change coordinates.

    public HexagonUnit (int r, int c, double width, double height, Color col) {
        super(r, c);
        unit = new Polygon();
        create(width, height);
        set(col, unit);
        
    }
    
    public void create(double width, double height){
        for (double[] v : vector){
            ((Polygon) unit).getPoints().add(x*width + 1.07*width*v[0]);
            ((Polygon) unit).getPoints().add(y*height + 1.07*height*((x%2 == 1) ? v[1]-0.5 : v[1])); 

        }
    }
}