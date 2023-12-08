import java.util.ArrayList;

/**
 * This class creates the objects to display and updates them.
 */
public class WinterCarnival extends SimulationEngine {

	private ArrayList<FrozenStatue> objects = new ArrayList<FrozenStatue>();

	/**
	 * Creates a new WinterCarnival object and adds all the objects to be displayed.
	 */
	public WinterCarnival() {
		objects.add(new FrozenStatue(new float[] { 600, 100 }));
		objects.add(new FrozenStatue(new float[] { 200, 500 }));
		objects.add(new StarshipRobot(new float[][] { { 0, 0 }, { 600, 100 } }));
		objects.add(new StarshipRobot(new float[][] { { 800, 300 }, { 200, 500 } }));
		DanceStep[] steps = { DanceStep.Left, DanceStep.Right, DanceStep.Right, DanceStep.Left, DanceStep.Down,
				DanceStep.Left, DanceStep.Right, DanceStep.Right, DanceStep.Left, DanceStep.Up };
		objects.add(new DancingBadger(new float[] { 304, 268 }, steps));
		objects.add(new DancingBadger(new float[] { 368, 268 }, steps));
		objects.add(new DancingBadger(new float[] { 432, 268 }, steps));
		objects.add(new DancingBadger(new float[] { 496, 268 }, steps));
	}

	/**
	 * Updates the objects in WinterCarnival.
	 */
	@Override
	public void update() {
		for (int i = 0; i < objects.size(); i++)
			objects.get(i).update(this);
	}

	/**
	 * Creates the WinterCarnival
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		WinterCarnival engine = new WinterCarnival();
	}
}