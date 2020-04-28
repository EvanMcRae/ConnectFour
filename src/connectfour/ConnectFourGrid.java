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
	 */
	public void placeChecker(CheckerState state, int col) {
		//TODO add a check for empty grid positions and place at lowest empty one (rn places at height 0)
		//TODO add a check for full column
		//TODO add a check for valid column (can't use negatives or anything over width)
		//TODO maybe move this kind of stuff to player class?
		grid[0][col-1].setState(state);
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
