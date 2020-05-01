package connectfour;

import connectfour.ConnectFourGrid.Checker;

public class ConnectFourGame {
	
	private ConnectFourPlayer player1, player2;
	private ConnectFourGrid grid;
	
	private static boolean darkTheme;
	
	// TODO make a no parameter constructor that prompts for player names and grid size from console
	public ConnectFourGame(int width, int height, String player1Name, String player2Name) {
		grid = new ConnectFourGrid(width, height);
		player1 = new ConnectFourPlayer(player1Name, grid, Checker.PLAYER1);
		player2 = new ConnectFourPlayer(player2Name, grid, Checker.PLAYER2);
		darkTheme = true; // TODO make a prompt for this too
	}
	
	/**
	 * Starts the game.
	 */
	public void start() {
		// main loop
		while (!grid.isFull() && grid.getWinner() == 0) {
			player1.play();
			// has to check between turns too
			if (grid.isFull() || grid.getWinner() != 0)
				break;
			player2.play();
		}
		
		// win checking
		if (grid.getWinner() == 1) {
			System.out.println(player1.getName() + " wins!");
		} else if (grid.getWinner() == 2) {
			System.out.println(player2.getName() + " wins!");
		} else {
			System.out.println("It's a draw!");
		}
	}
	
	/**
	 * Switches between dark and light theme.
	 * Necessary for symbols to be visible in either theme
	 * when ANSI coloring is used in the console.
	 */
	public static void switchTheme() {
		darkTheme = !darkTheme;
	}
	
	/**
	 * @return true if dark theme, false if light theme
	 */
	public static boolean darkTheme() {
		return darkTheme;
	}
	
}