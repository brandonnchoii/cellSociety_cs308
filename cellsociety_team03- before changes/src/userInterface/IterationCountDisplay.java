package userInterface;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class IterationCountDisplay {

    private HBox counter;

    public IterationCountDisplay (int count) {
        counter = new HBox();
        counter.getChildren().addAll(new Label("Round: "),
                                     new Label(String.valueOf(count)));
    }

    public Node getNode () {
        return counter;
    }
}
