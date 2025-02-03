package ca.mcmaster.se2aa4.mazerunner;
import java.util.ArrayList;

public class EntranceFinder {
    private Maze maze;
    public EntranceFinder(Maze mazeInput) {
        maze = mazeInput;
    }

    public int findWestEntrance() throws EntranceException {
        ArrayList<Tile> entryColumn = maze.getColumn(0);
        for (int i = 0; i < entryColumn.size(); i++) {
            if(entryColumn.get(i) == Tile.PASS) {
                return i;
            }
        }
        throw new EntranceException("Unable to find west entrance");
    }

    public int findEastEntrance() throws EntranceException {
        ArrayList<Tile> entryColumn = maze.getColumn(maze.width() - 1);
        for (int i = 0; i < entryColumn.size(); i++) {
            if(entryColumn.get(i) == Tile.PASS) {
                return i;
            }
        }
        throw new EntranceException("Unable to find east entrance");
    }
}