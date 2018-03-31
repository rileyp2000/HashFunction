
public class BooleanData {
	private boolean isWinner;
	private String board;
	
	public BooleanData(String s){
		isWinner = true;
		board = s;
	}
	
	public String getBoard(){
		return board;
	}
	
	public boolean equals(Object o){
		if(o != null)
			return board.equals(((BooleanData)o).getBoard());
		else 
			return false;
	}
	
	public int hashCode(){
		int total = 0;
		int curChar = 0;
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				char item = charAt(board,r, c);
				switch (item) {
				case 'x':
					curChar = 11;
					break;
				case 'o':
					curChar = 7;
					break;
				default:
					curChar = 5;

				}
				total += curChar * (Math.pow((r + 1), 2) * (c + 1));
			}
		}
		return total - 430;
	}
	
	/**
	 * Gets the value of a cell of a board from a string representation of it
	 * @param s the board string
	 * @param row the row of the desired cell
	 * @param col the column of the desired cell
	 * @return the char at (r,c)
	 */
	public char charAt(String s, int row, int col) {
		int pos = row * TicTacToe.COLS + col;
		if (s.length() >= pos)
			return s.charAt(pos);
		else
			return '*';
	}
	
	public String toString(){
		return "The value of the winning board is " + board;
	}
}
