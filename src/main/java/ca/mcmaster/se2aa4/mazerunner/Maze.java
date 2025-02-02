package ca.mcmaster.se2aa4.mazerunner;
import java.util.ArrayList;

public class Maze {

    private ArrayList<ArrayList<Point>> maze = new ArrayList<ArrayList<Point>>();
    private int mazeWidth = 0;
    private int mazeHeight = 0;

    public void addLine(ArrayList<Point> lineInput) {
        if (mazeWidth == 0) {
            mazeWidth = lineInput.size();
        }
        else if (mazeWidth != lineInput.size()){
            throw new IllegalArgumentException("Input length of " + lineInput.size() + " illegal for maze of width " + mazeWidth);
        }

        ArrayList<Point> line = new ArrayList<Point>();
        for (Point point : lineInput) {
            line.add(point);
        }

        maze.add(line);
        mazeHeight++;
    }

    public int height() {
        return mazeHeight;
    }

    public int width() {
        return mazeWidth;
    }

    public Point getPoint(int xCoords, int yCoords) {
        return maze.get(yCoords).get(xCoords);
    }

    public ArrayList<Point> getLine(int lineNumber) {
        return maze.get(lineNumber);
    }

    public ArrayList<Point> getColumn(int colNum) {

        ArrayList<Point> mazeColumn = new ArrayList<Point>();

        for (int i = 0; i < mazeWidth; i++) {
            mazeColumn.add(maze.get(i).get(colNum));
        }

        return mazeColumn;
    }
}

enum Point {
    PASS, WALL
}