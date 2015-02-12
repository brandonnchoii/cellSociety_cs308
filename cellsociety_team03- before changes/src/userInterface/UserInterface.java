package userInterface;

import java.io.File;
import grid.Grid;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import occupant.Occupant;
import viewerButton.ResetButton;
import viewerButton.StartButton;
import viewerButton.StepButton;
import viewerButton.StopButton;
import viewerButton.UploadButton;
import cellWorld.CellWorld;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public abstract class UserInterface {


    protected ArrayList<Object> myParameterList;
    protected Timeline myTimeline;
    private CellWorld myCellWorld;
    private int myIterationCount;
    protected IterationCountDisplay myICD;
    private GridDisplay myGridDisplay;
    private BorderPane myBorderPane;

    public UserInterface (BorderPane g, CellWorld cw) {
        myBorderPane = g;
        myBorderPane.setPadding(new Insets(10, 20, 10, 20));
        myParameterList = cw.getParameterList();
        myCellWorld = cw;
        myIterationCount = 0;
        myICD = new IterationCountDisplay(myIterationCount);
    }

    private Node makeGridDisplay (GridDisplay g){
        VBox result = new VBox();
        result.getChildren().add(g.getNode());
        result.setPadding(new Insets(50));
        result.setAlignment(Pos.CENTER);
        return result;
    }

    private Node makeButtonsPanel () {
        VBox result = new VBox();
        result.setAlignment(Pos.CENTER);
        result.setSpacing(15);
        result.getChildren().addAll(new StartButton(myTimeline).getNode(),
                                    new StopButton(myTimeline).getNode(),
                                    new StepButton(myTimeline).getNode(),
                                    new ResetButton(e -> resetSimulation()).getNode(),
                                    new UploadButton(e -> uploadXML()).getNode());
        return result;
    }
    
    public void uploadXML(){
        FileChooser fc = new FileChooser();
        fc.setTitle("Select a XML file");
        File selectedF = fc.showOpenDialog(new Stage());
        if (selectedF != null){
            try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(selectedF);

            XMLReader reader = new XMLReader(document, myCellWorld);
            reader.reset();
            paintGrid(myCellWorld.step());
            }
            catch (Exception exception){
                System.out.println("error");
            }
        }            
    }

    protected abstract Node makeParametersPanel ();

    protected Node makeSimulationInfoPanel (){
        return myICD.getNode();
    }

    public void resetSimulation () {
        myTimeline.stop();        
        updateParameters(); 
        paintGrid(myCellWorld.reset(myParameterList));;
        myBorderPane.setCenter(makeGridDisplay(myGridDisplay));
        myBorderPane.setLeft(makeButtonsPanel());
        myBorderPane.setBottom(makeParametersPanel());
        myBorderPane.setRight(makeSimulationInfoPanel());
        resetSimulationInfo();
    }

    protected abstract void updateParameters ();

    public void paintGrid (Grid<Occupant> g) {
        myGridDisplay = new GridDisplay(g);
        myBorderPane.setCenter(makeGridDisplay(myGridDisplay));
    }

    public void updateSimulationInfo () {
        myIterationCount++;
        myICD = new IterationCountDisplay(myIterationCount);
        //myBorderPane.setCenter(myGridDisplay.getNode());
        myBorderPane.setRight(myICD.getNode());
    }

    private void resetSimulationInfo () {
        myIterationCount=0;
        myICD = new IterationCountDisplay(myIterationCount);
        myBorderPane.setRight(myICD.getNode());
    }

    public void setParameters (ArrayList<Object> list) {
        myParameterList = list;
    }

    public void setTimeline (Timeline t) {
        myTimeline = t;
    }

    public ArrayList<Object> getParameters () {
        return null;
    }

}
