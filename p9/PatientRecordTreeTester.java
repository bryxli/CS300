import java.util.NoSuchElementException;

/**
 * This class checks the correctness of the implementation of the methods
 * defined in the class PatientRecordTree.
 *
 */

public class PatientRecordTreeTester {

	/**
	 * Checks the correctness of the implementation of both addPatientRecord() and
	 * toString() methods implemented in the PatientRecordTree class. This unit test
	 * considers at least the following scenarios. (1) Create a new empty
	 * PatientRecordTree, and check that its size is 0, it is empty, and that its
	 * string representation is an empty string "". (2) try adding one patient
	 * record and then check that the addPatientRecord() method call returns true,
	 * the tree is not empty, its size is 1, and the .toString() called on the tree
	 * returns the expected output. (3) Try adding another patientRecord which is
	 * older that the one at the root, (4) Try adding a third patient Record which
	 * is younger than the one at the root, (5) Try adding at least two further
	 * patient records such that one must be added at the left subtree, and the
	 * other at the right subtree. For all the above scenarios, and more, double
	 * check each time that size() method returns the expected value, the add method
	 * call returns true, and that the .toString() method returns the expected
	 * string representation of the contents of the binary search tree in an
	 * ascendant order from the oldest patient to the youngest one. (6) Try adding a
	 * patient whose date of birth was used as a key for a patient record already
	 * stored in the tree. Make sure that the addPatientRecord() method call
	 * returned false, and that the size of the tree did not change.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testAddPatientRecordToStringSize() {
		PatientRecordTree tree = new PatientRecordTree();
		if (tree.size() != 0 || !tree.toString().equals(""))
			return false;
		if (!tree.addPatientRecord(new PatientRecord("Bryan", "12/13/2000")) || tree.isEmpty() || tree.size() != 1
				|| !tree.toString().equals("Bryan(12/13/2000)"))
			return false;
		if (!tree.addPatientRecord(new PatientRecord("Nathan", "9/16/1999")))
			return false;
		if (!tree.addPatientRecord(new PatientRecord("Colin", "1/3/2001")))
			return false;
		if (!tree.addPatientRecord(new PatientRecord("Curtis", "9/2/1997")) || tree.size() != 4
				|| !tree.toString().equals("Colin(1/3/2001)\nBryan(12/13/2000)\nNathan(9/16/1999)\nCurtis(9/2/1997)"))
			return false;
		if (!tree.addPatientRecord(new PatientRecord("Joe", "5/13/2003")) || tree.size() != 5 || !tree.toString()
				.equals("Joe(5/13/2003)\nColin(1/3/2001)\nBryan(12/13/2000)\nNathan(9/16/1999)\nCurtis(9/2/1997)"))
			return false;
		if (tree.addPatientRecord(new PatientRecord("Kevin", "12/13/2000")))
			return false;
		return true;
	}

	/**
	 * This method checks mainly for the correctness of the
	 * PatientRecordTree.lookup() method. It must consider at least the following
	 * test scenarios. (1) Create a new PatientRecordTree. Then, check that calling
	 * the lookup() method with any valid date must throw a NoSuchElementException.
	 * (2) Consider a PatientRecordTree of height 3 which consists of at least 5
	 * PatientRecordNodes. Then, try to call lookup() method to search for the
	 * patient record at the root of the tree, then a patient records at the right
	 * and left subtrees at different levels. Make sure that the lookup() method
	 * returns the expected output for every method call. (3) Consider calling
	 * .lookup() method on a non-empty PatientRecordTree with a date of birth not
	 * stored in the tree, and ensure that the method call throws a
	 * NoSuchElementException.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testAddPatientRecordAndLookup() {
		PatientRecordTree tree = new PatientRecordTree();
		try {
			tree.lookup("12/13/2000");
		} catch (NoSuchElementException e) {
		} catch (Exception e) {
			return false;
		}
		PatientRecord bryan = new PatientRecord("Bryan", "12/13/2000");
		PatientRecord nathan = new PatientRecord("Nathan", "9/16/1999");
		PatientRecord curtis = new PatientRecord("Curtis", "9/2/1997");
		PatientRecord joe = new PatientRecord("Joe", "5/13/2003");
		PatientRecord alan = new PatientRecord("Alan", "1/1/2001");
		tree.addPatientRecord(bryan);
		tree.addPatientRecord(nathan);
		tree.addPatientRecord(curtis);
		tree.addPatientRecord(joe);
		tree.addPatientRecord(alan);
		if (tree.lookup("12/13/2000") != bryan)
			return false;

		if (tree.lookup("9/2/1997") != curtis)
			return false;

		if (tree.lookup("1/1/2001") != alan)
			return false;
		try {
			tree.lookup("1/1/2000");
		} catch (NoSuchElementException e) {
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Checks for the correctness of PatientRecordTree.height() method. This test
	 * must consider several scenarios such as, (1) ensures that the height of an
	 * empty patient record tree is zero. (2) ensures that the height of a tree
	 * which consists of only one node is 1. (3) ensures that the height of a
	 * PatientRecordTree with the following structure for instance, is 4. (*) / \
	 * (*) (*) \ / \ (*) (*) (*) / (*)
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testHeight() {
		PatientRecordTree tree = new PatientRecordTree();
		if (tree.height() != 0)
			return false;
		tree.addPatientRecord(new PatientRecord("Bryan", "12/13/2000"));
		if (tree.height() != 1)
			return false;
		tree.addPatientRecord(new PatientRecord("Nathan", "9/16/1999"));
		tree.addPatientRecord(new PatientRecord("Curtis", "9/2/1997"));
		tree.addPatientRecord(new PatientRecord("Max", "9/8/2000"));
		tree.addPatientRecord(new PatientRecord("Joe2", "9/17/1999"));
		tree.addPatientRecord(new PatientRecord("Joe", "1/3/2001"));
		tree.addPatientRecord(new PatientRecord("Alan", "1/1/2001"));
		if (tree.height() != 4)
			return false;
		return true;
	}

	/**
	 * Checks for the correctness of PatientRecordTree.getRecordOfYoungestPatient()
	 * method.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testGetRecordOfYoungestPatient() {
		PatientRecordTree tree = new PatientRecordTree();
		PatientRecord joe = new PatientRecord("Joe", "1/3/2001");
		tree.addPatientRecord(new PatientRecord("Nathan", "9/16/1999"));
		tree.addPatientRecord(new PatientRecord("Curtis", "9/2/1997"));
		tree.addPatientRecord(new PatientRecord("Max", "9/8/2000"));
		tree.addPatientRecord(new PatientRecord("Joe2", "9/17/1999"));
		tree.addPatientRecord(joe);
		tree.addPatientRecord(new PatientRecord("Alan", "1/1/2001"));
		if (tree.getRecordOfYoungestPatient() != joe)
			return false;
		return true;
	}

	/**
	 * Checks for the correctness of PatientRecordTree.getRecordOfOldestPatient()
	 * method.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testGetRecordOfOldestPatient() {
		PatientRecordTree tree = new PatientRecordTree();
		PatientRecord curtis = new PatientRecord("Curtis", "9/2/1997");
		tree.addPatientRecord(new PatientRecord("Nathan", "9/16/1999"));
		tree.addPatientRecord(curtis);
		tree.addPatientRecord(new PatientRecord("Max", "9/8/2000"));
		tree.addPatientRecord(new PatientRecord("Joe2", "9/17/1999"));
		tree.addPatientRecord(new PatientRecord("Joe", "1/3/2001"));
		tree.addPatientRecord(new PatientRecord("Alan", "1/1/2001"));
		if (tree.getRecordOfOldestPatient() != curtis)
			return false;
		return true;
	}

	/**
	 * Calls the test methods
	 * 
	 * @param args input arguments if any
	 */
	public static void main(String[] args) {
		// PatientRecordTree tree=new PatientRecordTree();
		// tree.addPatientRecord(new PatientRecord("a", "10/6/1975"));
		// tree.addPatientRecord(new PatientRecord("b", "3/4/1953"));
		// tree.addPatientRecord(new PatientRecord("c", "1/10/1981"));
		// tree.addPatientRecord(new PatientRecord("d", "4/28/1945"));
		// tree.addPatientRecord(new PatientRecord("e", "7/6/1993"));
		// tree.addPatientRecord(new PatientRecord("f", "5/13/1991"));
		// tree.addPatientRecord(new PatientRecord("g", "6/12/1949"));
		// tree.addPatientRecord(new PatientRecord("h", "4/11/2010"));
		// tree.addPatientRecord(new PatientRecord("i", "1/3/1945"));
		// System.out.println(tree);

	}
}