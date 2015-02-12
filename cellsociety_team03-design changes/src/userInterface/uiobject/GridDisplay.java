package userInterface.uiobject;

import grid.Grid;
import grid.location.Location;

import java.util.ArrayList;

import visualUnits.*;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class GridDisplay {

    public static final int P_WIDTH = 200;
    public static final int P_HEIGHT = 200;

    private Grid<Location> myGrid;
    private ArrayList<Units> units;
    private Group gridView;

    public GridDisplay (Color[][] c, String s) {
        initializeUnits(c, s);
    }

    public void initializeUnits (Color[][] list, String s) {
        int rows = list.length;
        int cols = list[0].length;
        gridView = new Group();
        double cellHeight = P_HEIGHT / rows;
        double cellWidth = P_WIDTH / cols;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                switch (s) { // Adds the specific shape-Location based on type specified by XML
                case "Square":
                    gridView.getChildren().add((new SquareUnit(r, c, cellHeight, cellWidth,
                                                               list[r][c]).getShape()));
                    break;
                case "Triangle":
                    gridView.getChildren().add((new TriangleUnit(r, c, cellHeight, cellWidth,
                                                                 list[r][c]).getShape()));
                    break;
                case "Hexagon":
                    gridView.getChildren().add((new HexagonUnit(r, c, cellHeight, cellWidth,
                                                                list[r][c]).getShape()));
                    break;
                }
            }

        }
    }

    public Node getNode () {
        return gridView;
    }
}
