import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TTT_HC extends Board {

	//private ArrayList<Object> winners;
	private Object[] winners;
	
	public TTT_HC(String title) {
		super(title);
		//winners = new ArrayList<Object>((int)Math.pow(3, 9));
		winners = new Object[(int)Math.pow(3, 9)];
		fillWinners();
	}

	private void fillWinners() {
		String fn = "TicTacToeWinners.txt";

		Scanner file = fileToScanner(fn);

		while (file.hasNextLine()) {
			String board = file.nextLine();
			super.setBoardString(board);

			try {
				//winners.get(myHashCode());
				winners[myHashCode()].toString();
			} catch (NullPointerException | IndexOutOfBoundsException ex) {
				//winners.add(myHashCode(), new BooleanData(board));
				winners[myHashCode()] = new BooleanData(board);
				break;
			}

			/*
			 * if(winners.get(myHashCode()) == null) winners.set(myHashCode(),
			 * new BooleanData(board)); else{
			 */
			
			
			//if (winners.get(myHashCode()) instanceof BooleanData) {
			if(winners[myHashCode()] instanceof BooleanData){
				ArrayList<BooleanData> arr = new ArrayList<BooleanData>();
				arr.add((BooleanData) winners[myHashCode()]);//winners.get(myHashCode()));
				arr.add(new BooleanData(board));
				//winners.set(myHashCode(), arr);
				winners[myHashCode()] = arr;
			} else
				((ArrayList<BooleanData>) winners[myHashCode()]/*.get(myHashCode())*/).add(new BooleanData(board));
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
		/*try {
			winners.get(myHashCode());
		} catch (NullPointerException e) {
			return false;
		}
		if (winners.get(myHashCode()) instanceof BooleanData) {
			return this.getBoardString().equals(((BooleanData) winners.get(myHashCode())).getBoard());
		} else {
			ArrayList<BooleanData> a = ((ArrayList<BooleanData>) winners.get(myHashCode()));
			for (BooleanData b : a) {
				if (b.getBoard().equals(getBoardString()))
					return true;
			}
			return false;
		}*/
	}

	public static void main(String[] args) {
		TTT_HC test = new TTT_HC("title");
	}
}
