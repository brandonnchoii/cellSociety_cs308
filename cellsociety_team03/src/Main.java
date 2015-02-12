
import java.io.File;

import org.w3c.dom.Document;

import cellWorld.FireCellWorld;
import cellWorld.GameOfLifeCellWorld;
import cellWorld.SegregationCellWorld;
import uiManager.UIManager;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class Main extends Application {
    private static final int SCENE_WIDTH = 500;
    private static final int SCENE_HEIGHT = 500;


    public static void main (String[] args) {
        launch(args);
    }

    /**
     * Parses a XML file and returns a Document
     */
    private static Document readFile (String XML) {
        Document doc = null;
        try {
            File XmlFile = new File(XML);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(XmlFile);
            doc.getDocumentElement().normalize();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

    /**
     * Fetches initial values for simulation from XML file; Initializes CellWorld and UIManager.
     */
    public void start (Stage stage) {
        Document doc = readFile("gol.xml");
        GameOfLifeCellWorld world = new GameOfLifeCellWorld(doc);
        UIManager myUIManager = new UIManager(world);

        myUIManager.initialize(stage, SCENE_WIDTH, SCENE_HEIGHT);
        stage.show();
    }

}
