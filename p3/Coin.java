
/**
 * Coin object to be used in ElasticBank.java
 */
public class Coin {
  private String name;
  private int value;

  public Coin(String name, int value) {
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public int getValue() {
    return value;
  }
}
