package userInterface;

import java.util.ArrayList;
import uiManager.UIManager;
import userInterface.uiobject.IterationCountDisplay;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class GOLUserInterface extends UserInterface {

    public GOLUserInterface (BorderPane root, UIManager uim) {
        super(root, uim);
    }

    @Override
    protected ArrayList<HBox> paramsPanelHelper (ArrayList <HBox> paramLabels) {
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
