package userInterface;

import parameterControllers.ParameterController;
import parameterControllers.ParameterReader;
import cellWorld.CellWorld;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GOLUserInterface extends UserInterface{

    private final int DEFAULT_ROW_NUM = (Integer) myParameterList.get(1);
    private final int DEFAULT_COL_NUM = (Integer) myParameterList.get(2);    
    private ParameterController numRows;
    private ParameterController numCols;
    
    public GOLUserInterface (BorderPane root, CellWorld cw){
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

        HBox speedParameter = new HBox();
        speedParameter.getChildren().addAll(new Label("Speed:"),
                                            new SpeedSlider(myTimeline).getNode());
        result.getChildren().addAll(dimensionsParameters, speedParameter);
        return result;
    }

    //CANT HARDCODE SIZE VV change
    @Override
    protected void updateParameters () {
        myParameterList.set(1, 10);
        myParameterList.set(2, 10);
    }

}
