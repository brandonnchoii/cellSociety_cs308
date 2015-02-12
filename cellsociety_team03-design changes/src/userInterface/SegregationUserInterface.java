package userInterface;

import java.util.ArrayList;
import uiManager.UIManager;
import userInterface.uiobject.IterationCountDisplay;
import userInterface.uiobject.SpeedSlider;
import userInterface.uiobject.parametercontrols.ParameterReader;
import userInterface.uiobject.parametercontrols.ParameterSlider;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class SegregationUserInterface extends UserInterface {

    public SegregationUserInterface (BorderPane root, UIManager uim) {
        super(root, uim);
    }

    @Override
    protected ArrayList<HBox> paramsPanelHelper (ArrayList <HBox> paramLabels) {
        HBox similarity = myUIFactory.createTextField("Similarity"); 
        paramLabels.add(similarity);
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