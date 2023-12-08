import java.io.File;

/**
 * This class defines the FrozenStatue objects.
 */
public class FrozenStatue {
	protected float x;
	protected float y;
	protected boolean isFacingRight;
	protected String imageName;

	/**
	 * Creates a new FrozenStatue object.
	 */
	public FrozenStatue(float[] positions) {
		x = positions[0];
		y = positions[1];
		isFacingRight = true;
		imageName = "images" + File.separator + "frozenStatue.png";
	}

	/**
	 * Draws the FrozenStatue to the screen.
	 */
	public void update(SimulationEngine engine) {
		engine.draw(imageName, x, y, isFacingRight);
	}

}