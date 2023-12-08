import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class tests the methods defined in ShallowFileIterator.java,
 * DeepFileIterator.java, and FilteredFileIterator.java
 */
public class P07Tester {

	/**
	 * Tests if ShallowFileIterator.java works as intended.
	 * 
	 * @return true if the iterator works as intended, false otherwise.
	 */
	public static boolean testShallowFileIterator(File file) {
		try {
			ShallowFileIterator iterator = new ShallowFileIterator(file);
			String result = "";
			while (iterator.hasNext()) {
				String item = iterator.next().getName();
				result += (item + ", ");
			}
			String expectedResults = "assignments, exam preparation, lecture notes, " + "reading notes, todo.txt, ";
			if (result.equals(expectedResults))
				return true;
		} catch (FileNotFoundException e) {
		}
		return false;
	}

	/**
	 * Tests if DeepFileIterator.java works as intended.
	 * 
	 * @return true if the iterator works as intended, false otherwise.
	 */
	public static boolean testDeepFileIterator(File file) {
		file = new File(file.getPath() + File.separator + "assignments");
		try {
			DeepFileIterator iterator = new DeepFileIterator(file);
			String result = "";
			while (iterator.hasNext()) {
				String item = iterator.next().getName();
				result += (item + ", ");
			}
			String expectedResults = "P01, PiggyBank.java, P02, CalendarPrinter.java, P03, "
					+ "ElasticBank.java, P04, ExceptionalPiggyBank.java, P05, ExtendedVersion, "
					+ "WinterCarnival.java, WinterCarnival.java, P06, AlphabetTrain.java, ";
			if (result.equals(expectedResults))
				return true;
		} catch (FileNotFoundException e) {
		}
		return false;
	}

	/**
	 * Tests if FilteredFileIterator.java works as intended.
	 * 
	 * @return true if the iterator works as intended, false otherwise.
	 */
	public static boolean testFilteredFileIterator(File file) {
		FilteredFileIterator iterator;
		String result = "";
		try {
			iterator = new FilteredFileIterator(file, ".java");
			while (iterator.hasNext()) {
				String item = iterator.next().getName();
				result += (item + ", ");
			}
		} catch (FileNotFoundException e) {
		}
		String expectedResults = "PiggyBank.java, CalendarPrinter.java, ElasticBank.java, "
				+ "ExceptionalPiggyBank.java, WinterCarnival.java, WinterCarnival.java, "
				+ "AlphabetTrain.java, codeSamples.java, ";
		if (result.equals(expectedResults))
			return true;
		return false;
	}

	/**
	 * Main method
	 * 
	 * @param args input arguments if any.
	 */
	public static void main(String[] args) {
		System.out.println(testShallowFileIterator(new File("filesystem")));
		System.out.println(testDeepFileIterator(new File("filesystem")));
		System.out.println(testFilteredFileIterator(new File("filesystem")));
	}
}
