package viewerButton;

import javafx.animation.Timeline;

public class StepButton extends ViewerButton {
    private static final String NAME = "STEP";
    private static final int WIDTH = 60;
    private static final int HEIGHT = 25;
    private Timeline myTimeline;

    public StepButton (Timeline t) {
        super(NAME, WIDTH, HEIGHT);
        myTimeline = t;
        myButton.setOnAction(e -> playOnce());
    }

    private void playOnce () {
        myTimeline.stop();
        myTimeline.setCycleCount(1);
        myTimeline.play();
        myTimeline.setCycleCount(myTimeline.INDEFINITE);
    }
}
