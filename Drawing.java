package core;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;

public class Drawing extends Image {
	private Graphics2D graph;
	private float strokeSize;
	private float[] dash = new float[1];

	/**
	 * Opens an image from a file source.
	 * 
	 * @param source
	 *            - Source location of the image file.
	 * @throws IOException
	 */
	public Drawing(String source) throws IOException {
		super(source);
		graph = getBI().createGraphics();
		setColor(new Color(0, 0, 0));
		fill(new Color(255, 255, 255));
	}

	/**
	 * creates a black image with width and height.
	 * 
	 * @param width
	 *            - Width of the image.
	 * @param height
	 *            - Height of the image.
	 */
	public Drawing(int width, int height) {
		super(width, height);
		graph = getBI().createGraphics();
		setColor(new Color(0, 0, 0));
		fill(new Color(255, 255, 255));
	}

	/**
	 * Sets the drawing color.
	 * 
	 * @param color
	 *            - Color to draw with.
	 */
	public void setColor(Color color) {
		graph.setColor(new java.awt.Color(color.getRed(), color.getGreen(),
				color.getBlue()));
	}

	/**
	 * Change to use antialiasing.
	 * 
	 * @param bool
	 */
	public void antialiasing(boolean bool) {
		if (bool) {
			graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
		} else {
			graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_OFF);
		}
	}

	/**
	 * Sets the stroke size when drawing shapes
	 * 
	 * @param size
	 */
	public void setStrokeSize(double size) {
		strokeSize = (float) size;
		graph.setStroke(new BasicStroke(strokeSize, 2, 0, 10, dash, 0));
	}

	/**
	 * Sets the dash pattern for the stroke.
	 * 
	 * @param dash
	 */
	public void setStrokeDash(float[] dash) {
		graph.setStroke(new BasicStroke(strokeSize, 2, 0, 10, dash, 0));
	}

	/**
	 * Sets the font to some system installed font.
	 * 
	 * @param name
	 * @param size
	 */
	public void setFont(String name, int size) {
		graph.setFont(new Font(name, Font.PLAIN, size));
	}

	/**
	 * Sets the font based on a .ttf file.
	 * 
	 * @param fileName
	 * @param size
	 * @throws IOException
	 */
	public void openFont(String fileName, int size) throws IOException {
		try {
			graph.setFont(Font.createFont(Font.TRUETYPE_FONT,
					new File(fileName)).deriveFont(size));
		} catch (FontFormatException e) {
		}
	}

	/**
	 * Draws a string.
	 * 
	 * @param str
	 * @param x
	 * @param y
	 */
	public void drawString(String str, int x, int y) {
		graph.drawString(str, x, y);
	}

	/**
	 * Draws a line from point (x1, y1) to (x2, y2).
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		graph.drawLine(x1, y1, x2, y2);
	}

	/**
	 * Draws an elipse of size (width, height) with the top left corner at (x,
	 * y).
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void drawEllipse(int x, int y, int width, int height) {
		graph.drawOval(x, y, width, height);
	}

	/**
	 * Draws and fills an elipse of size (width, height) with the top left
	 * corner at (x, y).
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void fillEllipse(int x, int y, int width, int height) {
		graph.fillOval(x, y, width, height);
	}

	/**
	 * Draws a rectangle of size (width, height) with the left corner at (x, y)
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void drawRect(int x, int y, int width, int height) {
		graph.drawRect(x, y, width, height);
	}

	/**
	 * Draws and fills a rectangle of size (width, height) with the left corner
	 * at (x, y)
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void fillRect(int x, int y, int width, int height) {
		graph.fillRect(x, y, width, height);
	}

	/**
	 * Draws a polygon with points from xPoints and yPoints. Will only use the
	 * number of points in the smallest list.
	 * 
	 * @param xPoints
	 * @param yPoints
	 */
	public void drawPolygon(int[] xPoints, int[] yPoints) {
		graph.drawPolygon(xPoints, yPoints,
				Math.min(xPoints.length, yPoints.length));
	}

	/**
	 * Draws and fills a polygon with points from xPoints and yPoints. Will only
	 * use the number of points in the smallest list.
	 * 
	 * @param xPoints
	 * @param yPoints
	 */
	public void fillPolygon(int[] xPoints, int[] yPoints) {
		graph.fillPolygon(xPoints, yPoints,
				Math.min(xPoints.length, yPoints.length));
	}

	/**
	 * Draws an elipse, but only draws from angle startAngle to endAngle.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param startAngle
	 * @param endAngle
	 */
	public void drawArc(int x, int y, int width, int height, int startAngle,
			int endAngle) {
		graph.drawArc(x, y, width, height, startAngle, endAngle - startAngle);
	}

	/**
	 * Draws and fills an elipse, but only draws from angle startAngle to
	 * endAngle.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param startAngle
	 * @param endAngle
	 */
	public void fillArc(int x, int y, int width, int height, int startAngle,
			int endAngle) {
		graph.fillArc(x, y, width, height, startAngle, endAngle - startAngle);
	}

	/**
	 * Draws a rectangle, but cuts the corners off based on the arcWidth and
	 * arcHeight.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param arcWidth
	 * @param arcHeight
	 */
	public void drawRoundRect(int x, int y, int width, int height,
			int arcWidth, int arcHeight) {
		graph.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
	}

	/**
	 * Draws and fills a rectangle, but cuts the corners off based on the
	 * arcWidth and arcHeight.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param arcWidth
	 * @param arcHeight
	 */
	public void fillRoundRect(int x, int y, int width, int height,
			int arcWidth, int arcHeight) {
		graph.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
	}
}
