package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;

public class Maze {
    private static final Logger logger = LogManager.getLogger();

    public Maze(String filePath) throws Exception {
      logger.debug("Reading maze from the file " + filePath);
      BufferedReader reader = new BufferedReader(new FileReader(filePath));

      String line;
      while ((line = reader.readLine()) != null) {
      StringBuilder outputLine = new StringBuilder();
      for (int idx = 0; idx < line.length(); idx++) {
          if (line.charAt(idx) == '#') {
              outputLine.append("WALL ");
          } else if (line.charAt(idx) == ' ') {
              outputLine.append("PASS ");
          }
      }

      logger.info(outputLine.toString());

      }
    }

}
