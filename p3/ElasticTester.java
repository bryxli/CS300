/**
 * Tests the methods defined in ElasticBank.java
 */
public class ElasticTester {
  /**
   * Checks if the Coin classes is instantiable.
   * 
   * @return true when this test verifies the proper values for the Coin class, and false otherwise
   */
  public static boolean testCoinInstantiableClass() {
    Coin penny = new Coin("PENNY", 1);
    Coin quarter = new Coin("QUARTER", 25);
    if (!penny.getName().equals("PENNY"))
      return false;
    if (penny.getValue() != 1)
      return false;
    if (!quarter.getName().equals("QUARTER"))
      return false;
    if (quarter.getValue() != 25)
      return false;
    return true;
  }

  /**
   * Checks whether the getBalance() method works as expected.
   * 
   * @return true when this test verifies getBalance() works as expected, and false otherwise
   */
  public static boolean testBalanceAccessors() {
    ElasticBank one = new ElasticBank(5);
    ElasticBank two = new ElasticBank(7);
    one.addCoin(new Coin("PENNY", 1));
    two.addCoin(new Coin("NICKEL", 5));
    if (one.getBalance() != 1)
      return false;
    if (two.getBalance() != 5)
      return false;
    return true;
  }

  /**
   * Checks whether the capacity() method works as expected.
   * 
   * @return true when this test verifies capacity() works as expected, and false otherwise
   */
  public static boolean testCapacityAccessors() {
    ElasticBank one = new ElasticBank(5);
    ElasticBank two = new ElasticBank(7);
    one.addCoin(new Coin("PENNY", 1));
    two.addCoin(new Coin("NICKEL", 5));
    if (one.capacity() != 4)
      return false;
    if (two.capacity() != 6)
      return false;
    return true;
  }

  /**
   * Checks whether the getExpansions() method works as expected.
   * 
   * @return true when this test verifies getExpansions() works as expected, and false otherwise
   */
  public static boolean testExpansionsAccessors() {
    ElasticBank one = new ElasticBank(5);
    ElasticBank two = new ElasticBank(7);
    one.addCoin(new Coin("PENNY", 1));
    two.addCoin(new Coin("NICKEL", 5));
    if (one.getExpansions() != 2)
      return false;
    if (two.getExpansions() != 2)
      return false;
    return true;
  }

  /**
   * Checks whether the getSize() method works as expected.
   * 
   * @return true when this test verifies getSize() works as expected, and false otherwise
   */
  public static boolean testSizeAccessors() {
    ElasticBank one = new ElasticBank(5);
    ElasticBank two = new ElasticBank(7);
    one.addCoin(new Coin("PENNY", 1));
    two.addCoin(new Coin("NICKEL", 5));
    if (one.getSize() != 1)
      return false;
    if (two.getSize() != 1)
      return false;
    return true;
  }

  /**
   * Checks whether the empty() method works as expected.
   * 
   * @return true when this test verifies the empty() method works as expected, and false otherwise
   */
  public static boolean testEmptyMutators() {
    ElasticBank one = new ElasticBank(5);
    ElasticBank two = new ElasticBank(7);
    one.addCoin(new Coin("PENNY", 1));
    two.addCoin(new Coin("NICKEL", 5));
    one.empty();
    for (int i = 0; i < 7; i++) {
      two.addCoin(new Coin("PENNY", 1));
    }
    two.empty();
    if (one.getSize() != 0)
      return false;
    if (two.getSize() != 0)
      return false;
    return true;
  }

  /**
   * Checks whether the removeCoin() method works as expected.
   * 
   * @return true when this test verifies removeCoin() works as expected, and false otherwise
   */
  public static boolean testRemoveCoinMutators() {
    ElasticBank one = new ElasticBank(5);
    ElasticBank two = new ElasticBank(7);
    one.addCoin(new Coin("PENNY", 1));
    two.addCoin(new Coin("NICKEL", 5));

    one.removeCoin();
    two.removeCoin();
    if (one.getSize() != 0)
      return false;
    if (two.getSize() != 0)
      return false;
    return true;
  }

  /**
   * Checks whether the addCoin() method works as expected.
   * 
   * @return true when this test verifies addCoin() works as expected, and false otherwise
   */
  public static boolean testAddCoin() {
    ElasticBank one = new ElasticBank(5);
    ElasticBank two = new ElasticBank(7);
    one.addCoin(new Coin("PENNY", 1));
    for (int i = 0; i < 7; i++) {
      two.addCoin(new Coin("NICKEL", 5));
    }

    if (one.getSize() != 1)
      return false;
    if (two.getSize() != 7)
      return false;
    return true;
  }

  public static void main(String args[]) {
    System.out.println("testCoinInstantiableClass(): " + testCoinInstantiableClass());
    System.out.println("testBalanceAccessors(): " + testBalanceAccessors());
    System.out.println("testEmptyMutators(): " + testEmptyMutators());
    System.out.println("testRemoveCoinMutators(): " + testRemoveCoinMutators());
    System.out.println("testAddCoin(): " + testAddCoin());
  }
}
