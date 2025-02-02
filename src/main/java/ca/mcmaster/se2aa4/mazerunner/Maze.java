package ca.mcmaster.se2aa4.mazerunner;
import java.util.ArrayList;

public class Maze {

    private ArrayList<ArrayList<Tile>> maze = new ArrayList<ArrayList<Tile>>();
    private int mazeWidth = 0;
    private int mazeHeight = 0;

    public void addLine(ArrayList<Tile> lineInput) {
        if (mazeWidth == 0) {
            mazeWidth = lineInput.size();
        }
        else if (mazeWidth != lineInput.size()){
            throw new IllegalArgumentException("Input length of " + lineInput.size() + " illegal for maze of width " + mazeWidth);
        }

        ArrayList<Tile> line = new ArrayList<Tile>();
        for (Tile point : lineInput) {
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

    public Tile getPoint(Coordinate point) {    
        return maze.get(point.y()).get(point.x());
    }

    public ArrayList<Tile> getLine(int lineNumber) {
        return maze.get(lineNumber);
    }

    public ArrayList<Tile> getColumn(int colNum) {

        ArrayList<Tile> mazeColumn = new ArrayList<Tile>();

        for (int i = 0; i < mazeWidth; i++) {
            mazeColumn.add(maze.get(i).get(colNum));
        }

        return mazeColumn;
    }
}

enum Tile {
    PASS, WALL
}