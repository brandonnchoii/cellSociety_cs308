package userInterface;

import parameterControllers.ParameterController;
import parameterControllers.ParameterReader;
import parameterControllers.ParameterSlider;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import cellWorld.CellWorld;


public class SegregationUserInterface extends UserInterface {

    private final int DEFAULT_ROW_NUM = (Integer) myParameterList.get(1);
    private final int DEFAULT_COL_NUM = (Integer) myParameterList.get(2);
    private final double DEFAULT_PROBSIMILAR = (Double) myParameterList.get(3);
    private final double DEFAULT_PERCENTAGENTO = (Double) myParameterList.get(4);
    private final double DEFAULT_PERCENTEMPTY = (Double) myParameterList.get(5);
    
    private ParameterController numRows;
    private ParameterController numCols;
    private ParameterController probability;
    private ParameterController distribution;
    private ParameterController empty;

    public SegregationUserInterface (BorderPane root, CellWorld cw) {
        super(root, cw);
    }

    @Override
    protected Node makeParametersPanel () {
        VBox result = new VBox();
        HBox dimensionsParameters = new HBox();
        numRows = new ParameterReader(5, 100, DEFAULT_ROW_NUM);
        numCols = new ParameterReader(5, 100, DEFAULT_COL_NUM);
        dimensionsParameters.getChildren().addAll(new Label("Rows:"), numRows.getNode(),
                                                  new Label("Cols:"), numCols.getNode());
        HBox similarityParameter = new HBox();
        probability = new ParameterSlider(0, 100, DEFAULT_PROBSIMILAR);
        similarityParameter.getChildren().addAll(new Label("%Similar:"),
                                             probability.getNode(),
                                             new Label("%"));
        
        HBox agentOParameter = new HBox();
        distribution = new ParameterSlider(0, 100, DEFAULT_PERCENTAGENTO);
        agentOParameter.getChildren().addAll(new Label("%AgentO:"),
                                             distribution.getNode(),
                                             new Label("%"));
        
        HBox emptyParameter = new HBox();
        empty = new ParameterSlider(0, 100, DEFAULT_PERCENTEMPTY);
        emptyParameter.getChildren().addAll(new Label("%Empty:"),
                                             empty.getNode(),
                                             new Label("%"));
        HBox speedParameter = new HBox();
        speedParameter.getChildren().addAll(new Label("Speed:"),
                                            new SpeedSlider(myTimeline).getNode());

        result.getChildren().addAll(dimensionsParameters, similarityParameter, 
                                    agentOParameter, emptyParameter, speedParameter);
        return result;
    }

    @Override
    // replace later with numRows.getValue()
    protected void updateParameters () {
        myParameterList.set(1, 10);
        myParameterList.set(2, 10);
    }

    @Override
    protected Node makeSimulationInfoPanel () {
        VBox result = new VBox();
        result.getChildren().add(myICD.getNode());
        return result;
    }

}