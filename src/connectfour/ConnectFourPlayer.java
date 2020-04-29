package connectfour;
import java.util.Scanner;

import connectfour.ConnectFourGrid.Checker;

public class ConnectFourPlayer {
	private String name;
	private ConnectFourGrid playGrid;
	private Checker checker;
	Scanner scan = new Scanner(System.in);
	
	public ConnectFourPlayer(String name, ConnectFourGrid grid, Checker check ) {
		this.name = name;
		playGrid = grid;
		checker = check;
	}
	
	public void play() {
		System.out.println("Enter a column to place your checker in.");
		int col = scan.nextInt();
		String before = playGrid.toString();
		playGrid.placeChecker(checker, col);
		String after = playGrid.toString();
		if(before.equals(after)) {
			System.out.println("Error in placing the checker. Please enter a valid column.");
			play();
		}
		else {
			System.out.println(name + " played a checker at " + col + "!");
			System.out.println(playGrid.toString());
		}
		
	}
}
