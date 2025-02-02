package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Explorer {
  private static final Logger logger = LogManager.getLogger();

  private Maze maze;
  private int xCoords;
  private int yCoords;
  private String path;
  private Direction direction;
  private int startCoords;
  private int exitCoords;

  public Explorer(Maze mazeInput){
    maze = mazeInput;
    path = "";
    startCoords = findStart();
    exitCoords = findExit();
    logger.info("Entrance y coords: " + startCoords);
    logger.info("Exit y coords: " + exitCoords);
  }

  public int getXCoords(){
    return xCoords;
  }

  public int getYCoords(){
    return yCoords;
  }

  private int findStart(){
    ArrayList<Point> entryColumn = maze.getColumn(0);
    for (int i = 0; i < entryColumn.size(); i++) {
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

  public String getPath(){
    return path;
  }

  public void moveForward(){
    path = path + "F";

    if (direction == Direction.UP){
      yCoords++;
    }

    else if (direction == Direction.DOWN){
      yCoords--;
    }

    else if (direction == Direction.LEFT){
      xCoords--;
    }

    else if (direction == Direction.RIGHT){
      xCoords++;
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

  enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
  }
}