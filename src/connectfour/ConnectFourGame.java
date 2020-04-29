package connectfour;

import connectfour.ConnectFourGrid.Checker;

public class ConnectFourGame {
	
	private ConnectFourPlayer player1, player2;
	private ConnectFourGrid grid;
	
	// TODO make a no parameter constructor that prompts for player names and grid size from console
	public ConnectFourGame(int width, int height, String player1Name, String player2Name) {
		grid = new ConnectFourGrid(width, height);
		player1 = new ConnectFourPlayer(player1Name, grid, Checker.PLAYER1);
		player2 = new ConnectFourPlayer(player2Name, grid, Checker.PLAYER2);
	}
	
	public void start() {
		// TODO obviously needs win checking
		while (!grid.isFull()) {
			player1.play();
			player2.play();
		}
		// TODO maybe return winning player or something?
	}
	
}