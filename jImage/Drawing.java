package jImage;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;

/**
 * Image with abilities to draw shapes and text.
 * 
 * @author Cy
 *
 */
public class Drawing extends Image {
  private Graphics2D graph;

  /**
   * Opens an image from a file source.
   * 
   * @param source Source location of the image file.
   */
  public Drawing(String source) {
    super(source);
    graph = getBI().createGraphics();
    setStrokeColor(new Color(0,0,0));
    fill(new Color(255, 255, 255));
  }

  /**
   * creates a black image with width and height.
   * 
   * @param width Width of the image.
   * @param height Height of the image.
   */
  public Drawing(int width, int height) {
    super(width, height);
    graph = getBI().createGraphics();
    setStrokeColor(new Color(0,0,0));
    fill(new Color(255, 255, 255));
  }
  
  /**
   * Reloads the internal graphics. Used after a resize to realign the grahics module.
   */
  public void updateDrawing() {
    graph.dispose();
    BasicStroke s = (BasicStroke) graph.getStroke();
    graph = null;
    graph = getBI().createGraphics();
    graph.setStroke(s);
  }

  /**
   * Sets the drawing color.
   * 
   * @param color Color to draw with.
   */
  public void setStrokeColor(Color color) {
    graph.setColor(new java.awt.Color(color.getRed(), color.getGreen(), color
        .getBlue()));
  }

  /**
   * Change to use antialiasing.
   * 
   * @param bool boolean for anti-aliasing
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
   * @param size width of stroke
   */
  public void setStrokeSize(double size) {
    BasicStroke s = (BasicStroke) graph.getStroke();
    graph.setStroke(new BasicStroke((float)size, s.getEndCap(), s.getLineJoin(), s.getMiterLimit(), s.getDashArray(), s.getDashPhase()));
  }

  /**
   * Sets the dash pattern for the stroke with a float[].
   * 
   * @param dash the dash pattern
   */
  public void setStrokeDash(float[] dash) {
    BasicStroke s = (BasicStroke) graph.getStroke();
    graph.setStroke(new BasicStroke(s.getLineWidth(), s.getEndCap(), s.getLineJoin(), s.getMiterLimit(), dash, s.getDashPhase()));
  }
  
  /**
   * Sets the dash pattern for the stroke with a double[].
   * 
   * @param dash the dash pattern
   */
  public void setStrokeDash(double[] dash) {
    float[] dat = new float[dash.length];
    for(int i=0; i<dat.length; i++) {
      dat[i] = (float)dash[i];
    }
    setStrokeDash(dat);
  }

  /**
   * Sets the font to some system installed font.
   * 
   * @param name name of system font
   * @param size font size
   */
  public void setFont(String name, int size) {
    graph.setFont(new Font(name, Font.PLAIN, size));
  }

  /**
   * Sets the font based on a .ttf file.
   * 
   * @param fileName font file location
   * @param size font size
   */
  public void openFont(String fileName, int size) {
    try {
      graph.setFont(Font.createFont(Font.TRUETYPE_FONT, new File(fileName))
          .deriveFont(size));
    } catch (Exception e) {
      throw new RuntimeException("Problem loading font: " + fileName);
    }
  }
  
  /**
   * Scales Image based on scale ratio. Uses nearest neighbor scaling. Overide is to reset Graphics2D.
   * 
   * @param scale Scale of the new Image.
   */
  public void scale(double scale) {
    super.scale(scale);
    updateDrawing();
  }
  
  /**
   * Scales Image based on the scale ratio. Overide is to reset Graphics2D.
   * @param scale Scale of the new Image.
   * @param hint Hint as to what type of scaling to use.
   */
  public void scale(double scale, int hint) {
    super.scale(scale, hint);
    updateDrawing();
  }
  
  /**
   * Resizes Image based on new width and height. Uses nearest neighbor scaling. Overide is to reset Graphics2D.
   * 
   * @param width Width of the new Image.
   * @param height Height of the new Image.
   */
  public void resize(int width, int height) {
    super.resize(width, height);
    updateDrawing();
  }
  
  /**
   * Resizes Image based on a new Width and Height. Overide is to reset Graphics2D.
   * 
   * @param width Width of the new Image.
   * @param height Height of the new Image.
   * @param hint Hint as to what type of scaling to use.
   */
  public void resize(int width, int height, int hint) {
    super.resize(width, height, hint);
    updateDrawing();
  }

  /**
   * Draws a string.
   * 
   * @param str string to be drawn
   * @param x x position of string
   * @param y y position of string
   */
  public void drawString(String str, int x, int y) {
    graph.drawString(str, x, y);
  }

