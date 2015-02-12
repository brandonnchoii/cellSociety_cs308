package userInterface;

import java.util.ArrayList;
import uiManager.UIManager;
import userInterface.UserInterface;
import userInterface.uiobject.IterationCountDisplay;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class FireUserInterface extends UserInterface {

    public FireUserInterface (BorderPane root, UIManager uim) {
        super(root, uim);
        // probability = new ParameterSlider(0, 100, myParams.getMiscParams().get("probability"));
    }

    @Override
    protected ArrayList<HBox> paramsPanelHelper (ArrayList <HBox> paramLabels) {
        HBox probability = myUIFactory.createSlider("probability", 0, 100); 
        paramLabels.add(probability);
        return paramLabels;
    }


    @Override
    protected Node simInfoPanelHelper (ArrayList<Object> updates) {
        IterationCountDisplay icd = (IterationCountDisplay) updates.get(0);
        return icd.getNode();
    }

    @Override
    protected ArrayList<Object> updateSimInfoParams (boolean isReset) {
        return new ArrayList<Object>();
    }

}
