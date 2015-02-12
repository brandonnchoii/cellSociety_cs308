package userInterface.uiobject;

import java.util.Map;
import xmlreader.ParameterList;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;


public class UIFactory {

    private final static int MAX_TEXTFIELD_WIDTH = 50;
    private Map<String, Double> myParamsMap;

    public UIFactory (Map<String, Double> paramsMap) {
        myParamsMap = paramsMap;
    }

    public HBox createTextField (String param, Map<String, Double> paramsMap) {
        HBox hb = new HBox();
        boolean isDouble = paramsMap.get(param) != Math.round(paramsMap.get(param));
        TextField tf =
                new TextField((isDouble) ? String.valueOf(paramsMap.get(param))
                                        : String.valueOf((int) Math.round(paramsMap.get(param))));
        tf.textProperty().addListener( (ov, oldVal, newVal) -> updateList(param, Double
                .parseDouble(newVal), paramsMap));
        tf.setMaxWidth(MAX_TEXTFIELD_WIDTH);
        hb.getChildren().addAll(new Label(param), tf);
        return hb;
    }
    
    public HBox createTextField (String param){
        return createTextField(param, myParamsMap);
    }

    private void updateList (String param, double newVal, Map<String, Double> paramsMap) {
        paramsMap.put(param, newVal);
    }

    public HBox createSlider (String param, int minVal, int maxVal, Map<String, Double> paramsMap) {
        HBox hb = new HBox();
        Slider sl = new Slider(minVal, maxVal, paramsMap.get(param));
        sl.valueProperty().addListener( (ov, oldValue, newValue) ->
                                       updateList(param, (double) newValue, paramsMap));
        sl.setShowTickMarks(true);
        sl.setShowTickLabels(true);
        hb.getChildren().addAll(new Label(param), sl);
        return hb;
    }
    
    public HBox createSlider (String param, int minVal, int maxVal){
        return createSlider (param, minVal, maxVal, myParamsMap);
    }

    public Button createButton (String label, EventHandler<ActionEvent> e) {
        Button button = new Button(label);
        button.resize(60, 25);
        button.setOnAction(e);
        return button;
    }

}
