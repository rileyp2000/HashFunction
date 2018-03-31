import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class TicTacToeHashMap {
	//CLASS BOOLEAN DATA SERVES THE SAME PURPOSE AS TICTACTOEMYHASHMAP WOULD
	private HashMap<String, Boolean> map;
	private HashMap<BooleanData, Boolean> map2;
	
	/**
	 * Instantiates and fills the 2 different HashMaps
	 */
	TicTacToeHashMap() {
	
		map = new HashMap<String, Boolean>(1000, (float) .75);
		map2 = new HashMap<BooleanData, Boolean>(1000, (float) .75);
		fillWinners();
		fillNewWinners();

	}
	
	/**
	 * Fills in the first hashmap that uses a string with the winning boards
	 */
	private void fillWinners() {
		String fn = "TicTacToeWinners.txt";

		Scanner file = fileToScanner(fn);

		while (file.hasNextLine()) {
			String board = file.nextLine();
			map.put(board, true);
		}
	}
	
	/**
	 * fills in the second hashmap that uses BooleanData with the winning boards
	 */
	private void fillNewWinners() {
		String fn = "TicTacToeWinners.txt";

		Scanner file = fileToScanner(fn);

		while (file.hasNextLine()) {
			String board = file.nextLine();
			map2.put(new BooleanData(board), true);
		}
	}

	
	/**
	 * Converts the filename to a scanner
	 * @param s the filename
	 * @return A new scanner based on the filename passed in
	 */
	private static Scanner fileToScanner(String s) {
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

	// TODO This method uses reflect to investigate the objects inside the
	// HashMap
	// You should be able to update this with your information and determine
	// Information about capacity (different than size()) and what is stored in
	// the cells

	private int capacity(boolean isStringMap) throws NoSuchFieldException, IllegalAccessException {
		Field tableField = HashMap.class.getDeclaredField("table");
		tableField.setAccessible(true);
		
		Object[] table = null;
		if(isStringMap)
			table = (Object[]) tableField.get(map);
		else
			table = (Object[]) tableField.get(map2);
		return table == null ? 0 : table.length;
	}
	
	/**
	 * Tries to get a list of entries from the map, and print them to the console
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private void entries(boolean isStringMap) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field tableField = HashMap.class.getDeclaredField("table");
		tableField.setAccessible(true);
		
		Object[] table = null;
		if(isStringMap)
			table = (Object[]) tableField.get(map);
		else
			table = (Object[]) tableField.get(map2);
		
		ArrayList<Integer> bucketValues = new ArrayList<Integer>();
		
		int counter = 0;
		int nav = 0;
		for(Object t : table) {
			if( t != null) {
				System.out.println(++counter);
				Field next = t.getClass().getDeclaredField("next");
				next.setAccessible(true);
				Object o = (Object) next.get(t);
				int ct = 0;
				while(o != null) {
					ct++;
					next = o.getClass().getDeclaredField("next");
					next.setAccessible(true);
					o = (Object) next.get(t);
					System.out.println("next");
				}
				bucketValues.add(ct);
			}
			else
				nav++;
		}
		
		System.out.println("Number of used indicies: " + counter);
		System.out.println("Number of empty spaces: " + nav);
		System.out.println("Number of buckets: " + bucketValues.size() + "\nBuckets: " +  bucketValues);
	}

	// TODO using the same code to get the table of entries as in the capacity
	// method,
	// create a method that will evaluate the table as directed in the
	// assignment.
	// note - if an entry is not null, then it has a value, it may have more
	// than one value
	// see if you can determine how many values it has. Using the debugger will
	// assist.

	@SuppressWarnings("unused")
	public static void main(String[] args)
			throws java.io.FileNotFoundException, NoSuchFieldException, IllegalAccessException {

		TicTacToeHashMap m = new TicTacToeHashMap();
		Field[] f = BooleanData.class.getDeclaredFields();
		
		m.entries(true);
		System.out.println(m.capacity(true));

		// TODO read in and store the strings in your hashmap, then close the
		// file

		// TODO print out the capacity using the capcity() method
		// TODO print out the other analytical statistics as required in the
		// assignment

	}

}