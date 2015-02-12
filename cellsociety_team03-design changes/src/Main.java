

import uiManager.UIManager;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main (String[] args) {
        launch(args);
    }
    
    /**
     * Creates UIManager that will mount scene on stage
     */
    public void start (Stage stage) {
        new UIManager(stage);
        stage.show();
    }

}
