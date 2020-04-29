package connectfour;
import java.util.Scanner;

import connectfour.ConnectFourGrid.Checker;

/**
 * A class for a player of the Connect Four game.
 */
public class ConnectFourPlayer {
	
	private String name;
	private ConnectFourGrid playGrid;
	private Checker checker;
	Scanner scan = new Scanner(System.in);
	
	/**
	 * Constructor for players.
	 * @param name The player's name.
	 * @param grid The active instance of grid.
	 * @param check The player's checker.
	 */
	public ConnectFourPlayer(String name, ConnectFourGrid grid, Checker check ) {
		this.name = name;
		playGrid = grid;
		checker = check;
	}
	
	/**
	 * Allows player to place down a checker.
	 */
	public void play() {
		System.out.println("Enter a column to place your checker in.");
		int col = scan.nextInt();
		int row = playGrid.placeChecker(checker, col);
		if (row == -1) {
			play();
		} else {
			System.out.println(name + " played a checker at " + col + "!");
			System.out.println(playGrid.toString());
		}
		
	}
}
