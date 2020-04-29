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
		
		public String toString() {
			return symbol;
		}
	}
	
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
	 * TODO maybe move this kind of stuff to player class?
	 */
	public void placeChecker(Checker checker, int col) {
		// checks for invalid column
		if (col-1 > width || col-1 < 0) {
			System.out.println("Invalid column!");
			return;
		}
		
		// checks for lowest empty column
		for (int i = 0; i < height; i++) {
			if (grid[i][col-1] == Checker.EMPTY) {
				grid[i][col-1] = checker;
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
				ret += grid[i][j] + " \033[37m";
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