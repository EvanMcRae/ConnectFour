package connectfour;

import connectfour.Checker.CheckerState;

/**
 * A class for a grid of Checkers with a set width and height.
 */
public class ConnectFourGrid {

	public Checker[][] grid;
	private int width, height;
	
	/**
	 * Constructor for ConnectFourGrids.
	 * @param width The width of the grid.
	 * @param height The height of the grid.
	 */
	public ConnectFourGrid(int width, int height) {
		this.width = width;
		this.height = height;
		grid = new Checker[height][width];
		
		// generates grid of empty checkers
		// TODO make sure this actually generates coordinates in the way we want it to
		for (int i = height-1; i >= 0; i--) {
			for (int j = 0; j < width; j++) {
				grid[i][j] = new Checker(CheckerState.EMPTY);
			}
		}
	}
	
	/**
	 * Places a checker at a given column in the grid.
	 * @param state The state of the checker (player1, player2)
	 * @param col The column to place the checker at.
	 * TODO maybe move this kind of stuff to player class?
	 */
	public void placeChecker(CheckerState state, int col) {
		// checks for invalid column
		if (col-1 > width || col-1 < 0) {
			System.out.println("Invalid column!");
			return;
		}
		
		// checks for lowest empty column
		for (int i = 0; i < height; i++) {
			if (grid[i][col-1].getState() == CheckerState.EMPTY) {
				grid[i][col-1].setState(state);
				return;
			}
		}
		
		// if previous check fails
		System.out.println("The column is full!");
	}
	
	public String toString() {
		String ret = "";
		
		// prints out grid
		for (int i = height - 1; i >= 0; i--) {
			for (int j = 0; j < width; j++) {
				if (grid[i][j].getState() == CheckerState.EMPTY)
					ret += "* ";
				else if (grid[i][j].getState() == CheckerState.PLAYER1)
					ret += "1 ";
				else if (grid[i][j].getState() == CheckerState.PLAYER2)
					ret += "2 ";
			}
			ret += "\n";
		}
		
		// prints out column numbers
		for (int i = 0; i < width; i++) {
			ret += i+1 + " ";
		}
		ret += "\n";
		
		return ret;
	}
}
