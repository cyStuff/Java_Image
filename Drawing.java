package core;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Drawing extends Image {
	private Graphics2D graph;

	public Drawing(String source) throws IOException {
		super(source);
		graph = getBI().createGraphics();
		setColor(new Color(0,0,0));
		fill(new Color(255,255,255));
	}

	public Drawing(int width, int height) {
		super(width, height);
		graph = getBI().createGraphics();
		setColor(new Color(0,0,0));
		fill(new Color(255,255,255));
	}

	protected Drawing(BufferedImage bufferedImage) {
		super(bufferedImage);
		graph = getBI().createGraphics();
		setColor(new Color(0,0,0));
		fill(new Color(255,255,255));
	}

	public void setColor(Color c) {
		graph.setColor(new java.awt.Color(c.getRed(), c.getGreen(), c.getBlue()));
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

	public void drawEllipse(int x, int y, int w, int h) {
		graph.drawOval(x, y, w, h);
	}

	public void fillEllipse(int x, int y, int w, int h) {
		graph.fillOval(x, y, w, h);
	}

	public void drawRect(int x, int y, int w, int h) {
		graph.drawRect(x, y, w, h);
	}

	public void fillRect(int x, int y, int w, int h) {
		graph.fillRect(x, y, w, h);
	}

	public void drawPolygon(int[] xp, int[] yp) {
		graph.drawPolygon(xp, yp, Math.min(xp.length, yp.length));
	}

	public void fillPolygon(int[] xp, int[] yp) {
		graph.fillPolygon(xp, yp, Math.min(xp.length, yp.length));
	}
}
