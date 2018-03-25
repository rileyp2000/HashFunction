import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//TODO Make sure you remove all of the TODO comments from this file before turning itin

public class TicTacToeHashCode extends Board {

	boolean[] winners; // True if the hash string that maps to this index is a
						// winner, false otherwise

	TicTacToeHashCode(String s) {
		super(s);
		winners = new boolean[(int)Math.pow(3, 9)];
		fillWinners();
		}

	public void fillWinners() {

		String fn = "TicTacToeWinners.txt";

		Scanner file = fileToScanner(fn);

		while (file.hasNextLine()) {
			String board = file.nextLine();
			super.setBoardString(board);
			winners[myHashCode()] = true;
		}

	}

	public Scanner fileToScanner(String s) {
		File f = new File(s);
		Scanner ret = null;

		try {
			ret = new Scanner(f);
		} catch (FileNotFoundException e) {
			System.out.println("No File!!");
			System.exit(1);
		}

		return ret;
	}

	@Override
	public int myHashCode() {
		int[][] pow3 = new int[][] { { 1, 3, 9 }, { 27, 81, 243 }, { 729, 2187, 6561 } };
		int total = 0;
		int curChar = 0;

		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				char item = super.charAt(r, c);
				switch (item) {
				case 'x':
					curChar = 1;
					break;
				case 'o':
					curChar = 2;
					break;
				default:
					curChar = 0;

				}
				total += curChar * pow3[r][c];
			}
		}

		return total;

	}

	@Override
	public boolean isWin(String s) {
		super.setBoardString(s);
		return winners[myHashCode()];

	}

	public boolean isWin() {
		return winners[myHashCode()];
	}

	public static void main(String[] args) throws InterruptedException {
		TicTacToeHashCode board = new TicTacToeHashCode("Tic Tac Toe");
		while (true) {
			board.displayRandomString();
			System.out.println("This should be a winner: " + board.isWin());
			Thread.sleep(4000);
		}
	}

}
