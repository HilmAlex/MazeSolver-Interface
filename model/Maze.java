package model;

public class Maze {
    int[][] maze;

    public Maze(int [][]maze){
        this.maze = maze;
    }

    public int getValue(int row, int column) {
        return maze[row][column];
    }

    public void setValue(int row, int column, int value){
        maze[row][column] = value;
    }

    public int rowsNumber(){
        return maze.length;
    }

    public int columnsNumber(){
        return maze[0].length;
    }

    public int[][] getArray() {
        return maze;
    }
}
