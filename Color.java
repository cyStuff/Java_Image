import java.util.function.*;

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
	 * @throws ColorException
	 */
	public Color(int[] color) throws ColorException {
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
	 *            - UnaryOperator<Integer>
	 * @return Color
	 */
	public Color setColor(UnaryOperator<int[]> operator) {
		if (!(operator.apply(dat).length == 3)) {
			throw new ColorException(
					"Color array must contatain exactly 3 elements.");
		}
		dat = operator.apply(dat);
		return this;
	}

	/**
	 * Sets rgb with the same UnaryOperator.
	 * 
	 * @param operator
	 *            - UnaryOperator<Integer>
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
	 *            - int
	 * @return Color
	 */
	public Color setRed(int color) {
		dat[0] = color;
		return this;
	}

	/**
	 * Sets the red value based on a UnaryOperator. This functions as a lambda
	 * expression.
	 * 
	 * @param operator
	 *            - UnaryOperator<Integer>
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
	 *            - int
	 * @return Color
	 */
	public Color setGreen(int color) {
		dat[1] = color;
		return this;
	}

	/**
	 * Sets the green value based on a UnaryOperator. This functions as a lambda
	 * expression.
	 * 
	 * @param operator
	 *            - UnaryOperator<Integer>
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
	 *            - int
	 * @return Color
	 */
	public Color setBlue(int color) {
		dat[2] = color;
		return this;
	}

	/**
	 * Sets the blue value based on a UnaryOperator. This functions as a lambda
	 * expression.
	 * 
	 * @param operator
	 *            - UnaryOperator<Integer>
	 * @return Color
	 */
	public Color setBlue(UnaryOperator<Integer> operator) {
		dat[2] = operator.apply(dat[2]);
		return this;
	}

	/**
	 * Returns the red color value.
	 */
	public int getRed() {
		return dat[0];
	}

	/**
	 * Returns the green color value.
	 */
	public int getGreen() {
		return dat[1];
	}

	/**
	 * Returns the blue color value.
	 */
	public int getBlue() {
		return dat[2];
	}

	/**
	 * Get the color values as an int[].
	 * 
	 * @return int[3]
	 */
	public int[] getArray() {
		return dat;
	}

	/**
	 * Set the color based on an int[].
	 * 
	 * @param color
	 *            - int[3]
	 */
	public void setArray(int[] color) {
		if (!(color.length == 3)) {
			throw new ColorException(
					"Color array must contatain exactly 3 elements.");
		}
		dat = color;
	}

	/**
	 * Exception for colors.
	 * 
	 * @author cy
	 */
	public class ColorException extends RuntimeException {
		public ColorException(String e) {
			super(e);
		}
	}
}
