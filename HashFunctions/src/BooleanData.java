
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
	
	public String toString(){
		return "The value of the winning board is " + board;
	}
}
