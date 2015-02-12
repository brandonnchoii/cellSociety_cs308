Team #3: Nathan Prabhu, Catherine Zhang, Brandon Choi

##CellSociety Design:

##Introduction
This Java program will create a framework to animate any 2D grid Cellular Automata simulation, including Schelling’s model of segregation, Wa-Tor World model of predator-prey relationships, the Spreading of Fire, as well as John Conway’s Game of Life. 

**Primary design goals**

The program is able to take in the type of simulation as desired by the user and generate a simulation of a grid of a particular size, where the cells in the grid are updated “simultaneously” at each iteration through the set of rules specific to the type of simulation. The program also allows the user to interact with the simulation interface, such as setting values of the parameters and starting/resetting/stopping the simulation. 

**Primary architecture**

The program contains several abstract classes that outline the general architecture of any CA simulation, and extending the abstraction, the specific classes that are relevant to each simulation, as grouped in separate packages. A XML file, initially read in by a main launcher class, provides information as to which package of classes will be used for the specific simulation as well as initial values of the simulation grid. The grid contains units, which are either cells or characters, depending on the type of simulation; a cell is a unit with a variable state at a set location while a character is a unit with a set state and variable location. At each iteration, the current grid is fetched, and all the units of the grid update either their current state or location based on predetermined rules pertaining to the properties of the unit’s neighbors. At the end of the iteration, the updated grid is returned to CellWorld, and the updating process restarts. A separate handler is responsible for fetching the grid at each frame/iteration and displaying the simulation at the U/I; it also communicates user inputs and commands back to the internal updating process. 


##Overview

(Please note that a UML screenshot was submitted as UMLDesign.jpg to the master branch. Reference UML file to see relationships between classes and the general layout of the project.)

**public class Main**

Main will read in the XML file and create and initialize the appropriate CellWorld instance depending on which cell automata simulation the file specified. A Viewer will be made to display and interact with the player. 

*private void readFile(XML initialFile)*- Takes XML file and saves parameters for initialization 

*private createViewer(CellWorld c)*- Takes the CellWorld created in Main() and instantiates Viewer class

*private void start()*- Takes Stage and sets Timeline

**public abstract class CellWorld**

CellWorld is an abstract class that will be extended to specify a particular cell automata simulation. An instance of a specific CellWorld subclass will be initialized within Main depending on which simulation the user wants to see. 

Grid<Occupant> theWorld
Color emptyColor

*void reset([parameters])*- resets theWorld with the passed in parameters

*void step()* - iterates through all Occupants and updates them

*boolean checkFinished()* - returns whether the simulation has finished based on status of the Grid

*int getNumRows()* - returns the number of rows in theWorld

*int getNumCols()* - returns the number of columns in theWorld

*int getNumCells()* - returns total number of cells in theWorld

**public abstract class Occupant**

Occupant defines any object that can occupy a cell in the GridWorld. For our purposes, these objects will either be States or Characters - which are subclasses. 

Grid<Occupant> myGrid
Location location
Color myColor

*Color getColor()* - returns Color of Occupant

*void setColor(Color newColor)*- sets Color of Occupant

*Grid getGrid()*- returns Grid of Occupant

*Location getLocation()*- returns location of Occupant

*void putSelfInGrid(Grid g, Location loc)*- put Occupant into Grid

*void removeSelfInGrid()*- remove Occupant from Grid

*abstract void act()* - will contain methods on how Occupant acts during each step

*String toString()* - returns String representation of Occupant

*abstract char getChar())* - returns a char letter representation of Occupant - implemented in subclass


**public abstract class State extends Occupant**

State represents a non-movable Occupant. It does not move from a particular location

final Location location
State myPrevState

*abstract State updateState()*- determines State, based on neighboring States, at the next step of the siulation

*State getPrevState()*- returns previous State

*void setPrevState()*- sets previous State

**public abstract class Character extends Occupant**

Character is a movable Occupant; it does not have a final location.

*void moveTo (Location loc)* - determines where Character moves, based on neighboring Characters, at next step

*Location getPrevLocation()* - returns Character's previous Location 

*void setPrevLocation()* - sets Character's previous location


**public interface Grid<E>**

Grid is a collection of Objects, mapped out by Location. This is an interface just in case methods need to be implemented differently due to non-rectangular mappings.

*int getNumRows()* - returns rows in grid 

*int getNumCols()* - returns columns in grid

*boolean isValid(Location loc)* - returns whether the loc is on the Grid

*E put(Location loc, E obj)* - puts object at the location and returns whatever was there (null if empty)

*E remove(Location loc)* - removes object from the location

