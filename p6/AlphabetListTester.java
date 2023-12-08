/**
 * This class implements unit test methods to check the correctness of LinkedCart and AlphabetList
 * classes defined in the CS300 Spring 2020 - P06 Alphabet Train programming assignment.
 *
 */
public class AlphabetListTester {

  /**
   * This method should test and make use of at least the LinkedCart constructor, an accessor
   * (getter) method, and a mutator (setter) method defined in the LinkedCart class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLinkedCart() {
    Cart startCart = new Cart("A");
    LinkedCart testLinkedCart = new LinkedCart(startCart, null, new LinkedCart(new Cart ("B")));
    
    if (!(testLinkedCart.getCart().compareTo(startCart) == 0) &&
        !(testLinkedCart.getNext().getCart().equals(new Cart ("B")))) {
      return false;
    }
    
    Cart testNextCart = new Cart("C");
    Cart testPreviousCart = new Cart("D");
    
    testLinkedCart.setPrevious(new LinkedCart(new Cart("D")));
    testLinkedCart.setNext(new LinkedCart(new Cart("C")));
    
    if (testLinkedCart.getNext().getCart().compareTo(testNextCart) != 0 &&
        testLinkedCart.getPrevious().getCart().compareTo(testPreviousCart) != 0) {
      return false;
    }
    return true;
  }

  /**
   * This method checks for the correctness of both AlphabetList constructors and the instance
   * method isEmpty() when called on an empty alphabet list object.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAlphabetListConstructorIsEmpty() {
    AlphabetList emptyList = new AlphabetList();
    AlphabetList emptyList2 = new AlphabetList(10);
    
    if (emptyList.isEmpty() && emptyList2.isEmpty()) {
      return true;
    }
    return false;
  }

  /**
   * This method checks for the correctness of the AlphabetList(int) constructor when passed a
   * negative int value.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAlphabetListConstructorBadInput() {
    try {
      AlphabetList badConstructor = new AlphabetList(-5);
    } catch (IllegalArgumentException e) {
      return true;
    }
    return false;
  }


  /**
   * This method checks for the correctness of the AlphabetList.add() method when it is passed bad
   * inputs. This method must consider at least the test scenarios provided in the detailed
   * description of these javadocs. (1) Try adding a null to the list; (2) Try adding a cart which
   * carries a non upper-case alphabet letter, for instance "Hello" or "1" or "a". (3) Try adding a
   * duplicate cart to the list.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAlphabetListAddBadInput() {
    boolean nullResult = false;
    boolean errorResult = false;
    boolean duplicateResult = false;
    
    try {
      AlphabetList nullAdded = new AlphabetList();
      nullAdded.add(null);
      System.out.println(nullAdded.get(1).toString());
    } catch (NullPointerException e) {
      //System.out.println("Detected the null");
      nullResult = true;
    }
    
    try {
      AlphabetList fuckTheAlphabet = new AlphabetList();
      fuckTheAlphabet.add(new Cart("lies"));
    } catch (IllegalArgumentException e) {
      //System.out.println("Detected error cart");
      errorResult = true;
    }
    
    try {
      AlphabetList duplicateCart = new AlphabetList();
      duplicateCart.add(new Cart("A"));
      duplicateCart.add(new Cart("A"));
    } catch (IllegalArgumentException e) {
      //System.out.println("Detected duplicate cart");
      duplicateResult = true;
    }
    
    if (nullResult && errorResult && duplicateResult) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * This method checks for the correctness of the AlphabetList.add() considering at least the test
   * scenarios provided in the detailed description of these javadocs. (1) Try adding a cart to an
   * empty list; (2) Try adding a cart which is expected to be added at the head of a non-empty
   * alphabet list; (3) Try adding a cart which is expected to be added at the end of a non-empty
   * alphabet list; (4) Try adding a cart which is expected to be added at the middle of a non-empty
   * alphabet list. For each of those scenarios, make sure that the size of the list is
   * appropriately updated after a call without errors of the add() method, and that the contents of
   * the list is as expected whatever if list is read in the forward or backward directions. You can
   * also check the correctness of AlphabetList.get(int), AlphabetList.indexOf(Cart), and
   * AlphabetList.size() in this test method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAlphabetListAdd() {
    boolean passes = true;
    AlphabetList testAddList = new AlphabetList();
    Cart indexOfCartTest = new Cart("G");
    testAddList.add(new Cart("J"));
    testAddList.add(new Cart("A"));
    testAddList.add(new Cart("Q"));
    testAddList.add(indexOfCartTest);
    
    if (!testAddList.readForward().equals("AGJQ") || !testAddList.readBackward().equals("QJGA")) {
      System.out.println("Fails the print test");
      passes = false;
    }
    
    if (testAddList.get(2).compareTo(new Cart("J")) != 0) {
      System.out.println("Fails the get test");
      passes = false;
    }
    
    if (testAddList.indexOf(indexOfCartTest) != 1) {
      System.out.println("Fails the indexOf test");
      passes = false;
    }
    
    if ((testAddList.size() != 4)) {
      System.out.println("Fails the size test");
      passes = false;
    }
    
    return passes;
  }

  /**
   * This method checks for the correctness of the AlphabetList.remove() considering at least the
   * test scenarios provided in the detailed description of these javadocs. (1) Try removing a cart
   * from an empty list or pass a negative index to AlphabetList.remove() method; (2) Try removing a
   * cart (at position index 0) from a list which contains only one cart; (3) Try to remove a cart
   * (position index 0) from a list which contains at least 2 carts; (4) Try to remove a cart from
   * the middle of a non-empty list containing at least 3 carts; (5) Try to remove the cart at the
   * end of a list containing at least two carts. For each of those scenarios, make sure that the 
   * size of the list is appropriately updated after a call without errors of the add() method, 
   * and that the contents of the list is as expected whatever if list is read in the forward or 
   * backward directions.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAlphabetListRemove() {
    boolean passes = true;
    AlphabetList testRemoveList = new AlphabetList();
    
    try {
      testRemoveList.remove(0);
    } catch (IndexOutOfBoundsException e) {
      //passes
    } catch (Exception e) {
      System.out.println("Fails empty list test");
      passes = false;
    }
    
    testRemoveList.add(new Cart("A"));
    
    try {
      testRemoveList.remove(0);
    } catch (NullPointerException e) {
      System.out.println("NullPointerException");
      passes = false;
    } catch (Exception e) {
      System.out.println("Fails removing head test");
      passes = false;
    }
    
    testRemoveList.add(new Cart("B"));
    testRemoveList.add(new Cart("C"));
    
    try {
      if (testRemoveList.remove(0).compareTo(new Cart("B")) != 0) {
        passes = false;
      }
    } catch (NullPointerException e) {
      System.out.println("Fails multicart removing head test");
      passes = false;
    } catch (Exception e) {
      System.out.println("Fails multicart removing head test");
      passes = false;
    }
    
    testRemoveList.add(new Cart("D"));
    testRemoveList.add(new Cart("E"));
    testRemoveList.add(new Cart("F"));
    
    try {
      testRemoveList.remove(2);
    } catch (NullPointerException e) {
      System.out.println("Fails removing middle test");
      passes = false;
    } catch (Exception e) {
      System.out.println("Fails removing head test");
      passes = false;
    }
    
    try {
      testRemoveList.remove(testRemoveList.size()-1);
    } catch (NullPointerException e) {
      System.out.println("Fails removing tail test");
      passes = false;
    } catch (Exception e) {
      System.out.println("Fails removing tail test");
      passes = false;
    }
    
    return passes;
  }


  /**
   * This method calls all the test methods defined and implemented in your AlphabetListTester
   * class.
   * 
   * @return true if all the test methods defined in this class pass, and false otherwise.
   */
  public static boolean runAllTests() {
    if (testLinkedCart() && testAlphabetListConstructorIsEmpty() && testAlphabetListConstructorBadInput()
        && testAlphabetListAddBadInput() && testAlphabetListAdd() && testAlphabetListRemove()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Driver method defined in this AlphabetListTester class
   * 
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    System.out.println(runAllTests());
  }
}
