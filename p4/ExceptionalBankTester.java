import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

/**
 * This class implements unit test methods for ExceptionalBank application
 * 
 */
public class ExceptionalBankTester {

	/**
	 * This method checks whether the ExceptionalBank constructor throws an
	 * IllegalArgumentException with appropriate error message, when it is passed a
	 * zero or a negative capacity. This test must fail if another kind of exception
	 * is thrown for such test scenario.
	 *
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testExceptionalBankConstructor() {
		try {
			// create an exceptional bank with a negative capacity
			ExceptionalBank bank = new ExceptionalBank(-10);
			System.out.println("Problem detected. The constructor call of the ExceptionalBank class did not "
					+ "throw an IllegalArgumentException when it is passed a negative capacity.");
			return false; // return false if no exception has been thrown
		} catch (IllegalArgumentException e1) {
			// check that the caught IllegalArgumentException includes
			// an appropriate error message
			if (e1.getMessage() == null // your test method should not throw
					// a NullPointerException,but must return false if e1.getMessage is null
					|| !e1.getMessage().toLowerCase().contains("must be a non-zero positive integer")) {
				System.out.println("Problem detected. The IllegalArgumentException thrown by the constructor "
						+ "call of the ExceptionalBank class when it is passed a negative capacity "
						+ "does not contain an appropriate error message.");
				return false;
			}
		} catch (Exception e2) {
			// an exception other than IllegalArgumentException has been thrown
			System.out.println("Problem detected. An unexpected exception has been thrown when calling the "
					+ "constructor of the ExceptionBank class with a negative argument. "
					+ "An IllegalArgumentException was expected to be thrown. " + "But, it was NOT the case.");
			e2.printStackTrace(); // to help locate the error within the bad ExceptionalBank
			// constructor code.
			return false;
		}
		return true; // test passed
	}

	/**
	 * This method checks whether the ExceptionalBank constructor creates without
	 * errors an empty exceptional bank with capacity 20 when it is passed 20 as
	 * input parameter. This test must fail if any exception is thrown for such test
	 * scenario.
	 *
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testGoodExceptionalBankConstructor() {
		try {
			ExceptionalBank bank = new ExceptionalBank(1);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * This method checks whether the ExceptionalBank.addCoin() method throws an
	 * IllegalArgumentException with an appropriate error message, when it is passed
	 * a null reference.
	 *
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testAddCoin() {
		try {
			ExceptionalBank bank = new ExceptionalBank(10);
			bank.addCoin(null);
			System.out.println("Problem detected. The addCoin method of the ExceptionalBank class did not "
					+ "throw an IllegalArgumentException when it is passed a null.");
			return false;
		} catch (IllegalArgumentException e1) {
			return true;
		} catch (Exception e2) {
			System.out.println("Problem detected. An unexpected exception has been thrown when calling the "
					+ "addCoin method of the ExceptionBank class with a null. "
					+ "An IllegalArgumentException was expected to be thrown. " + "But, it was NOT the case.");
			e2.printStackTrace();
			return false;
		}
	}

	/**
	 * This method checks whether the ExceptionalBank.removeCoin() method throws a
	 * NoSuchElementException with an appropriate error message, when it is called
	 * on an empty bank.
	 *
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testRemoveCoinEmptyBank() {
		try {
			ExceptionalBank bank = new ExceptionalBank(10);
			bank.removeCoin();
			System.out.println("Problem detected. The removeCoin method of the ExceptionalBank class did not "
					+ "throw a NoSuchElementException when bank is empty");
			return false;
		} catch (NoSuchElementException e1) {
			return true;
		} catch (Exception e2) {
			System.out.println("Problem detected. An unexpected exception has been thrown when calling the "
					+ "removeCoin method of the ExceptionBank class. "
					+ "A NoSuchElementException was expected to be thrown. " + "But, it was NOT the case.");
			e2.printStackTrace();
			return false;
		}
	}

	/**
	 * This method checks whether the ExceptionalBank.addCoins() works appropriately
	 * when it is passed a String with a valid format. You can consider the
	 * following test scenarios, for instance. First, Create a new ExceptionalBank
	 * with initial capacity 20. Then, call .addCoins("QUARTER:2"). Then, verify
	 * that 2 quarters have been added to the bank without errors. Then, call
	 * .addCoins(" Penny : 3 "); Then, verify that 3 pennies have been added to the
	 * bank without errors. You can consider further scenarios. No exceptions must
	 * be thrown by the call of .addCoins() method with valid input arguments.
	 *
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testAddCoinsValidFormat() {
		ExceptionalBank bank = new ExceptionalBank(20);
		try {
			bank.addCoins("QUARTER:2");
			if (!(bank.getBalance() == 50))
				return false;
			bank.addCoins(" Penny : 3 ");
			if (!(bank.getBalance() == 53))
				return false;
			return true;
		} catch (DataFormatException e1) {
			return false;
		} catch (Exception e2) {
			System.out.println("Problem detected. An unexpected exception has been thrown when calling the "
					+ "addCoins method of the ExceptionBank class. ");
			e2.printStackTrace();
			return false;
		}
	}

	/**
	 * This method checks whether ExceptionalBank.addCoins() method throws a
	 * NoSuchElementException with an appropriate error message when it is passed a
	 * String object with a correct format (meaning "string:positive_number"), but
	 * with a coin name not defined in the enum Coin's constants. For instance, when
	 * it is passed "blabla:10".
	 *
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testAddCoinsNoSuchElement() {
		ExceptionalBank bank = new ExceptionalBank(20);
		try {
			bank.addCoins("blabla:10");
		} catch (NoSuchElementException e1) {
			return true;
		} catch (DataFormatException e2) {
		} catch (Exception e3) {
			System.out.println("Problem detected. An unexpected exception has been thrown when calling the "
					+ "addCoins method of the ExceptionBank class. "
					+ "A NoSuchElementException was expected to be thrown. " + "But, it was NOT the case.");
			e3.printStackTrace();
		}
		return false;
	}

	/**
	 * This method checks whether ExceptionalBank.addCoins() method throws an
	 * IllegalArgumentException with an appropriate error message when it is passed
	 * a null reference as input argument.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testAddCoinsIllegalArgument() {
		ExceptionalBank bank = new ExceptionalBank(20);
		try {
			bank.addCoins(null);
		} catch (IllegalArgumentException e1) {
			return true;
		} catch (DataFormatException e2) {
		} catch (Exception e3) {
			System.out.println("Problem detected. An unexpected exception has been thrown when calling the "
					+ "addCoins method of the ExceptionBank class. "
					+ "An IllegalArgumentException was expected to be thrown. " + "But, it was NOT the case.");
			e3.printStackTrace();
		}
		return false;
	}

	/**
	 * This method checks whether ExceptionalBank.loadCoins() method throws a
	 * NullPointerException when it is passed a null reference.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testLoadCoinsNullReference() {
		ExceptionalBank bank = new ExceptionalBank(20);
		try {
			bank.loadCoins(null);
		} catch (NullPointerException e) {
			return true;
		} catch (FileNotFoundException e) {
		} catch (Exception e3) {
			System.out.println("Problem detected. An unexpected exception has been thrown when calling the "
					+ "loadCoins method of the ExceptionBank class. "
					+ "A NullPointerException was expected to be thrown. " + "But, it was NOT the case.");
			e3.printStackTrace();
		}
		return false;
	}

	/**
	 * This method checks whether ExceptionalBank.loadCoins() method throws a
	 * FileNotFoundException when it is passed a non found file.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testLoadCoinsFileNotFound() {
		ExceptionalBank bank = new ExceptionalBank(20);
		try {
			bank.loadCoins(new File("fake.txt"));
		} catch (FileNotFoundException e) {
			return true;
		} catch (Exception e2) {
			System.out.println("Problem detected. An unexpected exception has been thrown when calling the "
					+ "loadCoins method of the ExceptionBank class. "
					+ "A FileNotFoundException was expected to be thrown. " + "But, it was NOT the case.");
			e2.printStackTrace();
		}
		return false;
	}

	/**
	 * This method checks whether ExceptionalBank.loadCoins() method loads
	 * appropriately without throwing any exception when it is passed a found file.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testLoadCoinsFileFound() {
		ExceptionalBank bank = new ExceptionalBank(20);
		bank.addCoin(Coin.PENNY);
		try {
			File file = new File("bank.txt");
			bank.saveBankSummary(file);
			bank.loadCoins(file);
		} catch (FileNotFoundException e) {
			return false;
		} catch (Exception e2) {
			System.out.println("Problem detected. An unexpected exception has been thrown when calling the "
					+ "loadCoins method of the ExceptionBank class. ");
			e2.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Calls unit tests implemented in this class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(testExceptionalBankConstructor());
		System.out.println(testAddCoin());
		System.out.println(testRemoveCoinEmptyBank());
		System.out.println(testAddCoinsValidFormat());
		System.out.println(testAddCoinsNoSuchElement());
		System.out.println(testAddCoinsIllegalArgument());
		System.out.println(testLoadCoinsNullReference());
		System.out.println(testLoadCoinsFileNotFound());
		System.out.println(testLoadCoinsFileFound());
	}
}
