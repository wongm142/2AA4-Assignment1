package ca.mcmaster.se2aa4.mazerunner;
public class PathValidator {
	private Maze maze;
	private Explorer explorer;
	
	public PathValidator(Maze mazeInput) {
		maze = mazeInput;
	}

	public boolean checkPath(String path) throws EntranceException, ExitException {
		explorer = new Explorer(maze);

		boolean correctEast = runPath(path, explorer);
    
		explorer.switchSides();
		explorer.reset();

		boolean correctWest = runPath(path, explorer);
    
		if (correctEast || correctWest) {
			return true;
		} 
    
    else {
			return false;
		}
	}

	private boolean runPath(String path, Explorer explorer) {
    int i = 0;

    while (i < path.length()){
      char currentChar = path.charAt(i);

      if (Character.isDigit(currentChar)) {
        int repeatCount = Character.getNumericValue(currentChar);
        i++;

        if (i >= path.length()){
          return false;
        }

        char moveCmd = path.charAt(i);

        for (int j = 0; j < repeatCount; j++){
          if (!executeMove(moveCmd, explorer)) {
            return false;
          }
        }
      }
      
      else{
          if (!executeMove(currentChar, explorer)){
            return false;
          }
      }

      i++;

    }

    return explorer.reachedExit();
		
	}

  private boolean executeMove(char move, Explorer explorer) {
    switch (move) {
        case 'F':
            if (maze.getPoint(explorer.coords()) == Tile.WALL) {
                return false;
            }
            
            explorer.moveForward();
            break;

        case 'R':
            explorer.turnRight();
            break;

        case 'L':
            explorer.turnLeft();
            break;

        default:
            return false; 
    }
    return true;
  }
}