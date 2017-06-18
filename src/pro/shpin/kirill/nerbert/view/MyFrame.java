package pro.shpin.kirill.nerbert.view;

import javax.swing.*;

public class MyFrame extends JFrame {

	public MyFrame(String title, int width, int height) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setVisible(true);
	}
}
