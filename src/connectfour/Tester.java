package connectfour;

import connectfour.Checker.CheckerState;

public class Tester {

	public static void main(String[] args) {
		ConnectFourGrid grid = new ConnectFourGrid(7,6);
		System.out.println(grid);
		grid.placeChecker(CheckerState.PLAYER1, 3);
		System.out.println(grid);
	}
}
