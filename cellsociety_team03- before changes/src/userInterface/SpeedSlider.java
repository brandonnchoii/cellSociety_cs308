package userInterface;

import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Slider;


public class SpeedSlider {
    /**
     * <code>SpeedSlider</code> creates a Slider that allows the user to control
     * the speeds of simulation. <br />
     *
     * TODO: add methods to position slider
     *
     * @author Nathan Prabhu
     */
    private final int MIN_FRAMERATE_MULTIPLIER = 0;
    private final int MAX_FRAMERATE_MULTIPLIER = 3;
    private final int DEFAULT_FRAMERATE_MULTIPLIER = 1;

    private Slider slider;

    public SpeedSlider (Timeline t) {
        slider = new Slider(MIN_FRAMERATE_MULTIPLIER,
                            MAX_FRAMERATE_MULTIPLIER, DEFAULT_FRAMERATE_MULTIPLIER);
        slider.valueProperty().addListener( (ov, oldValue, newValue) ->
                                           changeSpeed(t, (double) newValue));
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(1);
        slider.setShowTickLabels(true);
    }

    private void changeSpeed (Timeline t, double newValue) {
        t.setRate(newValue);
    }

    /**
     * Returns slider node to be added to a group.
     */
    public Node getNode () {
        return slider;
    }
}
