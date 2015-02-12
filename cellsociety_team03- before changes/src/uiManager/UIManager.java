package uiManager;

import cellWorld.CellWorld;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import userInterface.FireUserInterface;
import userInterface.GOLUserInterface;
import userInterface.UserInterface;


/**
 * <code>UIManager</code> acts as a controller. It connects the front-end (UserInterface)
 * with the back-end (CellWorld). It controls the mechanics of creating the GUI <br />
 *
 * @author Nathan Prabhu
 */
public class UIManager {

    private static final int FRAMES_PER_SECOND = 3;
    private CellWorld myCellWorld;
    private Timeline myTimeline;

    public UIManager (CellWorld cw) {
        myCellWorld = cw;
    }

    public void initialize (Stage stage, int width, int height) {
        stage.setTitle(myCellWorld.getWindowTitle());
        BorderPane root = new BorderPane();
        stage.setScene(new Scene(root, width, height));
        // "new UserInterface" will change to a subclass of UserInterface
        buildSimulation(new GOLUserInterface(root, myCellWorld));
    }

    private void buildSimulation (UserInterface u) {
        final Duration oneFrameAmt = Duration.millis(1000 / FRAMES_PER_SECOND);
        final KeyFrame oneFrame = new KeyFrame(oneFrameAmt, e -> executeFrameActions(u));
        myTimeline = new Timeline();
        myTimeline.setCycleCount(Animation.INDEFINITE);
        myTimeline.getKeyFrames().add(oneFrame);
        u.setTimeline(myTimeline);
        u.resetSimulation();
    }

    private void executeFrameActions (UserInterface u) {
        //System.out.println("test");
        u.paintGrid(myCellWorld.step());
        u.updateSimulationInfo();
        if (myCellWorld.checkFinished()){
            myTimeline.stop();
        }
    }
}
