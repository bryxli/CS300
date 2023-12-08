/**
 * This class models and implements a doubly linked cart
 */
public class LinkedCart {
  private final Cart CART; // data field of this linked Cart
  private LinkedCart previous; // reference to the previous linked cart in
  // a list of carts
  private LinkedCart next; // reference to the next linked cart in a list of carts

  /**
   * Creates a new LinkedCart object with specific data cart and null for both
   * previous and next linked carts
   * 
   * @param cart - data of this linked cart
   */
  public LinkedCart(Cart cart) {
    CART = cart;
    previous = null;
    next = null;
  }

  /**
   * Creates a new LinkedCart object with specific data cart, previous and next
   * linked carts
   * 
   * @param cart     - data of this linked cart
   * 
   * @param previous - reference to the previous linked cart
   * 
   * @param next     - reference to the next linked cart
   */
  public LinkedCart(Cart cart, LinkedCart previous, LinkedCart next) {
    CART = cart;
    this.previous = previous;
    this.next = next;
  }

  /**
   * Returns a reference to the data cart of this linked cart
   * 
   * @return the data cart of this LinkedCart
   */
  public Cart getCart() {
    return CART;
  }

  /**
   * Returns a reference to the next LinkedCart attached to this LinkedCart
   * 
   * @return the next LinkedCart
   */
  public LinkedCart getNext() {
    return next;
  }

  /**
   * Returns a reference to the previous LinkedCart attached to this linked cart
   * 
   * @return the previous LinkedCart
   */
  public LinkedCart getPrevious() {
    return previous;
  }

  /**
   * Sets the next LinkedCart attached to this LinkedCart
   * 
   * @return next - the next to set
   */
  public void setNext(LinkedCart next) {
    this.next = next;
  }

  /**
   * Sets the previous LinkedCart attached to this LinkedCart
   * 
   * @return previous - the previous to set
   */
  public void setPrevious(LinkedCart previous) {
    this.previous = previous;
  }
}