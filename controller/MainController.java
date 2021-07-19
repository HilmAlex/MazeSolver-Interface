package controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.Maze;
import model.MazeSolver;
import model.Position;

public class MainController {
    private MazeSolver mazeSolver;
    private int i = 0;

    @FXML
    private Text navTitle;

    @FXML
    private BorderPane container;

    private GridPane gridContainer;

    public MainController() {

    }

    @FXML
    public void initialize() {
        this.mazeSolver = new MazeSolver(new Maze(new int[][]{
                {0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 2},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0},
                {0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        }));

        Text text = new Text("Laberinto");
        Text text1 = new Text("Solucion");

        gridContainer = new GridPane();
        gridContainer.setStyle("-fx-start-margin: 20px");
        gridContainer.setHgap(50);

        gridContainer.add(text, 0, 0);
        gridContainer.add(createGrid(this.mazeSolver.getMaze().rowsNumber(), this.mazeSolver.getMaze().columnsNumber()), 0, 1);

        this.mazeSolver.solve(new Position(0, 1));
        gridContainer.add(text1, 1, 0);
        gridContainer.add(createGrid(this.mazeSolver.getMaze().rowsNumber(), this.mazeSolver.getMaze().columnsNumber()), 1, 1);

        container.setCenter(gridContainer);
    }

    private GridPane createGrid(int rows, int columns) {
        GridPane gridContainer = new GridPane();
        double boxSize;
        double height;
        double width;

        switch (this.mazeSolver.getMaze().rowsNumber()) {
            case 10:
                boxSize = 50;
                break;
            case 15:
                boxSize = 30;
                break;
            default:
                boxSize = 25;
                break;
        }

        height = (boxSize) * this.mazeSolver.getMaze().rowsNumber();

        width = (boxSize) * columns;
        gridContainer.setMaxSize(width, height);

        if (i == 0) {
            fillGrid(gridContainer, rows, columns, boxSize, this.mazeSolver.getMaze().getArray());
        } else {
            fillGrid(gridContainer, rows, columns, boxSize, this.mazeSolver.getPath().getPileAsArray(rows, columns));
        }
        i++;

        gridContainer.setVisible(true);

        return gridContainer;
    }

    private void fillGrid(GridPane gridContainer, int rows, int columns, double boxSize, int[][] array) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Rectangle rectangle = new Rectangle();
                rectangle.setHeight(boxSize);
                rectangle.setWidth(boxSize);
                if (array[i][j] == 0) {
                    rectangle.setFill(Color.BLACK);
                } else if (array[i][j] == 1) {
                    rectangle.setFill(Color.GRAY);
                } else if (array[i][j] == 2) {
                    rectangle.setFill(Color.RED);
                } else {
                    rectangle.setFill(Color.GREEN);
                }
                gridContainer.add(rectangle, j, i);
            }
        }
    }
}