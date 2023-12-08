/**
 * This class models a sorted doubly linked list of carts. Each cart carries one
 * upper case alphabet letter. Duplicate letters are not allowed in this list.
 */
public class AlphabetList implements SortedListADT<Cart> {
  private static final Cart MIN_CART = new Cart("A"); // The smallest cart that
  // can be added to this sorted list
  private static final Cart MAX_CART = new Cart("Z"); // The largest cart that
  // can be added to this sorted list
  private LinkedCart head; // head of this doubly linked list
  private LinkedCart tail; // tail of this doubly linked list
  private int size; // size of this list
  private int capacity; // maximum number of carts which can be stored in this list

  /**
   * Creates an empty doubly linked list of carts with a capacity equals to 26
   */
  public AlphabetList() {
    head = null;
    tail = null;
    size = 0;
    capacity = 26;
  }

  /**
   * Creates an empty doubly linked list of carts with a given capacity
   * 
   * @param capacity - maximum number of carts to be connected in this list of
   *                 carts
   * 
   * @throws java.lang.IllegalArgumentException - with the following error message
   *                                            "The capacity of this list must be
   *                                            a non-zero a positive integer." if
   *                                            capacity is zero or negative
   */
  public AlphabetList(int capacity) {
    if (capacity <= 0)
      throw new IllegalArgumentException("The capacity of this list must be a non-zero a positive integer.");
    head = null;
    tail = null;
    size = 0;
    this.capacity = capacity;
  }

  /**
   * Adds a new cart to this sorted list.
   * 
   * @param newCart - to add to this list
   * 
   * @throws java.lang.IllegalArgumentException - with a descriptive error message
   *                                            if newCart does not carry one
   *                                            upper-case letter in the range "A"
   *                                            .. "Z" or if this list contains
   *                                            already a cart carrying the same
   *                                            letter. The descriptive error
   *                                            messages for these two cases can
   *                                            be respectively "Can only add
   *                                            carts carrying one upper-case
   *                                            alphabetic letter in the range A
   *                                            .. Z.", and "Cannot duplicate
   *                                            letters or carts in this list."
   * 
   * @throws java.lang.IllegalStateException    - with the following error message
   *                                            "This list is full. Cannot add
   *                                            another cart." if this list
   *                                            reached its capacity
   */
  public void add(Cart newCart) {
    if (size >= capacity)
      throw new IllegalStateException("This list is full. Cannot add another cart.");
    if (newCart.compareTo(MIN_CART) < 0 || newCart.compareTo(MAX_CART) > 0)
      throw new IllegalArgumentException(
          "Can only add carts carrying one upper-case alphabetic letter in the range A .. Z.");
    LinkedCart cart = new LinkedCart(newCart);
    if (isEmpty()) {
      head = cart;
      tail = cart;
    } else {
      LinkedCart temp = head;
      for (int i = 0; i < size; i++) {
        if (temp.getCart().compareTo(newCart) == 0)
          throw new IllegalArgumentException("Cannot duplicate letters or carts in this list.");
        else if (temp.getCart().compareTo(newCart) > 0)

          break;
        temp = temp.getNext();
      }
      if (temp == null) {
        tail.setNext(cart);
        cart.setPrevious(tail);
        ;
        tail = cart;
      } else if (temp.getPrevious() == null) {
        head.setPrevious(cart);
        ;
        cart.setNext(head);
        head = cart;
      } else {
        cart.setNext(temp);
        cart.setPrevious(temp.getPrevious());
        temp.getPrevious().setNext(cart);
        temp.setPrevious(cart);
      }
    }
    size++;
  }

  /**
   * Returns the capacity of this list in terms of maximum number of carts which
   * can be stored in it.
   * 
   * @return the capacity of this list
   */
  public int capacity() {
    return capacity;
  }

  public void clear() {
    if (!isEmpty()) {
      size = 0;
      head = null;
      tail = null;
    }
  }

