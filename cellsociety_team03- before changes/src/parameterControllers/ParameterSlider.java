package parameterControllers;

import javafx.scene.Node;
import javafx.scene.control.Slider;


public class ParameterSlider extends ParameterController {

    private Slider slider;

    public ParameterSlider (double minValue, double maxValue, double defaultValue) {
        slider = new Slider(minValue,
                            maxValue, defaultValue);
        slider.valueProperty().addListener( (ov, oldValue, newValue) ->
                                           setNewValue((double) newValue));
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
    }

    private void setNewValue (double d) {
        myValue = d;
    }

    public Node getNode () {
        return slider;
    }
}
