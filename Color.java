
import java.util.function.*;
/**
 * Color class to hold the RGB values of a color.
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
		if (!(dat.length == 3)) {
			throw new ColorException(
					"Color array must contatain exactly 3 elements.");
		}
		dat = color;
	}

	/**
	 * Sets the red color from an integer value.
	 * 
	 * @param color
	 *            - int
	 */
	public void setRed(int color) {
		dat[0] = color;
	}

	/**
	 * Sets the red value based on a UnaryOperator. This functions as a lambda
	 * expression.
	 * 
	 * @param operator
	 *            - UnaryOperator<Integer>
	 */
	public void setRed(UnaryOperator<Integer> operator) {
		dat[0] = operator.apply(dat[0]);
	}

	/**
	 * Sets the green color from an integer value.
	 * 
	 * @param color
	 *            - int
	 */
	public void setGreen(int color) {
		dat[1] = color;
	}

	/**
	 * Sets the green value based on a UnaryOperator. This functions as a lambda
	 * expression.
	 * 
	 * @param operator
	 *            - UnaryOperator<Integer>
	 */
	public void setGreen(UnaryOperator<Integer> operator) {
		dat[1] = operator.apply(dat[1]);
	}

	/**
	 * Sets the blue color from an integer value.
	 * 
	 * @param color
	 *            - int
	 */
	public void setBlue(int color) {
		dat[2] = color;
	}

	/**
	 * Sets the blue value based on a UnaryOperator. This functions as a lambda
	 * expression.
	 * 
	 * @param operator
	 *            - UnaryOperator<Integer>
	 */
	public void setBlue(UnaryOperator<Integer> operator) {
		dat[2] = operator.apply(dat[2]);
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
		if (!(dat.length == 3)) {
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
