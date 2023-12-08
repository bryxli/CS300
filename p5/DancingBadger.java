import java.io.File;

/**
 * This class defines the DancingBadger objects.
 * 
 */
public class DancingBadger extends StarshipRobot {
	protected DanceStep[] danceSteps;
	int stepIndex;

	/**
	 * Creates a new DancingBadger object.
	 */
	public DancingBadger(float[] start, DanceStep[] args) {
		super(new float[][] { start, args[0].getPositionAfter(start) });
		speed = 2;
		imageName = "images" + File.separator + "dancingBadger.png";
		danceSteps = args;
		stepIndex = 1;
	}

	/**
	 * Checks to see if the badger has finished the DanceStep. If it has, move on to
	 * the next step. If the dance is finished, restart it.
	 */
	@Override
	public void updateDestination() {
		if (moveTowardDestination()) {
			if (destination == beginAndEnd[1]) {
				beginAndEnd[0] = danceSteps[stepIndex].getPositionAfter(destination);
				destination = beginAndEnd[0];
				stepIndex++;
			} else {
				destination = beginAndEnd[1];
			}
			if (stepIndex > danceSteps.length - 1)
				stepIndex = 0;
		}
	}
}
