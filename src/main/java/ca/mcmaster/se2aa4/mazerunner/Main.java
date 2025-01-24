package ca.mcmaster.se2aa4.mazerunner;

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

        Options options = getParserOptions();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        
        try {
            cmd = parser.parse(options, args);

            String filePath = cmd.getOptionValue("i");
            Maze maze = new Maze(filePath);

        } catch (Exception e){
            logger.error("/!\\ An error has occured /!\\");
        }

        logger.info("** End of Maze Runner");
    }

    /**
     * Get options for CLI parser
     * 
     * @return CLI parser options
     */

    private static Options getParserOptions(){
        Options options = new Options();
        Option fileOption = new Option("i", true, "File containing maze");
        fileOption.setRequired(true);
    
        options.addOption(fileOption);
        
        return options;
    }

}
