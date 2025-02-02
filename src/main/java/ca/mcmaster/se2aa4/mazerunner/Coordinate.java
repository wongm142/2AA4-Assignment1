package ca.mcmaster.se2aa4.mazerunner;

public class Coordinate {
	private int xCoords;
	private int yCoords;

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