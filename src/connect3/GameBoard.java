package connect3;

import java.util.Scanner;

public class GameBoard {
	
	Scanner scan = new Scanner(System.in);
	
	private final int ARRAY_HEIGHT = 4; // number of rows
	private final int ARRAY_WIDTH = 6;	// number of columns
	private int[][] gameArray; // array to store the progress of our game
	private int[]moveArray = {0, 0}; // array to store a player's move, moveArray[0] - row, moveArray[1] - column
	
	private boolean stillPlaying = true; // state of the game
	
	public GameBoard() {
		gameArray = new int[ARRAY_HEIGHT][ARRAY_WIDTH];
		for (int i = 0; i < ARRAY_HEIGHT; i++) {
			for (int j = 0; j < ARRAY_WIDTH; j++) {
				gameArray[i][j] = 0;
			}
		}
	} // end constructor
	
	/**
	 * Prompts a player to make a move. Validates it. If the spot on the game board is available, makes changes to the game board, according to player's move.
	 * @param player
	 */
	public void makeMove(int player) {
		
		boolean valid1 = false; // Verification flag - input format.
		boolean valid2 = false; // Verification flag - spot availability at the game board.
		int height = ARRAY_HEIGHT - 1; // height of the gameArray, for loop iteration purposes.
				
		System.out.printf("Player %d, make your move...%n", player);
		
		while(!(valid1 & valid2)) {
			System.out.print("Enter the number of column(1-6): ");
			moveArray[1] = scan.nextInt() - 1;
			
			// Validating input format
			if (moveArray[1] >= 0 & moveArray[1] <= 5) {
				valid1 = true;
				
				// Validating spot availability
				if (gameArray[0][moveArray[1]] == 0) {
					
					do {
						if (gameArray[height][moveArray[1]] == 0) {
							gameArray[height][moveArray[1]] = player;
							moveArray[0] = height; // saving last move's height coordinate for checkWinner() method
							valid2 = true; //loop exit
						} else 
							height--;
					} while (!valid2);
					
				} else 
					System.out.println("This move is unavailable! Try again.");
			}
			else
				System.out.printf("Wrong input. Required format: columns(1 ... %d).%n", ARRAY_WIDTH);
		}
	}
	
	/**
	 * Current state of the game, true/false.
	 * @return stillPlaying
	 */
	public boolean stillPlaying() {
		return stillPlaying;
	}
	
	/**
	 * Creates a String object, records current state of the game
	 * @return String representation of the game board
	 */
	public String boardToString() {
		String s = "";
		for (int i = 0; i < ARRAY_HEIGHT; i ++ ) {
			for (int j = 0; j < ARRAY_WIDTH; j ++) {
				s = s + gameArray[i][j];
				if(j != 5)
					s = s + "\t";
				else
					s = s + "\n";
			}
		}
		return s;
	}
	
	/**
	 * 
	 * Checks for a winner
	 * 8 possible directions:
	 * 
	 * 	8   1   2
	 * 	  * * *
	 * 	7 * M * 3
	 * 	  * * *
	 * 	6   5	4 
	 * 
	 * M represents coordinates (x - height, y - width) of the CURRENT LAST move made in the game. 
	 * 
	 * I was trying to squeeze this method as much as I could.
	 */
	public void checkWinner() {

		int x = moveArray[0]; // last move's row, for readability purposes
		int y = moveArray[1]; // last move's column, for readability purposes
		int[] coordinatesArray = {1, 1, -1, -1, -1, 1, 1, -1, 1, 0, -1, 0, 0, 1, 0, -1}; //set of 8 (x, y) coordinate increments/decrements
		
		for (int i = 0; i <= 14; i += 2) {
			try {
				if (gameArray[x][y] == gameArray[x + coordinatesArray[i]][y + coordinatesArray[i + 1]] & gameArray[x][y] == gameArray[x + 2 * coordinatesArray[i]][y + 2 * coordinatesArray[i + 1]]) {
					stillPlaying = false;
					System.out.printf("Player %d won!", gameArray[x][y]);
				}
			} catch (ArrayIndexOutOfBoundsException ex) {;}
		}
	}
}//end of GameBoard class