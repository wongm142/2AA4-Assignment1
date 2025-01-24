package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    return 2;
  }

  private int findExit(){
    return 2;
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

}