package pro.shpin.kirill.nerbert.model;

public class PenPoint {

	public int x;
	public int y;

	public int controlNextX;
	public int controlNextY;

	public int controlLastX;
	public int controlLastY;

	public PenPoint(int x, int y) {
		this.x = x;
		this.y = y;

		controlNextX = x;
		controlNextY = y;

		controlLastX = x;
		controlLastY = y;
	}
}