*E get(Location loc)* - gets object at the location

*ArrayList<Location> getOccupiedLocations()*

**public abstract class AbstractXYGrid**

implements many methods in Grid assuming locations map onto Grid via XY coordinates

**public class BoundedGrid extends AbstractXYGrid**

An AbstractXYGrid with defined edges and corners

**public class WrappedBoundedGrid extends AbstractXYGrid**

An AbstractXYGrid with edges that wrap around, creating a toroidal grid

**public class Location**

Location designates where exactly in the grid a particular cell is. Rather than using two ints or doubles to represent a coordinate system, our project will be implementing the Location class as a single substitute. 

*void getRow()*- returns the location's row

*void getCol()*- returns the location's column

*Location getAdjacentLocation(int direction)*- returns the location in any of the eight compass directions

*int getDirectionTowards(Location target)*- returns direction to another location

**public abstract class UIManager**

The UIManager assembles the pieces of JavaFX animation. It sets the scene via GUI interface, creates the Timeline the iterator steps through, and retrieves the current grid to pass to the GUI.
private Timeline timeline

*void initialize()* - places the scene on the stage

*Grid getGrid()*- returns the current Grid of the CellWorld

*setTimeline(Timeline t)*- sets Timeline to current scene 

*getUI(Grid g, Timeline t)*- returns a UserInterface which contains a scene with all nodes of the group laid out

**public abstract class UserInterface**

A specific subclass that extends UserInterface will actually create and add all the components of the GUI the user sees. It is abstract because different cell automata simulations may require different components to be added to the GUI such as a graph representing data ratios between states as well as different parameter controllers depending on how many parameters the simulation has. 

*createButtons()*- creates all the necessary buttons needed. Each button created will already have extended ViewerButton and thus have a certain function coded into it. Components such as the text, size, and label will be created in the constructors of each specific button subclass.

*createIterationCounter(Timeline t)*- creates the iteration counter that is to be displayed by using how many frames have passed in the specific timeline.

*createParametersControls()*- creates input text boxes and/or sliders that allow the user to alter parameter settings and input new ones.

*updateGridDisplay(Grid g)*- updates the Grid’s visuals based on each Cell’s state and their appropriate graphics
addComponentsToScene()- adds all created components such as buttons or parameter controllers to myScene.

**public abstract class ViewerButton**

ViewerButton provides a consistent layout/styling to all buttons we use in our UI

*abstract setAction()*- sets the event handler associated with the button

**public class ResetButton extends ViewerButton**

reinitializes cellWorld and creates new timeline

**public class StartButton extends ViewerButton**

plays timeline

**public class StopButton extends ViewerButton**

pauses timeline

**public class StepButton extends ViewerButton**

goes to next frame in timeline

**public class DataGraph**

takes real time information of characters/states and graphs them per frame

**public class SpeedSlider**

This class allows the user to control how many frames to fast forward in the simulation and is an extension of the JavaFX Slider. This will be displayed on the UserInterface.
modifySpeed() changes the speed at which frames are iterated depending on the user mouse input

**public abstract class ParameterController**

This class describes the controllers, being either sliders and text boxes, that allow the user to alter parameter settings and input new ones. It is instantiated at the UserInterface.It communciates to Main to initiate a new CellWorld. 

**public abstract class ParameterSlider extends ParameterController**

Sliders are one of the controllers that alter parameter settings. 

**public abstract class ParameterReader extends ParameterController**

Text boxes input are read to alter parameter settings. 

**public class IterationCountDisplay**

This is to show how many times the cell automata has updated. This will be displayed on UserInterface.

*void incrementCount()*- increments the count by one

##User Interface

(Design was submitted as InterfaceDesignPicture.jpg on master branch.)

The general layout of the UI is attached. Each visualization will have a Grid representation, four simulation control Buttons (Reset, Start, Stop, and Step), a Text input option to change the size of the Grid, and a Slider to control the speed of the simulation. At times, some simulations will require extra parameters. These will be inserted in an area below the size and speed controls. To the right of these parameters, there will be a pane solely dedicated to giving the user real-time information about the current state of the simulation. This information could include the current iteration number, a graph of Characters/States over time, and a percentage of static Characters. 

The only types of errors that can occur in this UI would occur when the user enters bad data into Text fields. We will implement the JavaFX “Text Validators” to flash red to indicate these errors, which could include inputting non-numeric entries, non-integer entries, negative entries, and numeric entries outside of the allowable ranges. 

A miscellaneous, perhaps more ambitious, UI component will be adding in an onClick feature that will allow the user to change the current state of current cells when resetting the Grid. This could be implemented in the “Fire” simulation to choose which cell or cells contain the FIRE state. 


