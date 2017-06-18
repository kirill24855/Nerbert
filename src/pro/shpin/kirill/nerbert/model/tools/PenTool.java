package pro.shpin.kirill.nerbert.model.tools;

import pro.shpin.kirill.nerbert.model.PenObject;
import pro.shpin.kirill.nerbert.model.PenPoint;

public class PenTool implements Tool {

	@Override
	public void applyPress(int x, int y, int[] color, Object obj) {
		PenObject penObject = (PenObject) obj;

		boolean clickedOnPoint = false;
		for (PenPoint point : penObject.points) {
			if ((point.x < x+5 && point.x > x-5) && (point.y < y+5 && point.y > y-5)) {
				penObject.selected = penObject.points.indexOf(point);
				clickedOnPoint = true;
				penObject.movingPoint = true;
				break;
			}

			if ((point.x + point.controlLastX < x+5 && point.x + point.controlLastX > x-5)
			 && (point.y + point.controlLastY < y+5 && point.y + point.controlLastY > y-5)) {
				penObject.selected = penObject.points.indexOf(point);
				clickedOnPoint = true;
				point.movingControlLast = true;
			}
			if ((point.x + point.controlNextX < x+5 && point.x + point.controlNextX > x-5)
			 && (point.y + point.controlNextY < y+5 && point.y + point.controlNextY > y-5)) {
				penObject.selected = penObject.points.indexOf(point);
				clickedOnPoint = true;
				point.movingControlNext = true;
			}
		}
		if (!clickedOnPoint) penObject.addPoint(x, y);
	}

	@Override
	public void applyRelease(int x, int y, int[] color, Object obj) {
		PenObject penObject = (PenObject) obj;
		penObject.movingPoint = false;
		PenPoint point = penObject.points.get(penObject.selected);
		point.movingControlLast = false;
		point.movingControlNext = false;
	}

	@Override
	public void applyDrag(int x, int y, int[] color, Object obj) {
		PenObject penObject = (PenObject) obj;
		PenPoint point = penObject.points.get(penObject.selected);
		if (penObject.movingPoint) {
			point.x = x;
			point.y = y;
		} else if (point.movingControlLast) {
			point.controlLastX = x - point.x;
			point.controlLastY = y - point.y;
			if (point.mode == PenPoint.MODE_MIRRORED) {
				point.controlNextX = -point.controlLastX;
				point.controlNextY = -point.controlLastY;
			} else if (point.mode == PenPoint.MODE_ASYMMETRICAL) {
				double theta = Math.atan2(point.controlLastY, point.controlLastX) + Math.PI;
				double radius = Math.sqrt(point.controlNextX*point.controlNextX + point.controlNextY*point.controlNextY);
				point.controlNextX = radius * Math.cos(theta);
				point.controlNextY = radius * Math.sin(theta);
			}
		} else if (point.movingControlNext) {
			point.controlNextX = x - point.x;
			point.controlNextY = y - point.y;
			if (point.mode == PenPoint.MODE_MIRRORED) {
				point.controlLastX = -point.controlNextX;
				point.controlLastY = -point.controlNextY;
			} else if (point.mode == PenPoint.MODE_ASYMMETRICAL) {
				double theta = Math.atan2(point.controlNextY, point.controlNextX) + Math.PI;
				double radius = Math.sqrt(point.controlLastX*point.controlLastX + point.controlLastY*point.controlLastY);
				point.controlLastX = radius * Math.cos(theta);
				point.controlLastY = radius * Math.sin(theta);
			}
		} else {
			point.controlNextX = x - point.x;
			point.controlNextY = y - point.y;
			point.controlLastX = -point.controlNextX;
			point.controlLastY = -point.controlNextY;
		}
	}

	@Override
	public String toString() {
		return "Pen";
	}
}
