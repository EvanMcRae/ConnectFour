package connectfour;

import connectfour.ConnectFourGrid.Checker;

/**
 * A class for a computer player of the Connect Four game.
 * Automatically determines the best column to place a checker in order to win.
 */
public class ComputerConnectFourPlayer extends ConnectFourPlayer {

	/**
	 * Constructor for computer players.
	 * @param grid The active instance of grid.
	 * @param checker The player's checker.
	 */
	public ComputerConnectFourPlayer(ConnectFourGrid grid, Checker checker) {
		super("Computer", grid, checker);
	}
	
	/**
	 * Allows computer player to place down a checker.
	 * TODO this is where all AI calculation stuff should go. use grid.grid to traverse grid array
	 */
	public void play() {
		
	}
	
}