##Design Details

**Main Class**

readFile() will read a XML File and extract relevant parameters for the simulation: the window title and the author will be stored in Strings, and initialization values such as dimensions of the Grid, parameter values, and initial Grid states will be stored in int[]. A new CellWorld myWorld is initialized using these values. myWorld is passed into the createViewer, which instantiates the Viewer class, which acts as the intermediary between the CellWorld and U/I. 


**CellWorld Class**

initialize(int rows, int cols) creates the initial state of the Grid. It can take in parameters, such as dimensions, from readFile as well. createCells(Grid g) is called in this method as well so that each cell in the grid is generated with the appropriate state. Passing in the Grid also allows the cell to know the grid is currently apart of. updateAllUnits() does the logic of updating an entire grid for each timestep. It iterates through all the cells, runs their specific update() function with reference to the current grid, and createsCells() on the newGrid which is returned. setCurrentGrid is called with the return of updateAllUnits(). currentGrid is then passed to Viewer. checkFinish() is an optional method that will check for a finishing condition (i.e. no more movements) in the simulation in order to signal stopping the timeline animation.

**Abstract Grid Class**

Grid contains methods that can access the current layout of the grid itself. It can retrieve numRows and numCols. getNeighborCells(Cell c) first returns the cells that neighbor a cell. getNeighborStates(Cell c) uses getNeighborCells() to return a list of cell states, which will then be used for any cell’s update function. findEmptyCells() retrieves a list of all cells that currently have no states/characters in them. This would be useful when a character wants to move to an empty location, and they use this list to see where they can move. getDirection(cell c1, cell c2) will be used when, if in some future simulation, it is necessary to know in what compass direction neighbors are if that is a rule’s parameter. 

**Bounded Grid**

Will implement most of Grid’s methods, especially getNeighborCells(). The booleans in Cell such as isCorner and isEdge will help in writing rules for neighbrors here.

**UnboundedGrid**

Will have a general getNeighborCells() method, but the isEdge and isCorner cells will now have their neighbors updated with wrapAround(Location c). wrapAround allows the grid to assign neighbors to cells on other extremes of the grid, as if a toroid implementation were at play.

**Cell Class**

Cell is a unit referring to a specific location on the Grid. Because of this general characteristic, the Cell class is made non-abstract, unlike many other main classes of the program. The Cell class contains several methods that gives information regarding its location on the Grid: getLocation() returns it’s 2D Location; isCorner() and isEdge() tells whether the Cell is residing at the corner or edge of the Grid, respectively. Traversing through Cells is also one of two methods through which the Grid is updated at each iteration. When CellWorld calls updateAllUnits(), it loops through the Grid and looks at the State of each Cell through getState() and/or getPreviousState(), and updates its current and previous State through setState() and setPreviousState(). One important design feature of the Cell class is that it is instantiated with the parameter Grid. This allows one particular Cell to access other Cells on the Grid, which is particularly useful in scenarios where Characters on a Cell would relocate from one Cell to another, or change the status of a Character residing on another Cell. 

**Abstract Character Class**

The Character class is created to handle simulations where change in the simulation is characterized by particular “characters” updating their location or interacting with other “characters.” This requires a different approach of updating the simulation than through Cells, where we’re looking at each set location in the Grid and updating the States in each. Instead, the Character class allows updating through the perspective of the Character: in setCharacter(), the Character is moving to a designated cell; in removeCharacter, the Character is removed from a designated cell. These two methods capture the “movement” of a Character from one Cell to another. getCell() returns the current location of the Character. A list of all Characters is looped through in CellWorld’s updateAllUnits(), and each Character will call the aforementioned methods to determine its new location in the Grid. 

**Abstract State Class**

The State is abstracted to reflect all possible number and type of states that different CA simulations can take on. A State is represented by a general Image im rather than a particular Shape or Color. During the updating process, each Cell gets its current State. setNextState contains logic that will determine what the next State should be depending on the current State and its neighbours, which is available from Grid of the Cell.  

**Viewer Class**

The Viewer assembles the pieces of JavaFX animation. It is a middleman, in a sense, in that it communicates between the UI and the logic of the simulation. It mounts the UI on our stage with initialize, and allows a timeline to be associated to “simulation” in setTimeline(Timeline t). The logic of the simulation is retrieved through getGrid() which calls the getCurrentGrid() method in the particular cellWorld. getUI(Grid g, Timeline t) retrieves the User Interface, which is essentially a Scene that will be mounted on our stage in initalize().

**UserInterface (abstract)**

