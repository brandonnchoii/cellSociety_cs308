package viewerButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class ResetButton extends ViewerButton {
    private static final String NAME = "RESET";
    private static final int WIDTH = 60;
    private static final int HEIGHT = 25;

    public ResetButton (EventHandler<ActionEvent> e) {
        super(NAME, WIDTH, HEIGHT);
        myButton.setOnAction(e);
    }
}
