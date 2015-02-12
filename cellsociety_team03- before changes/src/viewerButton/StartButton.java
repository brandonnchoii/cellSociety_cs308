package viewerButton;

import javafx.animation.Timeline;

public class StartButton extends ViewerButton {
    private static final String NAME = "START";
    private static final int WIDTH = 60;
    private static final int HEIGHT = 25;

    public StartButton (Timeline t) {
        super(NAME, WIDTH, HEIGHT);
        myButton.setOnAction(e -> t.play());
    }

}
