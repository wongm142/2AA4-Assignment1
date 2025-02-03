package ca.mcmaster.se2aa4.mazerunner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MazeReader {

    public String mazeFile;

    public MazeReader(String mazeInput) {
        mazeFile = mazeInput;
    }

    public Maze readMaze() throws FileNotFoundException, IOException {
        Maze maze = new ArrayListMaze();

        BufferedReader read = new BufferedReader(new FileReader(mazeFile));
        String lineInput;
        ArrayList<Tile> line = new ArrayList<Tile>();

        int expectedWidth = -1;

        while ((lineInput = read.readLine()) != null) {
            line.clear();

            if (expectedWidth == -1){
                expectedWidth = lineInput.length();
            }

            if (lineInput.length() < expectedWidth){
                int paddingLength = expectedWidth - lineInput.length();
                lineInput = String.format("%-" + expectedWidth + "s", lineInput);
            }

            for (int i = 0; i < lineInput.length(); i++) {
                if (lineInput.charAt(i) == '#') {
                    line.add(Tile.WALL);
                } else if (lineInput.charAt(i) == ' ') {
                    line.add(Tile.PASS);
                }
            }


            maze.addLine(line);
        }

        read.close();

        return maze;
    }

}