package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Explorer {
  private static final Logger logger = LogManager.getLogger();

  private Maze maze;
  private Coordinate coords = new Coordinate();
  private String path;
  private Direction direction;
  private Coordinate startCoords = new Coordinate();
  private Coordinate endCoords = new Coordinate();

  public Explorer(Maze mazeInput){
    maze = mazeInput;
    path = "";
    startCoords.set(0, findStart());
    logger.info("**** Entrance y coords: " + startCoords.y());
    endCoords.set(maze.width() - 1, findExit());
    logger.info("**** Exit y coords: " + endCoords.y());
  }

  private int findStart(){
    ArrayList<Point> entryColumn = maze.getColumn(0);
    for (int i = 0; i < entryColumn.size(); i++) {
      System.out.println(entryColumn.get(i));
      if(entryColumn.get(i) == Point.PASS) {
        return i;
      }
    }

    System.out.println("Unable to find maze entrance");
    return 0;
  }

  private int findExit(){
    ArrayList<Point> entryColumn = maze.getColumn(maze.width() - 1);
    for (int i = 0; i < entryColumn.size(); i++) {
      if(entryColumn.get(i) == Point.PASS) {
        return i;
      }
    }
    System.out.println("Unable to find maze exit");
    return 0;

  }

  public Coordinate coords() {
    Coordinate tempCoord = new Coordinate();
    tempCoord.set(coords.x(), coords.y());
    return tempCoord;
  }

  public Coordinate getEnd() {
    Coordinate end = new Coordinate();
    end.set(endCoords.x(), endCoords.y());
    return end;
  }

  public String getPath(){
    return path;
  }

  public void moveForward(){
    path = path + "F";

    if (direction == Direction.UP){
      coords.setY(coords.y() + 1);
    }

    else if (direction == Direction.DOWN){
      coords.setY(coords.y() - 1);
    }

    else if (direction == Direction.LEFT){
      coords.setX(coords.x() + 1);
    }

    else if (direction == Direction.RIGHT){
      coords.setX(coords.x() - 1);
    }
  }

  public void turnRight(){
    path = path + "R";

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
    path = path + "L";

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
    return false;
  }

  enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
  }
}