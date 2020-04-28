package connectfour;

/**
 * @author EvanMcRae
 * A class for Checkers with a given state.
 */
public class Checker {
	
	/**
	 * @author EvanMcRae
	 * Possible states for a checker.
	 */
	public enum CheckerState {
		EMPTY, PLAYER1, PLAYER2;
	}
	
	private CheckerState state; 
	
	/**
	 * Constructor for Checkers.
	 * @param state The state of the checker (empty, player1, player2).
	 */
	public Checker(CheckerState state) {
		this.state = state;
	}
	
	/**
	 * @return The state of the checker (empty, player1, player2).
	 */
	public CheckerState getState() {
		return state;
	}
	
	/**
	 * Sets the state of the checker.
	 * @param state The new state (empty, player1, player2).
	 */
	public void setState(CheckerState state) {
		this.state = state;
	}
	
	public String toString() {
		switch (state) {
		case PLAYER1:
			return "Player 1 checker";
		case PLAYER2:
			return "Player 2 checker";
		default:
			return "Empty checker";
		}
	}
	
}
