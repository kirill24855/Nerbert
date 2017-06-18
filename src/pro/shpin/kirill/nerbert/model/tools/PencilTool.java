package pro.shpin.kirill.nerbert.model.tools;

import java.awt.image.BufferedImage;

public class PencilTool implements Tool {

	/**
	 * Colors the pixel directly under the mouse with the primary color
	 *
	 * @param x X-coordinate of the pixel
	 * @param y Y-coordinate of the pixel
	 * @param color The color with which to replace the pixel
	 * @param img The image whose pixel is to be replaced
	 */
	private static void colorPixel(int x, int y, int[] color, BufferedImage img) {
		img.setRGB(x, y, color[0]);
	}

	@Override
	public void applyPress(int x, int y, int[] color, Object obj) {
		colorPixel(x, y, color, (BufferedImage) obj);
	}

	@Override
	public void applyRelease(int x, int y, int[] color, Object obj) {
		colorPixel(x, y, color, (BufferedImage) obj);
	}

	@Override
	public void applyDrag(int x, int y, int[] color, Object obj) {
		colorPixel(x, y, color, (BufferedImage) obj);
	}

	@Override
	public String toString() {
		return "Pencil";
	}
}
