package pro.shpin.kirill.nerbert.model.tools;

import java.awt.image.BufferedImage;

public interface Tool {

	/**
	 * Applies the Tool's action with the given pixel, color, and image on PRESS
	 *
	 * @param x X-coordinate of the pixel
	 * @param y Y-coordinate of the pixel
	 * @param color The color with which the Tool's action is to be performed
	 * @param obj The object on which the changes will be reflected
	 */
	void applyPress(int x, int y, int[] color, Object obj);

	/**
	 * Applies the Tool's action with the given pixel, color, and image on RELEASE
	 *
	 * @param x X-coordinate of the pixel
	 * @param y Y-coordinate of the pixel
	 * @param color The color with which the Tool's action is to be performed
	 * @param obj The object on which the changes will be reflected
	 */
	void applyRelease(int x, int y, int[] color, Object obj);

	/**
	 * Applies the Tool's action with the given pixel, color, and image on DRAG
	 *
	 * @param x X-coordinate of the pixel
	 * @param y Y-coordinate of the pixel
	 * @param color The color with which the Tool's action is to be performed
	 * @param obj The object on which the changes will be reflected
	 */
	void applyDrag(int x, int y, int[] color, Object obj);
}