  /**
   * Returns the cart at position index of this list without removing it
   * 
   * @param index - of the cart to return
   * 
   * @return the cart of this sorted list at the given index
   * 
   * @throws java.lang.IndexOutOfBoundsException - with the following error
   *                                             message "Invalid index." if index
   *                                             is less than 0 or index is
   *                                             greater or equal to size()
   */
  public Cart get(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("Invalid index.");
    if (!isEmpty()) {
      LinkedCart temp = head;
      for (int i = 0; i < index; i++) {
        temp = temp.getNext();
      }
      return temp.getCart();
    }
    return null;
  }

  /**
   * Returns the index of the cart carrying data within this list
   * 
   * @param findCart - cart to find in this list
   * 
   * @return the index of findCart within this list or -1 if this list does not
   *         contain that cart.
   */
  public int indexOf(Cart findCart) {
    if (!isEmpty()) {
      LinkedCart temp = head;
      for (int i = 0; i < size; i++) {
        if (temp.getCart() == findCart)
          return i;
        temp = temp.getNext();
      }
    }
    return -1;
  }

  /**
   * isEmpty() Checks whether this list is empty.
   * 
   * @return true if the list is empty, false otherwise
   */
  public boolean isEmpty() {
    return (head == null);
  }

  /**
   * Reads the contents of this list in the backward direction starting from its
   * tail
   * 
   * @return a String representation of the contents of this list read in the
   *         backward direction or an empty string if this list is empty. For
   *         instance, if the list contains the following letters "A", "B", "D",
   *         "M", and "O". This method MUST return the following string "OMDBA".
   */
  public String readBackward() {
    String read = "";
    if (!isEmpty()) {
      if (tail != null) {
        LinkedCart temp = tail;
        for (int i = 0; i < size; i++) {
          read += temp.getCart().getCargo();
          temp = temp.getPrevious();
        }
      } else
        read += head.getCart().getCargo();
    }
    return read;
  }

  /**
   * Reads the contents of this list in the forward direction starting from its
   * head.
   * 
   * @return a String representation of the contents of this list read in the
   *         forward direction or an empty string if this list is empty. For
   *         instance, if the list contains the following letters "A", "B", "D",
   *         "M", and "O". This method MUST return the following string "ABDMO".
   */
  public String readForward() {
    String read = "";
    if (!isEmpty()) {
      LinkedCart temp = head;
      for (int i = 0; i < size; i++) {
        read += temp.getCart().getCargo();
        temp = temp.getNext();
      }
    }
    return read;
  }

  /**
   * Returns and removes the cart from this sorted list at the given index
   * position
   * 
   * @param index - of the cart to be removed
   * 
   * @return the removed cart
   * 
   * @throws java.lang.IndexOutOfBoundsException - with the following error
   *                                             message "Invalid index." if index
   *                                             is less than 0 or index is larger
   *                                             or equal to size()
   */
  public Cart remove(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("Invalid index.");
    LinkedCart temp = head;
    if (index == 0) {// if head
      if (size == 1) {
        head = null;
        size = 0;
        return temp.getCart();
      } else {
        head.getNext().setPrevious(null);
        head = head.getNext();
      }
    } else if (index == size - 1) {// if tail
      tail.getPrevious().setNext(null);
      temp = tail;
      tail = tail.getPrevious();
    } else {// if in middle
      for (int i = 0; i < index; i++) {// sets temp as index object
        temp = temp.getNext();
      }
      temp.getPrevious().setNext(temp.getNext());
      temp.getNext().setPrevious(temp.getPrevious());
      if (temp == head)
        head = temp.getNext();
      else if (temp == tail)
        tail = temp.getPrevious();
    }
    size--;
    return temp.getCart();
  }

  /**
   * Returns the size of this list
   * 
   * @return the number of carts linked in this list
   */
  public int size() {
    return size;
  }

  /**
   * Returns a String representation of this list. Note that the implementation
   * details of this method is provided in the assignment's specification.
   * 
   * @Override toString in class java.lang.Object
   * 
   * @return a String representation of this list
   */
  @Override
  public String toString() {
    String string = "This list contains " + size + " cart(s)";
    if (size == 0) {
      return string;
    }
    string += ": [ ";
    LinkedCart currentCart = head;
    while (currentCart != null) {
      string += currentCart.getCart().toString() + " ";
      currentCart = currentCart.getNext();
    }
    string += "]";
    return string;

  }

}