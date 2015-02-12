package viewerButton;

import javafx.animation.Timeline;

public class StopButton extends ViewerButton {
    private static final String NAME = "STOP";
    private static final int WIDTH = 60;
    private static final int HEIGHT = 25;

    public StopButton (Timeline t) {
        super(NAME, WIDTH, HEIGHT);
        myButton.setOnAction(e -> t.pause());
    }
}
