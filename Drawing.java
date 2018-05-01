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
		setColor(new Color(0,0,0));
		fill(new Color(255,255,255));
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
		setColor(new Color(0,0,0));
		fill(new Color(255,255,255));
	}

	/**
	 * Sets the drawing color.
	 * @param color - Color to draw with.
	 */
	public void setColor(Color color) {
		graph.setColor(new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue()));
	}
	
	/**
	 * Change to use antialiasing.
	 * @param bool
	 */
	public void antialiasing(boolean bool) {
		if (bool) {
			graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
		else {
			graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		}
	}
	
	public void setStrokeSize(double size) {
		strokeSize = (float)size;
		graph.setStroke(new BasicStroke(strokeSize));
	}
	
	public void setStrokeDash(float[] dash) {
		graph.setStroke(new BasicStroke(strokeSize, 2, 0, 10, dash, 0));
	}

	public void setFont(String name, int size) {
		graph.setFont(new Font(name, Font.PLAIN, size));
	}

	public void openFont(String fileName, int size) throws IOException {
		try {
			graph.setFont(Font.createFont(Font.TRUETYPE_FONT,
					new File(fileName)).deriveFont(size));
		} catch (FontFormatException e) {
		}
	}

	public void drawString(String str, int x, int y) {
		graph.drawString(str, x, y);
	}

	public void drawLine(int x1, int y1, int x2, int y2) {
		graph.drawLine(x1, y1, x2, y2);
	}

	public void drawEllipse(int x, int y, int width, int height) {
		graph.drawOval(x, y, width, height);
	}

	public void fillEllipse(int x, int y, int width, int height) {
		graph.fillOval(x, y, width, height);
	}

	public void drawRect(int x, int y, int width, int height) {
		graph.drawRect(x, y, width, height);
	}

	public void fillRect(int x, int y, int width, int height) {
		graph.fillRect(x, y, width, height);
	}

	public void drawPolygon(int[] xPoints, int[] yPoints) {
		graph.drawPolygon(xPoints, yPoints, Math.min(xPoints.length, yPoints.length));
	}

	public void fillPolygon(int[] xPoints, int[] yPoints) {
		graph.fillPolygon(xPoints, yPoints, Math.min(xPoints.length, yPoints.length));
	}
	
	public void drawArc(int x, int y, int width, int height, int startAngle, int endAngle) {
		graph.drawArc(x, y, width, height, startAngle, endAngle-startAngle);
	}
	
	public void fillArc(int x, int y, int width, int height, int startAngle, int endAngle) {
		graph.fillArc(x, y, width, height, startAngle, endAngle-startAngle);
	}
	
	public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		graph.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
	}
	
	public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		graph.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
	}
}
