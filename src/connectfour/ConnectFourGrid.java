package connectfour;

/**
 * A class for a grid of Checkers with a set width and height.
 */
public class ConnectFourGrid {

	/**
	 * @author EvanMcRae
	 * Checker implementation.
	 */
	public enum Checker {
		EMPTY("\u2B55\u200A"), PLAYER1("\033[31m\u2B24\u200A"), PLAYER2("\033[34m\u2B24\u200A");
		
		/**
		 * The string displayed for the checker on the grid.
		 */
		private final String symbol;
		
		private Checker(String symbol) {
			this.symbol = symbol;
		}
		
		/**
		 * Outputs symbol for checker.
		 */
		public String toString() {
			return symbol;
		}
	}
	
	public Checker[][] grid;
	private int width, height;
	private int winner;
	
	/**
	 * Constructor for ConnectFourGrids.
	 * @param width The width of the grid.
	 * @param height The height of the grid.
	 */
	public ConnectFourGrid(int width, int height) {
		this.width = width;
		this.height = height;
		winner = 0;
		
		// generates grid of empty checkers
		grid = new Checker[height][width];
		for (int i = height-1; i >= 0; i--) {
			for (int j = 0; j < width; j++) {
				grid[i][j] = Checker.EMPTY;
			}
		}
	}
	
	/**
	 * Places a checker at a given column in the grid.
	 * @param checker The checker to place.
	 * @param col The column to place the checker at.
	 * @return The row the checker was placed at (-1 if failed).
	 */
	public int placeChecker(Checker checker, int col) {
		// checks for invalid column
		if (col > width || col-1 < 0) {
			System.out.println("Error: Invalid column! Please try again.");
			return -1;
		}
		
		// checks for lowest empty column
		for (int i = 0; i < height; i++) {
			if (grid[i][col-1] == Checker.EMPTY) {
				grid[i][col-1] = checker;
				return i+1;
			}
		}
		
		// if previous check fails
		System.out.println("Error: The column is full! Please try again.");
		return -1;
	}
	
	/**
	 * @return if the grid has been filled.
	 */
	public boolean isFull() {
		for (int i = height-1; i >= 0; i--) {
			for (int j = 0; j < width; j++) {
				// if any empty spots found, grid is not full
				if (grid[i][j] == Checker.EMPTY)
					return false;
			}
		}
		return true;
	}
	
	/**
	 * @author Devon
	 * Checks for four checkers in a row from any checker on the grid.
	 * Updates winner variable depending on output.
	 * @param row The row of the most recently placed checker.
	 * @param col The column of the most recently placed checker.
	 */
	public void fourInARow(int row, int col) {
		winner = 0; // stays 0 unless otherwise updated
		
		int ogRow = row, ogCol = col, inRow = 1;
		
		/* Horizontal */
		if (inRow < 4) {
			col--;
			while (col >= 0 && grid[row][col] == grid[ogRow][ogCol]) {
				inRow++;
				col--;
			}
			
			col = ogCol +1;
			while (col < grid[0].length && grid[row][col] == grid[ogRow][ogCol]) {
				inRow++;
				col++;
			}
		}
			
		/* Vertical */
		if (inRow < 4) {
			// resets values
			row = ogRow;
			col = ogCol;
			inRow = 1;
			
			row--;
			while (row >= 0 && grid[row][col] == grid[ogRow][ogCol]) {
				inRow++;
				row--;
			}
			
			row = ogRow +1;
			while (row < grid.length && grid[row][col] == grid[ogRow][ogCol]) {
				inRow++;
				row++;
			}
		}
		
		/* Left-down diagonal */
		if (inRow < 4) {
			// resets values
			row = ogRow;
			col = ogCol;
			inRow = 1;
			
			col--;
			row--;
			while (col >= 0 && row >= 0 && grid[row][col] == grid[ogRow][ogCol]) {
				inRow++;
				col--;
				row--;
			}
			
			col = ogCol +1;
			row = ogRow +1;
			while (col < grid[0].length && row < grid.length && grid[row][col] == grid[ogRow][ogCol]) {
				inRow++;
				col++;
				row++;
			}
		}
		
		/* Right-up diagonal */
		if (inRow < 4) {
			// resets values
			row = ogRow;
			col = ogCol;
			inRow = 1;
			
			col--;
			row++;
			while (col >= 0 && row < grid.length && grid[row][col] == grid[ogRow][ogCol]) {
				inRow++;
				col--;
				row++;
			}
			
			col = ogCol +1;
			row = ogRow -1;
			while (col < grid[0].length && row >= 0 && grid[row][col] == grid[ogRow][ogCol]) {
				inRow++;
				col++;
				row--;
			}
		}
		
		/* Determine winner */
		if (inRow >= 4) {
			if (grid[ogRow][ogCol] == Checker.PLAYER1) {
				winner = 1;
			} else if (grid[ogRow][ogCol] == Checker.PLAYER2) {
				winner = 2;
			}
		}
	}
	
	/**
	 * Indicates the winner for this grid.
	 * Updates when {@link #fourInARow(int, int)} is called.
	 * @return 0 if no winner, 1 if player1, 2 if player2
	 */
	public int getWinner() {
		return winner;
	}
	
	/**
	 * Outputs whole grid.
	 */
	public String toString() {
		String ret = ConnectFourGame.darkTheme() ? "\033[37m" : "\033[30m";
		
		// prints out grid
		for (int i = height - 1; i >= 0; i--) {
			for (int j = 0; j < width; j++) {
				ret += grid[i][j];
				// changes color from white to black depending on dark theme setting
				ret += ConnectFourGame.darkTheme() ? " \033[37m" : " \033[30m";
			}
			ret += "\n";
		}
		
		// prints out column numbers
		for (int i = 0; i < width; i++) {
			ret += i+1 + " ";
		}
		ret += "\n\033[39m";
		
		return ret;
	}
	
}