package pro.shpin.kirill.nerbert.view;

import pro.shpin.kirill.nerbert.control.Controller;
import pro.shpin.kirill.nerbert.model.PenObject;
import pro.shpin.kirill.nerbert.model.tools.Tool;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.List;

public class View {

	private MyFrame frame;
	private JPanel pane;

	public View(int width, int height, int[] color, BufferedImage img, List<PenObject> penObjects) {
		frame = new MyFrame("Nerbert", width, height);
		pane = new DrawPane(width, height, color, img, penObjects);
		frame.add(pane);
	}

	public View(int width, int height, Tool[] tools) {
		frame = new MyFrame("Tools", width, height);
		pane = new JPanel();
		ButtonGroup group = new ButtonGroup();
		for (Tool tool : tools) {
			JRadioButton button = new JRadioButton(tool.toString());
			button.addActionListener((ActionEvent event) -> Controller.setCurTool(tool));
			group.add(button);
			pane.add(button);
		}

		pane.revalidate();
		frame.add(pane);
	}

	public void render() {
		pane.repaint();
	}

	public MyFrame getFrame() {
		return frame;
	}
}
