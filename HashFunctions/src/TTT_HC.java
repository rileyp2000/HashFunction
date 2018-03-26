import java.util.HashMap;

public class TTT_HC extends Board {

	private HashMap<String, Object> winners;

	public TTT_HC(String title) {
		super(title);
		fillWinners();
	}

	private void fillWinners() {

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

}
