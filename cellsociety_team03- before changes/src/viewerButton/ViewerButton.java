package viewerButton;

import javafx.scene.Node;
import javafx.scene.control.Button;

public abstract class ViewerButton {

    protected Button myButton;

    public ViewerButton (String label, double w, double h) {
        myButton = new Button(label);
        myButton.resize(w, h);
    }

    public Node getNode () {
        return myButton;
    }
}

// reset should call BuildAndSetSimluation
