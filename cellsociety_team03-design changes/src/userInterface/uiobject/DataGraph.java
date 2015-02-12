package userInterface.uiobject;

import java.util.ArrayList;

import occupant.Occupant;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class DataGraph{
    
    private final LineChart<Number, Number> lineChart;
    private final NumberAxis xAxis;
    private final NumberAxis yAxis;
    private XYChart.Series series;
    
    public DataGraph(){
        xAxis = new NumberAxis();
        yAxis = new NumberAxis();
        lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        series = new XYChart.Series();
    }
    
    public void update(ArrayList<Occupant> list, int iterationCount){
        for (Occupant o: list){
            //iterate through all occupants, get how many of them are present
            //graph (iterationCount, number of instances)
            
            
        }
    }
    public Node getNode () {
        return lineChart;
    }
}
