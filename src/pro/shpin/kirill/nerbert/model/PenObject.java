package pro.shpin.kirill.nerbert.model;

import java.util.ArrayList;
import java.util.List;

public class PenObject {

	public List<PenPoint> points;
	public int selected;

	public boolean movingPoint = false;

	public PenObject() {
		points = new ArrayList<>();
	}

	public PenObject(int x, int y) {
		points = new ArrayList<>();
		addPoint(x, y);
		selected = 0;
	}

	public void addPoint(int x, int y) {
		PenPoint point = new PenPoint(x, y);
		points.add(point);
		selected = points.indexOf(point);
	}

	public void moveControlNext(int x, int y) {
		PenPoint point = points.get(selected);
		point.controlNextX = x;
		point.controlNextY = y;
	}

	public void moveControlLast(int x, int y) {
		PenPoint point = points.get(selected);
		point.controlLastX = x;
		point.controlLastY = y;
	}
}
