package core;

import java.util.function.UnaryOperator;

/**
 * Color class to hold the RGB values of a color.
 * 
 * @author cy
 *
 */
public class Color {
	private int[] dat;

	/**
	 * Sets the color based on RGB int values.
	 * 
	 * @param red
	 *            - int
	 * @param green
	 *            - int
	 * @param blue
	 *            - int
	 */
	public Color(int red, int green, int blue) {
		dat = new int[] { red, green, blue };
	}

	/**
	 * Sets the color based on an int[].
	 * 
	 * @param color
	 *            - int[3]
	 */
	public Color(int[] color) {
		if (!(color.length == 3)) {
			throw new ColorException(
					"Color array must contatain exactly 3 elements.");
		}
		dat = color;
	}

	/**
	 * Sets a color based on a UnaryOperator.
	 * 
	 * @param operator
	 *            - UnaryOperator applied to the color
	 * @return Color
	 */
	public Color setColor(UnaryOperator<Color> operator) {
		int[] data = operator.apply(this).getArray();
		return new Color(data);
	}

	/**
	 * Sets all color channels with the same operator.
	 * 
	 * @param operator
	 *            - UnaryOperator applied to all channels seperately
	 * @return Color
	 */
	public Color setAllColor(UnaryOperator<Integer> operator) {
		dat[0] = operator.apply(dat[0]);
		dat[1] = operator.apply(dat[1]);
		dat[2] = operator.apply(dat[2]);
		return this;
	}

	/**
	 * Sets the red color from an integer value.
	 * 
	 * @param color
	 *            - int to define red channel
	 * @return Color
	 */
	public Color setRed(int color) {
		dat[0] = color;
		return this;
	}

	/**
	 * Sets the red value based on a UnaryOperator.
	 * 
	 * @param operator
	 *            - UnaryOperator to set the red channel
	 * @return Color
	 */
	public Color setRed(UnaryOperator<Integer> operator) {
		dat[0] = operator.apply(dat[0]);
		return this;
	}

	/**
	 * Sets the green color from an integer value.
	 * 
	 * @param color
	 *            - int to define green channel
	 * @return Color
	 */
	public Color setGreen(int color) {
		dat[1] = color;
		return this;
	}

	/**
	 * Sets the green value based on a UnaryOperator.
	 * 
	 * @param operator
	 *            - UnaryOperator to set the green channel
	 * @return Color
	 */
	public Color setGreen(UnaryOperator<Integer> operator) {
		dat[1] = operator.apply(dat[1]);
		return this;
	}

	/**
	 * Sets the blue color from an integer value.
	 * 
	 * @param color
	 *            - int to define blue channel
	 * @return Color
	 */
	public Color setBlue(int color) {
		dat[2] = color;
		return this;
	}

	/**
	 * Sets the blue value based on a UnaryOperator.
	 * 
	 * @param operator
	 *            - UnaryOperator to set the blue channel
	 * @return Color
	 */
	public Color setBlue(UnaryOperator<Integer> operator) {
		dat[2] = operator.apply(dat[2]);
		return this;
	}

	/**
	 * Returns the red color value.
	 * 
	 * @return the int value for red
	 */
	public int getRed() {
		return dat[0];
	}

	/**
	 * Returns the green color value.
	 * 
	 * @return the int value for green
	 */
	public int getGreen() {
		return dat[1];
	}

	/**
	 * Returns the blue color value.
	 * 
	 * @return the int value for blue
	 */
	public int getBlue() {
		return dat[2];
	}

	/**
	 * Get the color values as an int[].
	 * 
	 * @return int array with 3 values, one for each channel
	 */
	public int[] getArray() {
		return dat;
	}

	/**
	 * Set the color based on an int[].
	 * 
	 * @param color
	 *            - int array that must have exactly 3 values
	 */
	public void setArray(int[] color) {
		if (!(color.length == 3)) {
			throw new ColorException(
					"Color array must contatain exactly 3 elements.");
		}
		dat = color;
	}

	/**
	 * Tests if two colors are equivalent.
	 * 
	 * @param color
	 *            - Color being tested.
	 * @return boolean telling of equivalent
	 */
	public boolean equals(Color color) {
		if (this.getRed() == color.getRed()
				&& this.getGreen() == color.getGreen()
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
