package ca.mcmaster.se2aa4.mazerunner;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.ObjectInputFilter.Config;
import java.lang.module.Configuration;
import org.apache.commons.cli.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        try {
            Configuration config = configure(args);

            logger.info("**** Reading the maze from file " + config.inputMaze());
            
            MazeReader reader = new MazeReader(config.inputMaze());
            Maze maze = reader.readMaze();

            if (config.hasPath()) {
                logger.info("**** Checking path");
                PathValidator checker = new PathValidator(maze);
                boolean checkResult = checker.checkPath(config.inputPath());
                
                if (checkResult) {
                    System.out.println("Your input solution is correct!");
                } 
                
                else {
                    System.out.println("Your input solution is incorrect.");
                }
            } 
            
            else {
                logger.info("**** Computing path");
                MazeSolver solver = new RightHandAlgo();
                String solution = solver.solveMaze(maze);
                System.out.println("Solution: " + solution);
            }

        } catch(IOException | ParseException | EntranceException | ExitException e) {
            logger.error(e.getMessage());
            System.exit(1);
        }

        logger.info("** End of MazeRunner");
    }

    private static Configuration configure(String[] args) throws ParseException{
        Options options = new Options();
        options.addOption("i", true, "flag for maze file location");
        options.addOption("p", true, "flag for path checking");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        String maze = cmd.getOptionValue("i");
        boolean hasPath = cmd.hasOption("p");
        String path = cmd.getOptionValue("p");

        return new Configuration(maze, path, hasPath);
    }

    private record Configuration(String inputMaze, String inputPath, boolean hasPath) {}

}
