package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Explorer {
  private static final Logger logger = LogManager.getLogger();

  private Maze maze;
  private Coordinate coords;
  private Direction direction;
  private Coordinate startCoords;
  private Coordinate endCoords;
  private TraversalLogger pathLogger = new TraversalLogger();

  public Explorer(Maze mazeInput) throws EntranceException, ExitException {
    maze = mazeInput;
    
    direction = Direction.RIGHT;
    startCoords = new Coordinate(0, findStart());
    logger.info("**** Entrance y coords: " + startCoords.y());

    endCoords = new Coordinate(maze.width() - 1, findExit());
    logger.info("**** Exit y coords: " + endCoords.y());

    coords = new Coordinate(startCoords);
    logger.info("Coordinates are: " + coords.toString());
  }

  private int findStart() throws EntranceException {
    ArrayList<Tile> entryColumn = maze.getColumn(0);
    for (int i = 0; i < entryColumn.size(); i++) {
      System.out.println(entryColumn.get(i));

      if (entryColumn.get(i) == Tile.PASS) {
        return i;
      }
    }

    throw new EntranceException("Unable to find maze entrance");

  }

  private int findExit() throws ExitException {
    ArrayList<Tile> entryColumn = maze.getColumn(maze.width() - 1);
    for (int i = 0; i < entryColumn.size(); i++) {
      if(entryColumn.get(i) == Tile.PASS) {
        return i;
      }
    }
    
    throw new ExitException("Unable to find maze exit");

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

    if (direction == Direction.UP){
      coords.setY(coords.y() - 1);
    }

    else if (direction == Direction.DOWN){
      coords.setY(coords.y() + 1);
    }

    else if (direction == Direction.LEFT){
      coords.setX(coords.x() - 1);
    }

    else if (direction == Direction.RIGHT){
      coords.setX(coords.x() + 1);
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
      direction = Direction.LEFT;
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
    if (coords.x() == endCoords.x() & coords.y() == endCoords.y()){
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