import java.util.Random;

/**
 * Piggy bank that holds object Coin and is expandable up to 2 times.
 */
public class ElasticBank {
  private Coin[] coins;
  private int size;
  private int expansionsLeft;
  private static Random rand = new Random(100);

  public ElasticBank() {
    this.size = 0;
    this.expansionsLeft = 2;
    this.coins = new Coin[10];
  }

  public ElasticBank(int initialCapacity) {
    this.size = 0;
    this.expansionsLeft = 2;
    this.coins = new Coin[initialCapacity];
  }

  /**
   * Returns the capacity available in ElasticBank
   * 
   * @return the amount of spaces left
   */
  public int capacity() {
    int capacity = 0;
    for (int i = 0; i < coins.length; i++)
      if (coins[i] == null)
        capacity++;
    return capacity;
  }

  /**
   * Returns the amount of expansions left
   * 
   * @return the amount of expansions left
   */
  public int getExpansions() {
    return this.expansionsLeft;
  }

  /**
   * Returns the current amount of coins
   * 
   * @return the current amount of coins
   */
  public int getSize() {
    return this.size;
  }

  /**
   * Returns the balance of a of ElasticBank in cents
   * 
   * @return the total value of the coins held in cents
   */
  public int getBalance() {
    int balance = 0;
    for (int i = 0; i < coins.length; i++) {
      if (coins[i] != null)
        balance += coins[i].getValue();
    }
    return balance;
  }

  /**
   * Returns the name and value of every coin
   * 
   * @return a string holding the name and value of every coin
   */
  public String getCoins() {
    String values = "";
    for (int i = 0; i < coins.length; i++) {
      if (coins[i] != null)
        values += ("(" + coins[i].getName() + ", " + coins[i].getValue() + ") ");
    }
    return values;
  }

  /**
   * Removes a coin from ElasticBank
   * 
   * @return the removed Coin object
   */
  public Coin removeCoin() {
    int index = rand.nextInt(size);
    Coin newCoin = coins[index];
    coins[index] = null;
    size--;

    return newCoin;
  }

  /**
   * Empties ElasticBank by setting all elements to null
   */
  public void empty() {
    for (int i = 0; i < coins.length; i++) {
      coins[i] = null;
    }
    size = 0;
  }

  /**
   * Adds a coin to ElasticBank and if the capacity is full, it expands the bank.
   * 
   * @param c the coin to be added
   */
  public void addCoin(Coin c) {
    if (capacity() > 0) {
      boolean loop = true;
      int counter = 0;
      while (loop) {
        if (coins[counter] == null) {
          coins[counter] = c;
          size++;
          loop = false;
        }
        counter++;
      }
    } else if (expansionsLeft > 0) {
      Coin[] newBank = new Coin[coins.length + 10];
      for (int i = 0; i < coins.length; i++) {
        newBank[i] = coins[i];
      }
      coins = newBank;
      expansionsLeft--;
      addCoin(c);
    } else {
      empty();
      addCoin(c);
    }
  }
}
