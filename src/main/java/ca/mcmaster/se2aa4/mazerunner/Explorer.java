package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Explorer {
  private static final Logger logger = LogManager.getLogger();

  private Maze maze;
  private Coordinate coords;
  private Direction direction;
  private Direction solvingDirection;
  private Coordinate startCoords;
  private Coordinate endCoords;
  private TraversalLogger pathLogger;
  private EntranceFinder finder;

  public Explorer(Maze mazeInput) throws EntranceException {
    maze = mazeInput;
    finder = new EntranceFinder(mazeInput);
    pathLogger = new TraversalLogger();
    
    direction = Direction.RIGHT;

    int westEntranceY = finder.findWestEntrance();
    int eastEntranceY = finder.findEastEntrance();

    if (westEntranceY < 0 || westEntranceY >= maze.height()) {
        throw new EntranceException("Invalid west entrance: " + westEntranceY);
    }

    if (eastEntranceY < 0 || eastEntranceY >= maze.height()) {
        throw new EntranceException("Invalid east entrance: " + eastEntranceY);
    }


    startCoords = new Coordinate(0, westEntranceY);
    // logger.info("**** Entrance y coords: " + startCoords.y());

    endCoords = new Coordinate(maze.width() - 1, eastEntranceY);
    // logger.info("**** Exit y coords: " + endCoords.y());

    coords = new Coordinate(startCoords);
    // logger.info("Coordinates are: " + coords.toString());

    solvingDirection = Direction.RIGHT;
  }

  public void switchSides() {
    Coordinate startNew = new Coordinate(endCoords);
    Coordinate endNew = new Coordinate(startCoords);
    startCoords = startNew;
    endCoords = endNew;
    
    if (solvingDirection == Direction.RIGHT) {
      direction = Direction.LEFT;
      solvingDirection = Direction.LEFT;
    } 
    
    else {
      direction = Direction.RIGHT;
      solvingDirection = Direction.RIGHT;
    }
  }
  
  public void reset() {
    coords = new Coordinate(startCoords);
    pathLogger.clear();
    
    if (solvingDirection == Direction.RIGHT) {
      direction = Direction.RIGHT;
    } 
    
    else {
      direction = Direction.LEFT;
    }
  }

  public Coordinate coords() {
    Coordinate tempCoord = new Coordinate(coords);
    return tempCoord;
  }

  public String canonicalPath(){
    return pathLogger.getCanonical();
  }

  public String factorizedPath(){
    return pathLogger.getFactorized();
  }

  public Direction direction(){
    return direction;
  }

  public void moveForward(){
    pathLogger.forward();

    if (direction == Direction.UP && coords.y() > 0){
      coords.setY(coords.y() - 1);
    }

    else if (direction == Direction.DOWN && coords.y() < maze.height() - 1){
      coords.setY(coords.y() + 1);
    }

    else if (direction == Direction.LEFT && coords.x() > 0){
      coords.setX(coords.x() - 1);
    }

    else if (direction == Direction.RIGHT && coords.x() < maze.width() - 1){
      coords.setX(coords.x() + 1);
    }

    else{
      logger.error("Out of bounds move attempted: " + coords.x() + ", " + coords.y());
    }
  }

  public void turnRight(){
    pathLogger.right();

    if (direction == Direction.UP){
      direction = Direction.RIGHT;
    }

    else if (direction == Direction.DOWN){
      direction = Direction.LEFT;
    }

    else if (direction == Direction.LEFT){
      direction = Direction.UP;
    }

    else if (direction == Direction.RIGHT){
      direction = Direction.DOWN;
    }
  }

  public void turnLeft(){
    pathLogger.left();

    if (direction == Direction.UP){
      direction = Direction.LEFT;
    }

    else if (direction == Direction.DOWN){
      direction = Direction.RIGHT;
    }

    else if (direction == Direction.LEFT){
      direction = Direction.DOWN;
    }

    else if (direction == Direction.RIGHT){
      direction = Direction.UP;
    }
  }

  public boolean reachedExit() {
    if (coords.x() == endCoords.x() && coords.y() == endCoords.y()){
      return true;
    }
    else{
      return false;
    }
  }

}

enum Direction {
  UP, DOWN, LEFT, RIGHT
}
