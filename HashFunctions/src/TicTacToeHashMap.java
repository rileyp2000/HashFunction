import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class TicTacToeHashMap {

	// TODO Define a hash map to store the winning strings as Key and true as
	// Value
	private HashMap<String, Boolean> map;

	TicTacToeHashMap() {
		// TODO Instantiate/fill your HashMap ... pay attention to initial
		// capacity and load values
		map = new HashMap<String, Boolean>(1000, (float) .75);
		fillWinners();

	}

	private void fillWinners() {
		String fn = "TicTacToeWinners.txt";

		Scanner file = fileToScanner(fn);

		while (file.hasNextLine()) {
			String board = file.nextLine();
			map.put(board, true);
		}
	}

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

	private int capacity() throws NoSuchFieldException, IllegalAccessException {
		Field tableField = HashMap.class.getDeclaredField("table");
		tableField.setAccessible(true);
		Object[] table = (Object[]) tableField.get(map);
		return table == null ? 0 : table.length;
	}

	private int entries() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field tableField = HashMap.class.getDeclaredField("entrySet");
		tableField.setAccessible(true);
		Object entry = tableField.get(map);
		return 0;
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
		Field[] f = HashMap.class.getDeclaredFields();
		System.out.println(m.entries() + " " + m.capacity());

		// TODO read in and store the strings in your hashmap, then close the
		// file

		// TODO print out the capacity using the capcity() method
		// TODO print out the other analytical statistics as required in the
		// assignment

	}

}