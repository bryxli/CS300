/**
 * This class represents a group of people.
 */
public class BoardingGroup {

	private String name;
	private int number;
	private boolean VIP = false;

	/**
	 * Creates a new BoardingGroup with a name and amount of people.
	 * 
	 * @param name   - name of group
	 * 
	 * @param number - number of people in group
	 */
	public BoardingGroup(String name, int number) {
		this.name = name;
		this.number = number;
	}

	/**
	 * Getter method to return name field.
	 * 
	 * @return name field
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter method to return number field.
	 * 
	 * @return number field
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Getter method to VIP field.
	 * 
	 * @return VIP field
	 */
	public boolean getVIP() {
		return VIP;
	}

	/**
	 * Setter method to change VIP field to true.
	 */
	public void setVIP() {
		VIP = true;
	}
}
