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
		EMPTY, PLAYER1, PLAYER2;
		
		/**
		 * The string displayed for the checker on the grid.
		 */
		private String symbol;
		
		/**
		 * Used to keep track of chosen colors.
		 */
		private static boolean red, green, yellow, blue, magenta, cyan;
		
		/**
		 * Constructor for checkers.
		 * @param color The color used by default.
		 */
		private Checker() {
			setColor(-1);
		}
		
		/**
		 * Sets color for this checker if the color is not already picked.
		 * @param color The color ID to use (corresponds with ANSI).
		 * @return Whether this was successful.
		 */
		public boolean setColor(int color) {
			if (color == -1) { // for empty checker
				symbol = "\u2B55\u200A";
				return true;
//			} else if (color == 0 && !black) {
//				symbol = "\033[30m\u2B24\u200A";
//				black = true;
//				System.out.println("Black chosen!");
//				return true;
			} else if (color == 1 && !red) {
				symbol = "\033[31m\u2B24\u200A";
				red = true;
				System.out.println("Red chosen!");
				return true;
			} else if (color == 2 && !green) {
				symbol = "\033[32m\u2B24\u200A";
				green = true;
				System.out.println("Green chosen!");
				return true;
			} else if (color == 3 && !yellow) {
				symbol = "\033[33m\u2B24\u200A";
				yellow = true;
				System.out.println("Yellow chosen!");
				return true;
			} else if (color == 4 && !blue) {
				symbol = "\033[34m\u2B24\u200A";
				blue = true;
				System.out.println("Blue chosen!");
				return true;
			} else if (color == 5 && !magenta) {
				symbol = "\033[35m\u2B24\u200A";
				magenta = true;
				System.out.println("Magenta chosen!");
				return true;
			} else if (color == 6 && !cyan) {
				symbol = "\033[36m\u2B24\u200A";
				cyan = true;
				System.out.println("Cyan chosen!");
				return true;
//			} else if (color == 7 && !white) {
//				symbol = "\033[37m\u2B24\u200A";
//				white = true;
//				System.out.println("White chosen!");
//				return true;
			} else if (color >= 7 || color <= 0)
				System.out.println("Invalid color! Please choose a number from 1 to 6.");
			else
				System.out.println("Color already chosen! Please choose a different one:");
			return false;
		}
		
		/**
		 * Outputs symbol for checker.
		 */
		public String toString() {
			return symbol;
		}
	}
	
	public Checker[][] grid;
	private boolean[][] highlight;
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
		highlight = new boolean[height][width];
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
		
		// checks for lowest empty row
		for (int i = 0; i < height; i++) {
			if (grid[i][col-1] == Checker.EMPTY) {
				grid[i][col-1] = checker;
				// does win check
				fourInARow(i, col-1);
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
		
		highlight = new boolean[height][width];
		highlight[row][col] = true;
		
		int ogRow = row, ogCol = col, inRow = 1;
		
		/* Horizontal */
		if (inRow < 4) {
			col--;
			while (col >= 0 && grid[row][col] == grid[ogRow][ogCol]) {
				inRow++;
				highlight[row][col] = true;
				col--;
			}
			
			col = ogCol +1;
			while (col < grid[0].length && grid[row][col] == grid[ogRow][ogCol]) {
				inRow++;
				highlight[row][col] = true;
				col++;
			}
		}
			
		/* Vertical */
		if (inRow < 4) {
			// resets values
			row = ogRow;
			col = ogCol;
			highlight = new boolean[height][width];
			highlight[row][col] = true;
			inRow = 1;
			
			row--;
			while (row >= 0 && grid[row][col] == grid[ogRow][ogCol]) {
				inRow++;
				highlight[row][col] = true;
				row--;
			}
			
			// Don't need backwards check because checkers stack up only
		}
		
		/* Left-down diagonal */
		if (inRow < 4) {
			// resets values
			row = ogRow;
			col = ogCol;
			highlight = new boolean[height][width];
			highlight[row][col] = true;
			inRow = 1;
			
			col--;
			row--;
			while (col >= 0 && row >= 0 && grid[row][col] == grid[ogRow][ogCol]) {
				inRow++;
				highlight[row][col] = true;
				col--;
				row--;
			}
			
			col = ogCol +1;
			row = ogRow +1;
			while (col < grid[0].length && row < grid.length && grid[row][col] == grid[ogRow][ogCol]) {
				inRow++;
				highlight[row][col] = true;
				col++;
				row++;
			}
		}
		
		/* Right-up diagonal */
		if (inRow < 4) {
			// resets values
			row = ogRow;
			col = ogCol;
			highlight = new boolean[height][width];
			highlight[row][col] = true;
			inRow = 1;
			
			col--;
			row++;
			while (col >= 0 && row < grid.length && grid[row][col] == grid[ogRow][ogCol]) {
				inRow++;
				highlight[row][col] = true;
				col--;
				row++;
			}
			
			col = ogCol +1;
			row = ogRow -1;
			while (col < grid[0].length && row >= 0 && grid[row][col] == grid[ogRow][ogCol]) {
				inRow++;
				highlight[row][col] = true;
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
		} else {
			highlight = new boolean[height][width];
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
				if (highlight[i][j])
					ret += ConnectFourGame.darkTheme() ? "\033[37m\u2B24\u200A" : "\033[30m\u2B24\u200A";
				else
					ret += grid[i][j];
				ret += ConnectFourGame.darkTheme() ? " \033[37m" : " \033[30m";
			}
			ret += "\n";
		}
		
		// prints out column letters
		for (int i = 0; i < width; i++) {
			ret += (char) (65+i) + " ";
		}
		ret += ConnectFourGame.darkTheme() ? "\033[37m\n" : "\033[30m\n";
		
		return ret;
	}
	
}