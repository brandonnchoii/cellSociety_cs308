#CellSociety

Nathan Prabhu, Catherine Zhang, Brandon Choi

**Date Started:** 1/23/15

**Date Finished:** 2/9/15

**Estimate number of hours spent:** ~85

**People's roles:**: </br>
Nathan focused on implementing the XML Reader as well as the GUI features that allowed the user to change the parameters dynamically. He also programmed the back end to the predator and prey simulation. Nathan worked heavily on the connection between front end displays and back end data through the UIManager class. 


Brandon focused on implementing the back end for the simulations' algorithms and specifically worked on and emphasized FireCellWorld and GameOfLifeCellWorld. Though the changes were never 100% functional and therefore never pushed to master, Brandon aimed to change Grid<Occupant> to Grid<Location> in order to compensate for when simulations forced each grid unit location to hold certain attributes.


Catherine focused on implementing the front end aspects of the grid display as well as the new shapes (Hexagon, Triangle). She also worked on programming the segregation cell automata model. Catherine also worked at implementing the flexibility of getting neighbors in any grid shape. 


**Resources used:**: WaterWorld code for basics in Grid components of our project, other online sources to articles detailing the rules for each simulation such as [this] (http://www.cs.duke.edu/courses/compsci308/spring15/assign/02_cellsociety/part2.php) or [this] (http://www.cs.duke.edu/courses/compsci308/spring15/assign/02_cellsociety/part3.php), and open source Java documentation regarding data structures and methods

**Files used to start the project:**: Main.java is what starts our program.

**Files used to test the project:**: fire.xml, gol.xml, predatorprey.xml, segregation.xml are all files to test the respective simulations

**Required files:**: In order to be able to simulate the maximum number of cell automatas, all Java folders in the src file are required. 

**Information on program:**: Our Cell Society project allows users to use an XML file (provided by us and able to be altered) in order to simulate a certain cell automata animation. The front end GUI is capable of interacting with the user and allows the user to change parameters such as speed of the animation or to even load in a new XML file. Each cell automata simulation has distinct rules for its occupants' behaviors. 

**Known issues:**: Process of getting neighbors for hexagonal grids is flawed, parameters on the GUI don't change when XML is loaded for the second time, and our SegregationCellWorld animation never finishes.

**Extra features:**: Our GUI featured numerous parameter inputs that allowed the user to truly customize the simulation in multiple ways. 

**Impressions:**: Very interesting project that required us to work on both front and back end design and implementation. Having mulitple sprints to divide up the project also pressured us to keep the design of our code in mind so that our project is adaptable for additional simulations in the future. Would love to have had more time for sprint 3 because we decided to make very drastic changes to our design. Not all of those changes were able to be submitted due to a myriad of bugs for certain simulations. Any more time to at least try to implement more sprint 3 features would've been exciting, especially if our design changes were to be sucessful. Overall, Cell Society pushed us to emphasize our program's scalability and efficient design by anticipating future scenarios we would need to implement. 
