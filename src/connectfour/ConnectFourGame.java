package connectfour;

import java.util.Scanner;

import connectfour.ConnectFourGrid.Checker;

/**
 * A class for the Connect Four game.
 * Allows players to take turns and checks win conditions at the end of the game.
 */
public class ConnectFourGame {
	
	private ConnectFourPlayer player1, player2;
	private ConnectFourGrid grid;
	
	private final Scanner scan = new Scanner(System.in);
	private static boolean darkTheme = true; // by default :)
	private boolean twoPlayers = true;
	
	/**
	 * General constructor for the Connect Four game.
	 */
	public ConnectFourGame() {
		/* Dark theme option */
		System.out.println("Are you playing on dark theme? Enter Y or N:");
		String response = scan.next();
		while (!"YN".contains(response.toUpperCase())) {
			System.out.println("Please enter Y or N:");
			response = scan.next();
		}
		darkTheme = response.toUpperCase().equals("Y");
		
		System.out.print(darkTheme ? "\033[37m" : "\033[30m" + "\n"); // ANSI reset
		System.out.println("   _____                            _     ______");
		System.out.println("  / ____|                          | |   |  ____|              ");
		System.out.println(" | |     ___  _ __  _ __   ___  ___| |_  | |__ ___  _   _ _ __ ");
		System.out.println(" | |    / _ \\| '_ \\| '_ \\ / _ \\/ __| __| |  __/ _ \\| | | | '__|");
		System.out.println(" | |___| (_) | | | | | | |  __/ (__| |_  | | | (_) | |_| | |   ");
		System.out.println("  \\_____\\___/|_| |_|_| |_|\\___|\\___|\\__| |_|  \\___/ \\__,_|_|   ");
		System.out.println("\nWelcome to Connect Four! This is a two-player game where each player");
		System.out.println("tries to place four checkers in a row - horizontally, vertically, or");
		System.out.println("diagonally - and prevent the other player from doing so as well.");
		initialize();
	}
	
	/**
	 * Allows user to input grid size, player names, and other options.
	 */
	public void initialize() {		
		/* Grid creation */
		System.out.println("\nWhat size grid would you like to play on?\nEnter a number for the width (maximum: 26):");
		int width = -1;
		do {
			try {
				width = scan.nextInt();
				if (width < 1 || width > 26) {
					System.out.println("Please enter a number between 1 and 26:");
					scan.nextLine();
					width = -1;
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println("Please enter a valid integer number:");
				scan.nextLine();
				width = -1;
			}
		} while (width == -1);
		System.out.println("Enter a number for the height (maximum: 26):");
		int height = -1;
		do {
			try {
				height = scan.nextInt();
				if (height < 1 || height > 26) {
					System.out.println("Please enter a number between 1 and 26:");
					scan.nextLine();
					height = -1;
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println("Please enter a valid integer number:");
				scan.nextLine();
				height = -1;
			}
		} while (height == -1);
		grid = new ConnectFourGrid(width, height);
		System.out.println();
		
		/* Computer player option */
		System.out.println("Are you playing with two players? Enter Y to play with two players or N to play against the computer:");
		String response = scan.next();
		while (!"YN".contains(response.toUpperCase())) {
			System.out.println("Please enter Y or N:");
			response = scan.next();
		}
		twoPlayers = response.toUpperCase().equals("Y");
		
		/* Player creation (supports computer player) */
		Checker.reset();
		player1 = new ConnectFourPlayer(grid, Checker.PLAYER1);
		System.out.println(twoPlayers ? "" : "\nComputer: ");
		player2 = twoPlayers ? new ConnectFourPlayer(grid, Checker.PLAYER2) : new ComputerConnectFourPlayer(grid, Checker.PLAYER2);
		
		System.out.println("\nLet the game begin!\n");
		System.out.println(grid);
	}
	
	/**
	 * Starts the game.
	 */
	public void start() {
		/* Main loop */
		while (!grid.isFull() && grid.getWinner() == 0) {
			player1.play();
			// has to check between turns too
			if (grid.isFull() || grid.getWinner() != 0)
				break;
			player2.play();
		}
		
		/* Win checking */
		if (grid.getWinner() == 1)
			System.out.println(player1.getName() + " wins!");
		else if (grid.getWinner() == 2)
			System.out.println(player2.getName() + " wins!");
		else
			System.out.println("It's a draw!");
		
		/* Play again */
		System.out.println("\nWould you like to play again? Enter Y or N:");
		String response = scan.next();
		while (!"YN".contains(response.toUpperCase())) {
			System.out.println("Please enter Y or N:");
			response = scan.next();
		}
		if (response.toUpperCase().equals("Y")) {
			initialize();
			start();
		} else
			System.out.println("Thanks for playing!");
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