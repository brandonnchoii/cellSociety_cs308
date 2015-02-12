package visualUnits;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class TriangleUnit extends Units{
    double[][] vectorL = new double[][]{
                                       { 0.0, 0.0 },
                                       { 0.5, -1.0},
                                       {-0.5, -1.0},
                                     };
    double[][] vectorR = new double[][]{
                                        { 0.0, 0.0 },
                                        { 1.0, 0.0},
                                        { 0.5, -1.0},
                                      };

    public TriangleUnit (int r, int c, double width, double height, Color col) {
        super(r, c);
        unit = new Polygon();
        set(col, unit);
        create(width, height);
    }
    
    public void create(double width, double height){
        double[][] vector = ((x%2 == 0)  ? vectorL : vectorR);
        for (double[] v : vector){
            ((Polygon) unit).getPoints().add(2*width*((x/2) + v[0] + ((y%2 == 1)  ? 0.5 : 0))); //offset for even rows
            ((Polygon) unit).getPoints().add(2*height*(y + v[1]));
        }
    }
}