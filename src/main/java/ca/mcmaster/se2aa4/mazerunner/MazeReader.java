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
        Maze maze = new Maze();

        BufferedReader read = new BufferedReader(new FileReader(mazeFile));
        String lineInput;
        ArrayList<Point> line = new ArrayList<Point>();

        while ((lineInput = read.readLine()) != null) {
            line.clear();

            for (int i = 0; i < lineInput.length(); i++) {
                if (lineInput.charAt(i) == '#') {
                    line.add(Point.WALL);
                } else if (lineInput.charAt(i) == ' ') {
                    line.add(Point.PASS);
                }
            }
            maze.addLine(line);
        }
        
        read.close();

        for (int i = 0; i < 5; i++) {
            ArrayList<Point> mazeLine = maze.getLine(i);
            for (Point point : mazeLine) {
                System.out.print(point + " ");
            }
            System.out.println();
        }
        return maze;
    }

}