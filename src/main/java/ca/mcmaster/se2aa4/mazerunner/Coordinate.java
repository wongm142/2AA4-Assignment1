package ca.mcmaster.se2aa4.mazerunner;

public class Coordinate {
	private int xCoords;
	private int yCoords;

  public Coordinate(){
    xCoords = 0;
    yCoords = 0;
  }

	public Coordinate(int x, int y) {
		xCoords = x();
		yCoords = y();
	}

  public Coordinate(Coordinate coordinateCopy) {
		xCoords = coordinateCopy.x();
		yCoords = coordinateCopy.y();
	}

	public String toString() {
		return String.valueOf(xCoords) + "," + String.valueOf(yCoords);
	}

	public void set(int x, int y) {
		xCoords = x;
		yCoords = y;
	}

	public void setX(int x) {
		xCoords = x;
	}

	public void setY(int y) {
		yCoords = y;
	}

	public int x() {
		return xCoords;
	}

	public int y() {
		return yCoords;
	}
}