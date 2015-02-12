package viewerButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class UploadButton extends ViewerButton{
    private static final String NAME = "UPLOAD";
    private static final int WIDTH = 60;
    private static final int HEIGHT = 25;
    
    public UploadButton(EventHandler<ActionEvent> e){
        super(NAME, WIDTH, HEIGHT);
        myButton.setOnAction(e);
    }
}
