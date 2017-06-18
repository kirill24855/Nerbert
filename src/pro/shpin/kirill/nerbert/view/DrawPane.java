package pro.shpin.kirill.nerbert.view;

import pro.shpin.kirill.nerbert.model.PenObject;
import pro.shpin.kirill.nerbert.model.PenPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.util.List;

public class DrawPane extends JPanel {

	private int width;
	private int height;

	private int[] color;

	private BufferedImage img;

	private List<PenObject> penObjects;

	public DrawPane(int width, int height, int[] color, BufferedImage img, List<PenObject> penObjects) {
		this.width = width;
		this.height = height;
		this.color = color;
		this.img = img;
		this.penObjects = penObjects;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, 0, 0, width, height, null);
		g2.setColor(new Color(color[0]));
		for (PenObject object : penObjects) {
			if (object.points.size() == 0) continue;

			PenPoint curPoint = object.points.get(0);

			if (object.points.size() > 1) {
				Path2D path = new Path2D.Float();
				path.moveTo(curPoint.x, curPoint.y);

				for (int i = 0; i < object.points.size(); i++) {
					PenPoint nextPoint = object.points.get(i);
					g2.fillOval(nextPoint.x-4, nextPoint.y-4, 8, 8);
					if (object.selected == i) {
						g2.drawLine(nextPoint.x, nextPoint.y, nextPoint.controlLastX, nextPoint.controlLastY);
						g2.drawLine(nextPoint.x, nextPoint.y, nextPoint.controlNextX, nextPoint.controlNextY);
						g2.drawOval(nextPoint.controlLastX-4, nextPoint.controlLastY-4, 8, 8);
						g2.drawOval(nextPoint.controlNextX-4, nextPoint.controlNextY-4, 8, 8);
					}

					path.curveTo(
							curPoint.controlNextX,
							curPoint.controlNextY,
							nextPoint.controlLastX,
							nextPoint.controlLastY,
							nextPoint.x,
							nextPoint.y
					);

					curPoint = nextPoint;
				}

				g2.draw(path);
			}
		}
	}
}
