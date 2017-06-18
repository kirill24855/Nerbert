package pro.shpin.kirill.nerbert.model;

public class PenPoint {

	public int x;
	public int y;

	public double controlLastX;
	public double controlLastY;
	public boolean movingControlLast = false;

	public double controlNextX;
	public double controlNextY;
	public boolean movingControlNext = false;

	// range [0, 3)
	public byte mode;

	// 0 (MIRRORED) the helpers are opposite from each other, at the same distance from the point
	public static final byte MODE_MIRRORED = 0;
	// 1 (ASYMMETRICAL) the helpers are opposite from each other, but at different distances from the point
	public static final byte MODE_ASYMMETRICAL = 1;
	// 2 (DIFFERENT) the helpers do not depend on each other in any way
	public static final byte MODE_DIFFERENT = 2;

	public PenPoint(int x, int y) {
		this.x = x;
		this.y = y;

		controlLastX = 0;
		controlLastY = 0;

		controlNextX = 0;
		controlNextY = 0;

		mode = MODE_ASYMMETRICAL;
	}
}
