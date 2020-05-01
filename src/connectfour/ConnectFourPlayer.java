package connectfour;

import java.util.Scanner;
import connectfour.ConnectFourGrid.Checker;

/**
 * A class for a player of the Connect Four game.
 */
public class ConnectFourPlayer {
	
	private String name;
	private ConnectFourGrid grid;
	private Checker checker;
	private final Scanner scan = new Scanner(System.in);
	
	/**
	 * Constructor for players.
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
	 * Allows player to place down a checker.
	 */
	public void play() {
		System.out.println("Enter a column to place your checker in.");
		try {
			int col = scan.nextInt();
			int row = grid.placeChecker(checker, col);
			if (row == -1) {
				play();
			} else {
				System.out.println(name + " played a checker at " + col + "!");
				grid.fourInARow(row, col);
				System.out.println(grid.toString());
			}
		} catch (java.util.InputMismatchException e) {
			System.out.println("Please enter a valid number.");
			scan.nextLine();
			play();
		}
	}
	
	/**
	 * @return The player's name.
	 */
	public String getName() {
		return name;
	}
	
}