package pro.shpin.kirill.nerbert.control;

import pro.shpin.kirill.nerbert.model.PenObject;
import pro.shpin.kirill.nerbert.model.tools.PaintBucketTool;
import pro.shpin.kirill.nerbert.model.tools.PenTool;
import pro.shpin.kirill.nerbert.model.tools.PencilTool;
import pro.shpin.kirill.nerbert.model.tools.Tool;
import pro.shpin.kirill.nerbert.view.View;

import javax.imageio.ImageIO;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Controller {

	private static BufferedImage img;

	private View imageView;

	private int[] color;

	private Tool[] tools;
	private static Tool curTool;

	private static List<PenObject> penObjects;

	private static Object curObject;

	public Controller() {
		color = new int[] {0x00ff00, 0xff0000};
		tools = new Tool[] {
				new PencilTool(),
				new PenTool(),
				new PaintBucketTool(),
		};
		curTool = new PencilTool();
		penObjects = new ArrayList<>();
		penObjects.add(new PenObject());
	}

	public void begin() throws IOException {
		// TODO query the user to choose a file from their system

		img = ImageIO.read(new File("/home/kirill/Pictures/julia5.png"));
		curObject = img;

		imageView = new View(img.getWidth(), img.getHeight(), color, img, penObjects);
		imageView.render();

		new View(200, 50, tools);

		imageView.getFrame().addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent mouseEvent) {

			}

			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				curTool.applyPress(mouseEvent.getX(), mouseEvent.getY(), color, curObject);
				imageView.render();
			}

			@Override
			public void mouseReleased(MouseEvent mouseEvent) {
				curTool.applyRelease(mouseEvent.getX(), mouseEvent.getY(), color, curObject);
				imageView.render();
			}

			@Override
			public void mouseEntered(MouseEvent mouseEvent) {

			}

			@Override
			public void mouseExited(MouseEvent mouseEvent) {

			}
		});
		imageView.getFrame().addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent mouseEvent) {
				curTool.applyDrag(mouseEvent.getX(), mouseEvent.getY(), color, curObject);
				imageView.render();
			}

			@Override
			public void mouseMoved(MouseEvent mouseEvent) {

			}
		});
	}

	public static void setCurTool(Tool tool) {
		curTool = tool;
		if (tool instanceof PenTool) curObject = penObjects.get(0);
		else curObject = img;
	}

}
