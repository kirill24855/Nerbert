package pro.shpin.kirill.nerbert.model.tools;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PaintBucketTool implements Tool {

	/**
	 * Determines a list of pixels in an image that are of the same color as the starting pixel
	 *
	 * @param x X-coordinate of the starting pixel
	 * @param y Y-coordinate of the starting pixel
	 * @param img Image whose list of pixels is to be determined
	 * @return The list of pixels of the same color
	 */
	private static List<short[]> queueFill(short x, short y, BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		int curColor = img.getRGB(x, y);
		LinkedList<short[]> nodeQueue = new LinkedList<>();
		short[] curNode = {x, y};
		nodeQueue.add(curNode);
		List<short[]> filledNodes = new ArrayList<>();
		while ((curNode = nodeQueue.pollFirst()) != null) {
			if (curNode[0] < 0 || curNode[1] < 0 || curNode[0] >= width || curNode[1] >= height || img.getRGB(curNode[0], curNode[1]) != curColor) continue;
			System.out.println(Arrays.toString(curNode));
			filledNodes.add(curNode);
			nodeQueue.offerLast(new short[] {curNode[0], (short) (curNode[1]-1)});
			nodeQueue.offerLast(new short[] {(short) (curNode[0]+1), curNode[1]});
			nodeQueue.offerLast(new short[] {curNode[0], (short) (curNode[1]+1)});
			nodeQueue.offerLast(new short[] {(short) (curNode[0]-1), curNode[1]});
		}
		return filledNodes;
	}

	/**
	 * Determines and replaces a list of pixels in an image that are of the same color as the starting pixel
	 *
	 * @param x X-coordinate of the starting pixel
	 * @param y Y-coordinate of the starting pixel
	 * @param fillColor The color with which to replace the list of pixels
	 * @param img Image whose list of pixels is to be determined and replaced
	 */
	private static void queueFillApplyChanges(short x, short y, int fillColor, BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		int curColor = img.getRGB(x, y);
		LinkedList<short[]> nodeQueue = new LinkedList<>();
		short[] curNode = {x, y};
		nodeQueue.add(curNode);
		while ((curNode = nodeQueue.pollFirst()) != null) {
			if (curNode[0] < 0 || curNode[1] < 0 || curNode[0] >= width || curNode[1] >= height || img.getRGB(curNode[0], curNode[1]) != curColor) continue;
			img.setRGB(curNode[0], curNode[1], fillColor);
			nodeQueue.offerLast(new short[] {curNode[0], (short) (curNode[1]-1)});
			nodeQueue.offerLast(new short[] {(short) (curNode[0]+1), curNode[1]});
			nodeQueue.offerLast(new short[] {curNode[0], (short) (curNode[1]+1)});
			nodeQueue.offerLast(new short[] {(short) (curNode[0]-1), curNode[1]});
		}
	}

	@Override
	public void applyPress(int x, int y, int[] color, Object obj) {
		queueFillApplyChanges((short) x, (short) y, color[0], (BufferedImage) obj);
	}

	@Override
	public void applyRelease(int x, int y, int[] color, Object obj) {

	}

	@Override
	public void applyDrag(int x, int y, int[] color, Object obj) {

	}

	@Override
	public String toString() {
		return "Paint Bucket";
	}
}
