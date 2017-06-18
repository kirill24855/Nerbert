package pro.shpin.kirill.nerbert;

import pro.shpin.kirill.nerbert.control.Controller;

import java.io.IOException;

public class Starter {

	public static void main(String[] args) {
		Controller control = new Controller();
		try {
			control.begin();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
