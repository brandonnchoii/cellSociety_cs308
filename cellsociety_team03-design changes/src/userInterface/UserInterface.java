package userInterface;

import grid.Grid;

import java.util.ArrayList;

import occupant.Occupant;
import uiManager.UIManager;
import userInterface.uiobject.UIFactory;
import userInterface.uiobject.GridDisplay;
import userInterface.uiobject.IterationCountDisplay;
import userInterface.uiobject.SpeedSlider;
import xmlreader.ParameterList;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


/**
 * <code>UserInterface</code> acts as the viewer. It builds the GUI with a BorderPane,
 * in which each pane corresponds to a Panel initialized in resetSimulation(). makeGridDisplay
 * is responsible for building the GridDisplay (with paintGrid()) and makeSimControl() builds
 * the simulation controls.Both are common to all simulations. makeParamsPanel() shows simulation
 * parameters and makeSimInfo() generates real-time simulation info; they both contain some
 * abstraction to be implemented in subclasses. <br />
 * 
 * @author Nathan Prabhu
 *
 */
public abstract class UserInterface {

    private BorderPane myBorderPane;
    protected ParameterList myParams;
    protected Timeline myTimeline;
    private int myIterationCount;
    private UIManager myUIManager;
    protected UIFactory myUIFactory;
    boolean firstTimeThrough;

    public UserInterface (BorderPane g, UIManager uim) {
        myIterationCount = 0;
        myUIManager = uim;
        myParams = uim.getParameterList();
        myUIFactory = new UIFactory(myParams.getParamsMap());
        myBorderPane = g;
        myBorderPane.setPadding(new Insets(10, 20, 10, 20));
    }

    /**
     * Resets a simulation while taking in any new parameters. It generates the four panels of the
     * GUI: grid display, simulation control panel, parameters panel, and current simulation info
     * panel.
     */
    public void resetSimulation () {
        myTimeline.stop();
        makeGridDisplay(myUIManager.resetWorld(), myUIManager.getShape());
        makeSimControlPanel();
        makeParamsPanel();
        updateSimInfo(true);
        firstTimeThrough = false;
    }

    /**
     * Paints the current grid on the grid display.
     * 
     * @param g current grid
     */
    public void makeGridDisplay (Color[][] c, String shape) {
        VBox result = new VBox(); // VBox for centering purposes
        result.getStyleClass().add("vbox");
        result.getChildren().add(new GridDisplay(c, shape).getNode());
        result.setAlignment(Pos.CENTER);
        result.setMaxSize(170, 170);
        myBorderPane.setCenter(result);
    }

    // TODO: add SpeedSlider in here, figure out how to resize
    // (in order to limit visibility of timeline)
    /**
     * Creates simulation control panel. It contains the Start, Stop, Step, and Reset Buttons.
     */
    private void makeSimControlPanel () {
        VBox result = new VBox();
        result.setAlignment(Pos.CENTER);
        result.setSpacing(10);
        result.setMinWidth(40);
        HBox speed = new HBox();
        speed.getChildren().addAll(new Label("Speed:"),
                                   new SpeedSlider(myTimeline).getNode());
        result.getChildren().addAll(myUIFactory.createButton("START", e -> myTimeline.play()),
                                    myUIFactory.createButton("STOP", e -> myTimeline.stop()),
                                    myUIFactory.createButton("STEP", e -> stepTimeline()),
                                    myUIFactory.createButton("RESET", e -> resetSimulation()),
                                    myUIFactory.createButton("LOAD", e -> myUIManager.loadXML()),
                                    speed);
        myBorderPane.setLeft(result);
    }

    private void stepTimeline () {
        myTimeline.stop();
        myTimeline.setCycleCount(1);
        myTimeline.play();
        myTimeline.setCycleCount(myTimeline.INDEFINITE);
    }

    /**
     * Creates the Parameters panel. It contains Parameter Controllers relevant to all parameters
     * relevant to the simulation.
     */
    private void makeParamsPanel () {
        VBox result = new VBox();
        ArrayList <HBox> paramLabels = new ArrayList<HBox>();
        HBox dimensions = new HBox();
        HBox numRows = myUIFactory.createTextField("rows");
        HBox numCols = myUIFactory.createTextField("cols");
        dimensions.getChildren().addAll(numRows, numCols);
        paramLabels.add(dimensions);
        if (myParams.getOccupantNumbers()!=null){
            for (String s: myParams.getOccupantNumbers().keySet()){
                HBox occParam = myUIFactory.createTextField(s, myParams.getOccupantNumbers());
                paramLabels.add(occParam);
            }
        }
        paramLabels = paramsPanelHelper(paramLabels); // add specific params to each simulation
        for (HBox h: paramLabels){
            result.getChildren().add(h);
        }
        myBorderPane.setBottom(result);
    }

    
    private void makeDataGraph () {

    }
    
    /**
     * Organizes all parameters into a node to be placed in Parameters panel. Grid dimensions is by
     * default added to the panel.
     * 
     * @return Node
     */
    protected abstract ArrayList<HBox> paramsPanelHelper (ArrayList <HBox> paramLabels);

    /**
     * Creates the Simulation Info panel. It contains real-time simulation information, relevant to
     * the particular simulation.
     * 
     * @param icd Iteration Counter used in all simulations.
     */
    private void makeSimInfoPanel (ArrayList<Object> updates) {
        myBorderPane.setRight(simInfoPanelHelper(updates));
    }

    /**
     * Organizes all simulation information into a node to be placed in Simulation Info panel.
     * 
     * @param icd Iteration Counter used in all simulations.
     * @return Node
     */
    protected abstract Node simInfoPanelHelper (ArrayList<Object> updates);

    /**
     * Updates all simulation information, with a toggle to reset.
     * 
     * @param isReset boolean that will reset simulation information if true
     */
    public void updateSimInfo (boolean isReset) {
        ArrayList<Object> updates = new ArrayList<Object>();
        myIterationCount = (isReset) ? 0 : myIterationCount + 1;
        updates.add(new IterationCountDisplay(myIterationCount));
        updates.addAll(updateSimInfoParams(isReset));
        makeSimInfoPanel(updates);
    }

    /**
     * Updates all non iteration count simulation information parameters.
     * 
     * @param isReset boolean that will reset simulation information if true
     * @return List of updated simulation info parameters
     */
    protected abstract ArrayList<Object> updateSimInfoParams (boolean isReset);

    /**
     * Gets the ParameterList
     * 
     * @return
     */
    protected ParameterList getParameters () {
        return myParams;
    }

    /**
     * Sets the ParameterList
     * 
     * @param list
     */
    protected void setParameters (ParameterList list) {
        myParams = list;
    }

    /**
     * Sets timeline
     * 
     * @param t
     */
    public void setTimeline (Timeline t) {
        myTimeline = t;
    }
}
