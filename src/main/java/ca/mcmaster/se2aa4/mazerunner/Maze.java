package ca.mcmaster.se2aa4.mazerunner;
import java.util.ArrayList;

public interface Maze {
    public void addLine(ArrayList<Tile> lineInput);
    public int height();
    public int width();
    public Tile getPoint(Coordinate point);
    public ArrayList<Tile> getLine(int lineNumber);
    public ArrayList<Tile> getColumn(int columnNum);
}