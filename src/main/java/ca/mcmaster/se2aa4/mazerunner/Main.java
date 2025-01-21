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

            String inputFilePath = cmd.getOptionValue("i");
            logger.info("**** Reading the maze from file: {}", inputFilePath);

            File inputFile = new File(inputFilePath);

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
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
        } catch (Exception e){
            logger.error("/!\\ An error has occured /!\\");
        }

        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
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
