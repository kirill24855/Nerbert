package pro.shpin.kirill.nerbert.model.tools;

import pro.shpin.kirill.nerbert.model.PenObject;
import pro.shpin.kirill.nerbert.model.PenPoint;

public class PenTool implements Tool {

	@Override
	public void applyPress(int x, int y, int[] color, Object obj) {
		PenObject penObject = (PenObject) obj;
		penObject.addPoint(x, y);
	}

	@Override
	public void applyRelease(int x, int y, int[] color, Object obj) {

	}

	@Override
	public void applyDrag(int x, int y, int[] color, Object obj) {
		PenObject penObject = (PenObject) obj;
		PenPoint point = penObject.points.get(penObject.selected);
		point.controlNextX = x;
		point.controlNextY = y;
		point.controlLastX = 2*point.x - x;
		point.controlLastY = 2*point.y - y;
	}

	@Override
	public String toString() {
		return "Pen";
	}
}
