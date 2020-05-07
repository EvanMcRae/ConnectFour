package connectfour;

import java.util.Scanner;
import connectfour.ConnectFourGrid.Checker;

/**
 * A class for a player of the Connect Four game.
 * Allows placement of checkers in a given column on a turn-by-turn basis.
 */
public class ConnectFourPlayer {
	
	private String name;
	protected ConnectFourGrid grid;
	private Checker checker;
	private final Scanner scan = new Scanner(System.in);
	
	/**
	 * Constructor for players with a name automatically provided.
	 * @param name The player's name.
	 * @param grid The active instance of grid.
	 * @param check The player's checker.
	 */
	public ConnectFourPlayer(String name, ConnectFourGrid grid, Checker checker) {
		this.name = name;
		this.grid = grid;
		this.checker = checker;
	}
	
	/**
	 * Constructor for players with a name given as text input.
	 * @param grid The active instance of grid.
	 * @param check The player's checker.
	 */
	public ConnectFourPlayer(ConnectFourGrid grid, Checker checker) {
		if (checker == Checker.PLAYER1)
			System.out.print("Player 1: ");
		else if (checker == Checker.PLAYER2)
			System.out.print("Player 2: ");
		System.out.println("What's your name?");
		String name = scan.nextLine();
		System.out.println("Hi, " + name + "! What checker color would you like to use?");
		System.out.print("\u001b[31m1 \u001b[32m2 \u001b[33m3 \u001b[34m4 \u001b[35m5 \u001b[36m6");
		System.out.println(ConnectFourGame.darkTheme() ? "\033[37m" : "\033[30m");
		int color = -1;
		boolean success = false;
		do {
			try {
				color = scan.nextInt();
				success = checker.setColor(color);
			} catch (java.util.InputMismatchException e) {
				System.out.println("Invalid color! Please enter a number from 1 to 6:");
			}
		} while (!success);
			
		this.name = name;
		this.grid = grid;
		this.checker = checker;
	}
	
	/**
	 * Allows player to place down a checker.
	 */
	public void play() {
		System.out.println(name + ": Enter a column to place your checker in.");
		char col = scan.next().toUpperCase().charAt(0);
		while (col < 65 || col > 90) {
			System.out.println("Please enter a valid letter.");
			col = scan.next().toUpperCase().charAt(0);
		}
		int row = grid.placeChecker(checker, col-64);
		if (row == -1) {
			play();
		} else {
			System.out.println("\n" + name + " placed a checker at column " + col + "!");
			System.out.println(grid.toString());
		}
	}
	
	/**
	 * @return The player's name.
	 */
	public String getName() {
		return name;
	}
	
}