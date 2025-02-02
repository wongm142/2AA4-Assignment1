package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandAlgo implements MazeSolver{
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String solveMaze(Maze maze) {
        Explorer explorer = new Explorer(maze);
        
        explorer.moveForward();
        explorer.moveForward();
        explorer.moveForward();
        explorer.moveForward();

        return explorer.getPath();
    }
}
