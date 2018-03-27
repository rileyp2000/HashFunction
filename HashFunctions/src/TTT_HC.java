import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TTT_HC extends Board {

	// private ArrayList<Object> winners;
	Object[] winners;

	public TTT_HC(String title) {
		super(title);
		// winners = new ArrayList<Object>((int)Math.pow(3, 9));
		winners = new Object[1000];
		fillWinners();
	}

	private void fillWinners() {
		String fn = "TicTacToeWinners.txt";

		Scanner file = fileToScanner(fn);

		while (file.hasNextLine()) {
			String board = file.nextLine();
			super.setBoardString(board);
			System.out.println(board + "    " + myHashCode());
			try {
				winners[myHashCode()].toString();
			} catch (NullPointerException | IndexOutOfBoundsException ex) {
				winners[myHashCode()] = new BooleanData(board);
				continue;
			}

			if (winners[myHashCode()] instanceof BooleanData) {
				ArrayList<BooleanData> arr = new ArrayList<BooleanData>();
				arr.add((BooleanData) winners[myHashCode()]);
				arr.add(new BooleanData(board));
				winners[myHashCode()] = arr;
			} else
				((ArrayList<BooleanData>) winners[myHashCode()]).add(new BooleanData(board));
		}

	}

	public static Scanner fileToScanner(String s) {
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
	int myHashCode() {
		int total = 0;
		int curChar = 0;
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				char item = super.charAt(r, c);
				switch (item) {
				case 'x':
					curChar = 2;
					break;
				case 'o':
					curChar = 1;
					break;
				default:
					curChar = 0;

				}
				total += curChar * ((r + 1) * (c + 1));
			}
		}
		return total;
	}

	@Override
	boolean isWin(String s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean isWin() {
		// TODO Auto-generated method stub
		return false;

	}

	public static void main(String[] args) {
		TTT_HC test = new TTT_HC("title");
		int total = 0;
		for (int i = 0; i < test.winners.length; i++) {
			if (test.winners[i] != null) {
				total++;
				if (test.winners[i] instanceof ArrayList<?>)
					System.out.println("ArrayList " + ((ArrayList<BooleanData>) test.winners[i]).size());
				else
					System.out.println("BooleanData");
			}
		}
		System.out.println(total);
	}
}
