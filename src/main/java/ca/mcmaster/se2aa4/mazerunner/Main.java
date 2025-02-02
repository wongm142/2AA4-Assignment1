package ca.mcmaster.se2aa4.mazerunner;
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

            logger.info("**** Computing path");
            MazeSolver solver = new RightHandAlgo();
            String solution = solver.solveMaze(maze);

            System.out.println("Solution: " + solution);
        } 
        
        catch(Exception e) {
            logger.error(e.getMessage());
            System.exit(1);
        }

        logger.info("** End of MazeRunner");
    }

    private static Configuration configure(String[] args) throws ParseException{
        Options options = new Options();
        options.addOption("i", true, "i flag");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        String maze = cmd.getOptionValue("i");

        return new Configuration(maze);
    }

    private record Configuration(String inputMaze) {}

}