  /**
   * Draws a line.
   * 
   * @param x1 x coordinate of the first point
   * @param y1 y coordinate of the first point
   * @param x2 x coordinate of the second point
   * @param y2 y coordinate of the second point
   */
  public void drawLine(int x1, int y1, int x2, int y2) {
    graph.drawLine(x1, y1, x2, y2);
  }

  /**
   * Draws an ellipse.
   * 
   * @param x x coordinate of top left corner
   * @param y y coordinate of top left corner
   * @param width width of ellipse
   * @param height height of ellipse
   */
  public void drawEllipse(int x, int y, int width, int height) {
    graph.drawOval(x, y, width, height);
  }

  /**
   * Draws and fills an elipse.
   * 
   * @param x x coordinate of top left corner
   * @param y y coordinate of top left corner
   * @param width width of ellipse
   * @param height height of ellipse
   */
  public void fillEllipse(int x, int y, int width, int height) {
    graph.fillOval(x, y, width, height);
  }

  /**
   * Draws a rectangle.
   * 
   * @param x x coordinate of top left corner
   * @param y y coordinate of top left corner
   * @param width width of rectangle
   * @param height height of rectangle
   */
  public void drawRect(int x, int y, int width, int height) {
    graph.drawRect(x, y, width, height);
  }

  /**
   * Draws and fills a rectangle.
   * 
   * @param x x coordinate of top left corner
   * @param y y coordinate of top left corner
   * @param width width of rectangle
   * @param height height of rectangle
   */
  public void fillRect(int x, int y, int width, int height) {
    graph.fillRect(x, y, width, height);
  }

  /**
   * Draws a polygon with points from xPoints and yPoints. Will only use the
   * number of points in the smallest list.
   * 
   * @param xPoints array of x coordinates
   * @param yPoints array of y coordinates
   */
  public void drawPolygon(int[] xPoints, int[] yPoints) {
    graph.drawPolygon(xPoints, yPoints,
        Math.min(xPoints.length, yPoints.length));
  }

  /**
   * Draws and fills a polygon with points from xPoints and yPoints. Will only
   * use the number of points in the smallest list.
   * 
   * @param xPoints array of x coordinates
   * @param yPoints array of y coordinates
   */
  public void fillPolygon(int[] xPoints, int[] yPoints) {
    graph.fillPolygon(xPoints, yPoints,
        Math.min(xPoints.length, yPoints.length));
  }

  /**
   * Draws an elipse, but only draws from angle startAngle to endAngle.
   * 
   * @param x x coordinate of top left corner
   * @param y y coordinate of top left corner
   * @param width width of ellipse
   * @param height height of ellipse
   * @param startAngle angle in degrees to start drawing from
   * @param endAngle angle in degrees to stop drawing at
   */
  public void drawArc(int x, int y, int width, int height, int startAngle,
      int endAngle) {
    graph.drawArc(x, y, width, height, startAngle, endAngle - startAngle);
  }

  /**
   * Draws and fills an elipse, but only draws from angle startAngle to
   * endAngle.
   * 
   * @param x x coordinate of top left corner
   * @param y y coordinate of top left corner
   * @param width width of ellipse
   * @param height height of ellipse
   * @param startAngle angle in degrees to start drawing from
   * @param endAngle angle in degrees to stop drawing at
   */
  public void fillArc(int x, int y, int width, int height, int startAngle,
      int endAngle) {
    graph.fillArc(x, y, width, height, startAngle, endAngle - startAngle);
  }

  /**
   * Draws a rectangle, but cuts the corners off based on an arc.
   * 
   * @param x x coordinate of top left corner
   * @param y y coordinate of top left corner
   * @param width width of rectangle
   * @param height height of rectangle
   * @param arcWidth angle in degrees to start cutting from
   * @param arcHeight angle in degrees to stop cutting at
   */
  public void drawRoundRect(int x, int y, int width, int height, int arcWidth,
      int arcHeight) {
    graph.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
  }

  /**
   * Draws and fills a rectangle, but cuts the corners off based on an arc.
   * 
   * @param x x coordinate of top left corner
   * @param y y coordinate of top left corner
   * @param width width of rectangle
   * @param height height of rectangle
   * @param arcWidth angle in degrees to start cutting from
   * @param arcHeight angle in degrees to stop cutting at
   */
  public void fillRoundRect(int x, int y, int width, int height, int arcWidth,
      int arcHeight) {
    graph.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
  }
}
