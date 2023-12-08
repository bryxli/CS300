/**
 *This class implements the student object.
 */
public class Student {
	private int x,y,id;
	

	/**
	 * Creates a student that holds x,y, and an id
	 * 
	 * @param x - x coordinate
	 * 
	 * @param y - y coordinate
	 * 
	 * @param id - student id
	 */
	public Student(int x, int y, int id) {
		this.x=x;
		this.y=y;
		this.id=id;
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
	 * Getter method to return id field.
	 * 
	 * @return id field
	 */
	public int getId() {
		return id;
	}
	
	@Override
	/**
	 * String representation of object
	 * 
	 * @return string representation
	 */
	public String toString() {
		return id+"("+x+","+y+")";
	}
}
