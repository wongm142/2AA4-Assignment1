package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandAlgo implements MazeSolver{
    private static final Logger logger = LogManager.getLogger();

    private boolean isValidCoordinate(Coordinate coord, Maze maze){
        boolean valid = coord.x() >= 0 && coord.x() < maze.width() && coord.y() >= 0 && coord.y() < maze.height();
    
        if (!valid) {
            logger.warn("Invalid coordinate: (" + coord.x() + ", " + coord.y() + ")");
        }

        return valid;

    }

    @Override
    public String solveMaze(Maze maze) {
        Explorer explorer = null;

        try{
            explorer = new Explorer(maze);

            while (!explorer.reachedExit()) {
                Coordinate rightCoord = new Coordinate(explorer.coords());
                Coordinate forwardCoord = new Coordinate(explorer.coords());
                Coordinate leftCoord = new Coordinate(explorer.coords());
                Coordinate behindCoord = new Coordinate(explorer.coords());

                if (explorer.direction() == Direction.UP) {
                    // logger.info("direction is up");
                    rightCoord.setX(rightCoord.x() + 1);
                    forwardCoord.setY(forwardCoord.y() - 1);
                    leftCoord.setX(leftCoord.x() - 1);
                    behindCoord.setY(behindCoord.y() + 1);
                } 
                
                else if (explorer.direction() == Direction.DOWN) {
                    // logger.info("direction is down");
                    rightCoord.setX(rightCoord.x() - 1);
                    forwardCoord.setY(forwardCoord.y() + 1);
                    leftCoord.setX(leftCoord.x() + 1);
                    behindCoord.setY(behindCoord.y() - 1);
                } 
                
                else if (explorer.direction() == Direction.RIGHT) {
                    // logger.info("direction is right");
                    rightCoord.setY(rightCoord.y() + 1);
                    forwardCoord.setX(forwardCoord.x() + 1);
                    leftCoord.setY(leftCoord.y() - 1);
                    behindCoord.setX(behindCoord.x() - 1);
                } 
                
                else if (explorer.direction() == Direction.LEFT) {
                    // logger.info("direction is left");
                    rightCoord.setY(rightCoord.y() - 1);
                    forwardCoord.setX(forwardCoord.x() - 1);
                    leftCoord.setY(leftCoord.y() + 1);
                    behindCoord.setX(behindCoord.x() + 1);
                }


                if (isValidCoordinate(rightCoord, maze) && maze.getPoint(rightCoord) == Tile.PASS) {
                    // logger.info("Turning Right");
                    explorer.turnRight();
                    explorer.moveForward();
                } 
                
                else if (isValidCoordinate(forwardCoord, maze) && maze.getPoint(forwardCoord) == Tile.PASS) {
                    // logger.info("Moving Forwards");
                    explorer.moveForward();
                } 
                
                else if (isValidCoordinate(leftCoord, maze) && maze.getPoint(leftCoord) == Tile.PASS) {
                    // logger.info("Turning Left");
                    explorer.turnLeft();
                    explorer.moveForward();
                } 
                
                else if (isValidCoordinate(behindCoord, maze) && maze.getPoint(behindCoord) == Tile.PASS) {
                    explorer.turnRight();
                    explorer.turnRight();
                    explorer.moveForward();
                } 
                
                else {
                    logger.error("Runner Trapped");
                    logger.error("Right Tile: " + rightCoord.x() + "," + rightCoord.y() + " - " + maze.getPoint(rightCoord));
                    logger.error("Forward Tile: " + forwardCoord.x() + "," + forwardCoord.y() + " - " + maze.getPoint(forwardCoord));
                    logger.error("Left Tile: " + leftCoord.x() + "," + leftCoord.y() + " - " + maze.getPoint(leftCoord));
                    logger.error("Rear Tile: " + behindCoord.x() + "," + behindCoord.y() + " - " + maze.getPoint(behindCoord));
                    return ("Unable to solve - Runner Trapped");
                }

            }

            return explorer.factorizedPath();

        } catch (Exception e) {
            logger.error(e.getMessage());
            return e.getMessage();
        }

    }
}
