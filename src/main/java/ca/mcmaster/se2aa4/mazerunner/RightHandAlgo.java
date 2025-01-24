package ca.mcmaster.se2aa4.mazerunner;

public class RightHandAlgo implements MazeSolver{
    
    @Override
    public String solveMaze(Maze maze) {
        Explorer explorer = new Explorer(maze);
        
        explorer.moveForward();
        explorer.moveForward();
        explorer.moveForward();
        explorer.moveForward();

        return explorer.getPath();
    }
}
