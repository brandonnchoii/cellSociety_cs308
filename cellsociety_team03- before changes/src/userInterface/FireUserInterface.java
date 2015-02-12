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


public class FireUserInterface extends UserInterface {

    private final int DEFAULT_ROW_NUM = (Integer) myParameterList.get(1);
    private final int DEFAULT_COL_NUM = (Integer) myParameterList.get(2);
    private final double DEFAULT_PROBABILITY = (Double) myParameterList.get(3);

    private ParameterController numRows;
    private ParameterController numCols;
    private ParameterController probability;

    public FireUserInterface (BorderPane root, CellWorld cw) {
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
        HBox spreadParameter = new HBox();
        probability = new ParameterSlider(0, 100, DEFAULT_PROBABILITY);
        spreadParameter.getChildren().addAll(new Label("Spread Probability:"),
                                             probability.getNode(),
                                             new Label("%"));
        HBox speedParameter = new HBox();
        speedParameter.getChildren().addAll(new Label("Speed:"),
                                            new SpeedSlider(myTimeline).getNode());
        result.getChildren().addAll(dimensionsParameters, spreadParameter, speedParameter);
        return result;
    }

    @Override
    // replace later with numRows.getValue()
    protected void updateParameters () {
        myParameterList.set(1, 10);
        myParameterList.set(2, 10);
        myParameterList.set(3, 40);
    }

    @Override
    protected Node makeSimulationInfoPanel () {
        VBox result = new VBox();
        result.getChildren().add(myICD.getNode());
        return result;
    }

}