The abstraction allows any custom user interface to be built. Inherent to the UI is a Scene, which can be retrieved in a getScene() method in UI. There are a bunch of methods that create Controller object that can be implemented in the UI subclass. Lastly, one can addComponentsToScene(), which will put of all these controller objects into the group’s children and therefore be a part of the scene. 

**ViewerButton (abstract)**

An abstract class to make styling and layout consistent for all buttons. The setAction() method will define the particular action particular to each button.

**Start/Stop/Reset/Step Button**

These classes are created to save time later on calling these often used buttons. Their functionality all plays with the timeline, which will have to be accessed through the viewer. 

**DataGraph**

This class has the possibility to graph states/characters at every frame. It will be apart of the animation, and so should have its update function sync with that of the CellWorld. graphData() generally selects the parameters we want to track and graph.

**SpeedSlider**

Class that can slow/speed up the animations. modifySpeed() is the only method here that does that and it will probably add delays in between each frame in the timeline. 

**IterationCountDisplay**

Class that will keep a counter on how many frames have passed. 

**GridDisplay**

Will paint a grid display based on the current layout of the getGrid() from the viewer. paintGrid() is responsible for carrying out this action. It will also be constructed based on the parameters in the XML file.

**ParameterController (abstract) **

This will give the user the ability to reset parameters that will be implemented the next time a game is reset. This class will have to have access to the CellWorld’s initialization in order to let it know about the parameter changes. changeParameter() will be the method that accomplishes this communication.

**ParameterReader (abstract)**

ParameterController that allows a text-based input for unbounded parameters. 

**ParameterSlider (abstract)**

ParameterController that implements a slider for bounded parameters. 


##Design Considerations

One design issue that has yet to be resolved is the relationship between State and Character classes. Originally, the two classes were created to reflect the two different types of CA simulations: one, where each set location is changed depending solely on the states of its neighbors, like the Spreading of Fire, and two, where change is expressed by the movement of specific characters to different locations based on their neighboring characters, like the predator-prey relationship simulations. A Character and State bear similarity in that they can both take up a Cell, yet they’re different in that while a Character can change locations, a particular State cannot. We made the decision of having Character extend State because it creates convenience for Cell, where methods like getState() can be extended to getNeighbor(). We should consider whether the benefit of such convenience outweighs the potential confusion and limitation in extensibility caused by establishing such relationship between the two classes.

One key assumption we made is that the grid will have rectangular cells, thus and x-y coordinate system would work to track their locations. However, if the cells were hexagonal or triangular, our entire location system would not work. Global locations, then would have to be tracked with completely new logic.

Another assumption we made is that edges and corners might have different rules for determining neighbors, based on the simulation chosen. This condition is taken care of in the getNeighborCells methods in BoundedGrid and UnboundedGrid. 

##Team Responsibilities

Brandon: 
Primary: Occupant, State, Character, CellWorld, ViewerButton and all its universal subclasses (StartButton, StopButton, ResetButton, StepButton)
Secondary: Viewer, Location, Fire simulation

Catherine: 
Primary: Main, Location, ParameterController (all subclasses), XML format <br>
Secondary: U/I, DataGraph, Segregation simulation

Nathan:
Primary: Viewer, UI, Grid, Grid (subclasses), IterationCountDisplay, GridDisplay, SpeedSlider, DataGraph<br>
Secondary: Occupant, Main, CellWorld, predator-prey

We plan on meeting as often with our TA as possible in order to consistently review design and any code we have implemented. We all have certain classes we want to focus on when working individually but understand that it will be impossible to make significant progress without meeting together. Therefore, we plan on utilizing pair programming (but with three people!) as often as possible in order to foster a shared knowledge on as much code as possible. We have filled out an availability chart in order to see when we can meet with our TA as well as when we can meet to code together. We have overlaps in the secondary classes we each want to focus on and so we will be sure to utilize GitHub often to work with the most recent versions of code in order to avoid merge conflicts. Moreover, we will all work on separate branches when working individually and before we commit anything to master, we will make sure we all agree on the changes and additions. We want to start by first completing all abstract classes and then focusing on simple front end matters such as being able to visualize the grid. This will be important for us in testing the program as we go. Afterwards, we want to implement any specific cell automata cases that are thrown at us and hope that our abstractions will save us lots of time in not having to repeat code or redesign our project. Finally, we understand that our code will not necessarily be robust enough to withstand all cases and so we plan on finishing our initial implemenations of at least the abstract classes very early on in the next few days so that we can later focus on addressing possible issues concerning adaptability of code. We hope to get a better sense of where we need to improve our design as well as where we did well after meeting with our TA and/or Professor Duvall. 


