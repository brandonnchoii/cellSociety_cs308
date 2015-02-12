package userInterface;

import grid.Grid;
import grid.Location;
import java.util.ArrayList;
import occupant.Occupant;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GridDisplay {

    public static final int P_WIDTH = 100;
    public static final int P_HEIGHT = 100;
    ArrayList<Occupant> occupants;
    ArrayList<GridUnit> units;
    Group gridPane;

    public GridDisplay (Grid<Occupant> g) {
        occupants = findOccupants(g);
        initializeGridUnits();
        render();
    }

    private ArrayList<Occupant> findOccupants(Grid<Occupant> g){
        ArrayList<Location> locs = g.getOccupiedLocations();
        ArrayList<Occupant> result = new ArrayList<Occupant>();
        for (Location l: locs){
            result.add(g.get(l));
        }
        return result;
    }
    public void initializeGridUnits () {
        units = new ArrayList<GridUnit>();
        int cellWidth = (int) (1.0 * P_WIDTH / occupants.get(0).getGrid().getNumRows());
        int cellHeight = (int) (1.0 * P_HEIGHT / occupants.get(0).getGrid().getNumCols());
        for (Occupant occ : occupants) {
            units.add(new GridUnit(cellWidth*2, cellHeight*2, occ, cellWidth * occ.getLocation().getRow(),
                                   cellHeight * occ.getLocation().getCol()));
        }
    }

    public void render () {
        gridPane = new Group();
        for (GridUnit unit : units) {
            Rectangle r = unit.rect;
            gridPane.getChildren().add(r);
            r.setLayoutX(unit.x);
            r.setLayoutY(unit.y);

        }
    }

    public Node getNode () {
        return gridPane;
    }
}

class GridUnit {
    int x;
    int y;
    Rectangle rect;

    GridUnit (int width, int height, Occupant occ, int r, int c) {
        rect = new Rectangle((double) r, (double) c, (double) width, (double) height);
        rect.setFill(occ.getColor());
        rect.setStroke(Color.BLACK);
        x = r;
        y = c;
    }
}
