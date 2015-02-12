package uiManager;

import java.io.File;
import javax.management.RuntimeErrorException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import cellWorld.CellWorld;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import userInterface.GOLUserInterface;
import userInterface.PredPreyUserInterface;
import userInterface.FireUserInterface;
import userInterface.SegregationUserInterface;
import userInterface.UserInterface;
import xmlreader.ParameterList;
import xmlreader.XMLReader;


/**
 * <code>UIManager</code> acts as a controller. It connects the front-end (UserInterface)
 * with the back-end (CellWorld). It controls the mechanics of initializing a GUI and
 * defining frame actions to be performed <br />
 *
 * @author Nathan Prabhu
 */
public class UIManager {

    private static final int SCENE_WIDTH = 800;
    private static final int SCENE_HEIGHT = 600;
    private static final int FRAMES_PER_SECOND = 10;

    private ParameterList list;
    private CellWorld myCellWorld;
    private Stage myStage;

    public UIManager (Stage stage) {
        // TODO: create starter scene/prompt to load xml?
        myStage = stage;
        loadXML();
    }

    public void loadXML () {
        FileChooser fc = new FileChooser();
        fc.setTitle("Select XML file");
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fc.getExtensionFilters().add(extFilter);
        File selectedF = fc.showOpenDialog(new Stage());
        if (selectedF != null) {
            try {
                DocumentBuilderFactory documentBuilderFactory =
                        DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document doc = documentBuilder.parse(selectedF);
                doc.getDocumentElement().normalize();
                list = new XMLReader(doc).getParameterList();
                myCellWorld = list.getCellWorld();
            }
            catch (Exception exception) {
                System.err.println("Invalid XML file");
                throw new RuntimeErrorException(new Error());
            }
        }
        resetWorld();
        initialize(myStage);
    }

    public Color[][] resetWorld () {
        return myCellWorld.reset();
    }
    
    public String getShape(){
        return myCellWorld.getShape();
    }

    /**
     * Initializes scene and builds the simulation
     * 
     * @param stage primary Stage scene will be put on
     * @param width Scene width
     * @param height Scene height
     */
    private void initialize (Stage stage) {
        stage.setTitle(list.getWindowTitle());
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        // TODO: figure out how to work css
        // File f = new File("layoutstyles.css");
        // scene.getStylesheets().add(f.getAbsolutePath());
        stage.setScene(scene);
        switch (list.getTitle()) {
            case "Game of Life":
                buildSimulation(new GOLUserInterface(root, this));
                break;
            case "Predator and Prey":
                buildSimulation(new PredPreyUserInterface(root, this));
                break;
            case "Segregation":
                buildSimulation(new SegregationUserInterface(root, this));
                break;
            case "Spreading of Fire":
                buildSimulation(new FireUserInterface(root, this));
                break;
        }
    }

    /**
     * Builds timeline and resets simulation
     * 
     * @param u UserInterface particular to each cellWorld
     */
    private void buildSimulation (UserInterface u) {
        Timeline timeline = new Timeline();
        final Duration oneFrameAmt = Duration.millis(1000 / FRAMES_PER_SECOND);
        final KeyFrame oneFrame = new KeyFrame(oneFrameAmt, e -> executeFrameActions(u, timeline));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(oneFrame);
        u.setTimeline(timeline);
        u.resetSimulation();
    }

    /**
     * Calls all frame actions to be performed in simulation. These frame actions include painting
     * the grid display with the updated grid, updating the simulation info, and checking for a
     * finishing condition.
     * 
     * @param u UserInterface particular to each cellWorld
     * @param t Timeline
     */
    private void executeFrameActions (UserInterface u, Timeline t) {
        myCellWorld.step();
        u.makeGridDisplay(myCellWorld.getColors(), myCellWorld.getShape());
        u.updateSimInfo(false);
        if (myCellWorld.checkFinished()) {
            t.stop();
        }
    }

    public ParameterList getParameterList () {
        return list; // should make unmodifiable?
    }
}
