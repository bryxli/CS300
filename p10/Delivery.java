/**
 * This class implements the delivery object.
 */
public class Delivery implements Comparable<Delivery> {
	private int studentId;
	private String robotName;
	private int distance;

	/**
	 * Creates a delivery object that holds a student and a foodrobot
	 * 
	 * @param student   - student to deliver to
	 * 
	 * @param foodRobot - robot to be used
	 * 
	 */
	public Delivery(Student student, FoodRobot foodRobot) {
		studentId = student.getId();
		robotName = foodRobot.getName();
		distance = Math.abs(student.getX() - foodRobot.getX()) + Math.abs(student.getY() - foodRobot.getY());
	}

	@Override
	/**
	 * Determines if deliveries are the same.
	 * 
	 * @return true if equal, false otherwise
	 */
	public boolean equals(Object object) {
		if (object instanceof Delivery) {
			Delivery delivery = (Delivery) object;
			if (studentId == delivery.studentId || robotName.equals(delivery.robotName))
				return true;
		} else if (object instanceof Student) {
			Student student = (Student) object;
			if (studentId == student.getId())
				return true;
		} else if (object instanceof FoodRobot) {
			FoodRobot foodrobot = (FoodRobot) object;
			if (robotName.equals(foodrobot.getName()))
				return true;
		}
		return false;
	}

	@Override
	/**
	 * String representation of object
	 * 
	 * @return string representation
	 */
	public String toString() {
		return "The distance between " + studentId + " and " + robotName + " is " + distance;
	}

	@Override
	/**
	 * Compares to Delivery objects
	 * 
	 * @return -1 if object distance is less than parameter, 1 otherwise. If
	 *         distance is equal, 01 if student id is smaller, 1 otherwise. If id is
	 *         the same, -1 if robot name comes before parameter, 1 otherwise.
	 */
	public int compareTo(Delivery delivery) {
		if (distance < delivery.distance)
			return -1;
		else if (distance > delivery.distance)
			return 1;
		else {
			if (studentId < delivery.studentId)
				return -1;
			else if (studentId > delivery.studentId)
				return 1;
			else {
				int comparison = robotName.compareTo(delivery.robotName);
				if (comparison < 0)
					return -1;
				else
					return 1;
			}
		}
	}
}
