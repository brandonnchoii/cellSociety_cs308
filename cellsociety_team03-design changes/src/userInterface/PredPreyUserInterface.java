package userInterface;

import java.util.ArrayList;
import java.util.Collections;
import uiManager.UIManager;
import userInterface.UserInterface;
import userInterface.uiobject.IterationCountDisplay;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class PredPreyUserInterface extends UserInterface {

    public PredPreyUserInterface (BorderPane root, UIManager uim) {
        super(root, uim);
    }

    @Override
    protected ArrayList<HBox> paramsPanelHelper (ArrayList <HBox> paramLabels) {
        HBox fishBreed = myUIFactory.createTextField("FishBreed"); 
        HBox sharkBreed = myUIFactory.createTextField("SharkBreed");
        HBox sharkStarve = myUIFactory.createTextField("SharkStarve");
        Collections.addAll(paramLabels, fishBreed, sharkBreed, sharkStarve);
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
