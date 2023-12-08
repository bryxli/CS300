import java.io.File;

/**
 * This class defines the StarshipRobot objects.
 */
public class StarshipRobot extends FrozenStatue {
	protected float[][] beginAndEnd;
	protected float[] destination;
	protected float speed;

	/**
	 * Creates a new StarshipRobot object.
	 */
	StarshipRobot(float[][] beginAndEnd) {
		super(beginAndEnd[0]);
		this.beginAndEnd = beginAndEnd;
		speed = 6;
		destination = beginAndEnd[1];
		imageName = "images" + File.separator + "starshipRobot.png";
	}

	/**
	 * Moves the StarshipRobot one step towards its destination.
	 */
	protected boolean moveTowardDestination() {
		if (destination == beginAndEnd[1]) {
			x = x + ((speed * (destination[0] - x)) / (Math.abs(destination[0] - x) + 1));
			y = y + ((speed * (destination[1] - y)) / (Math.abs(destination[1] - y) + 1));
			if (Math.abs(y - destination[1]) < 6) {
				y = destination[1];
			}
		} else if (destination == beginAndEnd[0]) {
			if (!(Math.abs(destination[0] - x) < 150)) {
				x = x + ((speed * (destination[0] - x)) / (Math.abs(destination[0] - x) + 1));
			} else {
				x = x + ((speed * (destination[0] - x)) / (Math.abs(destination[0] - x) + 1));
				y = y + ((speed * (destination[1] - y)) / (Math.abs(destination[1] - y) + 1));
			}
		}
		if (destination[0] > x)
			isFacingRight = true;
		else
			isFacingRight = false;
		if (Math.sqrt(Math.pow(x - destination[0], 2) + Math.pow(y - destination[1], 2)) < speed * 2) {
			return true;
		} else
			return false;
	}

	/**
	 * Checks to see if the robot has reached its destination. If it has, sets its
	 * destination to the beginning.
	 */
	protected void updateDestination() {
		if (moveTowardDestination()) {
			if (destination == beginAndEnd[1]) {
				destination = beginAndEnd[0];
			} else {
				destination = beginAndEnd[1];
			}
		}
	}

	/**
	 * Draws the StarshipRobot to the screen.
	 */
	@Override
	public void update(SimulationEngine engine) {
		updateDestination();
		super.update(engine);
	}
}