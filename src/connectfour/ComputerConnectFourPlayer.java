package connectfour;

import connectfour.ConnectFourGrid.Checker;

/**
 * A class for a computer player of the Connect Four game.
 * Automatically determines the best column to place a checker in order to win.
 */
public class ComputerConnectFourPlayer extends ConnectFourPlayer {
	//The idea behind those variables is to store the last place a checker was checked.
	
	private int lastRow = -1;
	private int lastCol = -1;
	/**
	 * Constructor for computer players.
	 * @param grid The active instance of grid.
	 * @param checker The player's checker.
	 */
	public ComputerConnectFourPlayer(ConnectFourGrid grid, Checker checker) {
		super("Computer", grid, checker);
	}
	
	/**
	 * Allows computer player to place down a checker depending on the current grid.
	 * TODO this is where all AI calculation stuff should go. use grid.grid to traverse grid array
	 */
	public void play() {
		// TODO replace with proper strategy. for now random placement is used
		int row;
		int col;
		if(lastCol == -1) {
			 col = (int) (Math.random()*grid.getWidth()) + 1;
			 row = grid.placeChecker(checker, col);
		}
		else {
			col = grid.checkPosition(lastRow, lastCol);
			if(col == -1) {
				col = (int) (Math.random()*grid.getWidth()) + 1;
			}
			row = grid.placeChecker(checker, col);
		}
		
		if (row == -1) {
			lastCol = -1;
			lastRow = -1;
			play();
			
		} else {
			System.out.println(name + " placed a checker at column " + (char) (col+64) + "!");
			System.out.println(grid.toString());
			lastCol = col;
			lastRow = row;
		}
	}
	
	
	
}