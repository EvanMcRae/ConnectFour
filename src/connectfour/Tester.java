package connectfour;

import connectfour.ConnectFourGrid.Checker;

public class Tester {

	public static void main(String[] args) {
		ConnectFourGrid grid = new ConnectFourGrid(7,6);
		System.out.println(grid);
		grid.placeChecker(Checker.PLAYER1, 3);
		System.out.println(grid);
		grid.placeChecker(Checker.PLAYER2, 3);
		System.out.println(grid);
		grid.placeChecker(Checker.PLAYER1, 3);
		System.out.println(grid);
		grid.placeChecker(Checker.PLAYER2, 3);
		System.out.println(grid);
		grid.placeChecker(Checker.PLAYER1, 3);
		System.out.println(grid);
		grid.placeChecker(Checker.PLAYER2, 3);
		System.out.println(grid);
		grid.placeChecker(Checker.PLAYER1, 3);
		System.out.println(grid);
		grid.placeChecker(Checker.PLAYER2, 0);
		grid.placeChecker(Checker.PLAYER1, 9);
		System.out.println(grid);
	}
}
