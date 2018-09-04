package jImage;

import java.util.function.UnaryOperator;

/**
 * Color class to hold the RGB values of a color.
 * 
 * @author Cy
 *
 */
public final class Color {
  private final int r;
  private final int g;
  private final int b;

  /**
   * Sets the color based on RGB int values.
   * 
   * @param red int describing red channel
   * @param green int describing green channel
   * @param blue int describing blue channel
   */
  public Color(int red, int green, int blue) {
    r = red;
    g = green;
    b = blue;
  }

  /**
   * Sets the color based on an int[].
   * 
   * @param color int array that contains exactly 3 ints
   */
  public Color(int[] color) {
    if (!(color.length == 3)) {
      throw new ColorException("Color array must contatain exactly 3 elements.");
    }
    r = color[0];
    g = color[1];
    b = color[2];
  }

  /**
   * Sets a color based on a UnaryOperator.
   * 
   * @param operator UnaryOperator applied to the color
   * @return new color after the change
   */
  public Color setColor(UnaryOperator<Color> operator) {
    int[] data = operator.apply(this).getArray();
    return new Color(data);
  }

  /**
   * Sets all color channels with the same operator.
   * 
   * @param operator UnaryOperator applied to all channels seperately
   * @return new color after the change
   */
  public Color setAllColor(UnaryOperator<Integer> operator) {
    return new Color(operator.apply(r), operator.apply(g), operator.apply(b));
  }

  /**
   * Sets the red color from an integer value.
   * 
   * @param color int to define red channel
   * @return new color after the change
   */
  public Color setRed(int color) {
    return new Color(color, g, b);
  }

  /**
   * Sets the red value based on a UnaryOperator.
   * 
   * @param operator UnaryOperator to set the red channel
   * @return new color after the change
   */
  public Color setRed(UnaryOperator<Integer> operator) {
    return new Color(operator.apply(r),g,b);
  }

  /**
   * Sets the green color from an integer value.
   * 
   * @param color int to define green channel
   * @return new color after the change
   */
  public Color setGreen(int color) {
    return new Color(r, color, b);
  }

  /**
   * Sets the green value based on a UnaryOperator.
   * 
   * @param operator UnaryOperator to set the green channel
   * @return new color after the change
   */
  public Color setGreen(UnaryOperator<Integer> operator) {
    return new Color(r,operator.apply(g),b);
  }

  /**
   * Sets the blue color from an integer value.
   * 
   * @param color int to define blue channel
   * @return new color after the change
   */
  public Color setBlue(int color) {
    return new Color(r, g, color);
  }

  /**
   * Sets the blue value based on a UnaryOperator.
   * 
   * @param operator UnaryOperator to set the blue channel
   * @return new color after the change
   */
  public Color setBlue(UnaryOperator<Integer> operator) {
    return new Color(r,g,operator.apply(b));
  }

  /**
   * Returns the red color value.
   * 
   * @return the int value for red
   */
  public int getRed() {
    return r;
  }

  /**
   * Returns the green color value.
   * 
   * @return the int value for green
   */
  public int getGreen() {
    return g;
  }

  /**
   * Returns the blue color value.
   * 
   * @return the int value for blue
   */
  public int getBlue() {
    return b;
  }

  /**
   * Get the color values as an int[].
   * 
   * @return int array with 3 values, one for each channel
   */
  public int[] getArray() {
    return new int[] {r,g,b};
  }

  /**
   * Tests if two colors are equivalent.
   * 
   * @param color Color being tested.
   * @return boolean telling of equivalent
   */
  public boolean equals(Color color) {
    if (this.getRed() == color.getRed() && this.getGreen() == color.getGreen()
        && this.getBlue() == color.getBlue()) {
      return true;
    }
    return false;
  }

  /**
   * Exception for colors.
   * 
   * @author cy
   */
  @SuppressWarnings("serial")
  public class ColorException extends RuntimeException {
    public ColorException(String e) {
      super(e);
    }
  }
}
