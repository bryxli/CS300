/**
 * This class implements the foodrobot object.
 */
public class FoodRobot {
	private int x, y;
	private String name;

	/**
	 * Creates a foodrobot that holds x,y, and a name
	 * 
	 * @param x - x coordinate
	 * 
	 * @param y - y coordinate
	 * 
	 * @param y - robot name
	 */
	public FoodRobot(int x, int y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
	}

	/**
	 * Getter method to return x field.
	 * 
	 * @return x field
	 */
	public int getX() {
		return x;
	}

	/**
	 * Getter method to return y field.
	 * 
	 * @return y field
	 */
	public int getY() {
		return y;
	}

	/**
	 * Getter method to return name field.
	 * 
	 * @return name field
	 */
	public String getName() {
		return name;
	}

	@Override
	/**
	 * String representation of object
	 * 
	 * @return string representation
	 */
	public String toString() {
		return name + "(" + x + "," + y + ")";
	}
}
