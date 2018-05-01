package core;

import java.io.IOException;
import java.util.function.UnaryOperator;

/**
 * Image class for editing images. For any bugs or questions, please visit
 * https://github.com/peanut15/Java_Image/issues.
 * 
 * @author cy
 *
 */
public class Image extends BaseImage {
	public static int SCALE_NEAREST = 0;
	public static int SCALE_BILENEAR = 1;

	/**
	 * Opens an image from a file source.
	 * 
	 * @param source
	 *            - Source location of the image file.
	 * @throws IOException
	 */
	public Image(String source) throws IOException {
		super(source);
	}

	/**
	 * creates a black image with width and height.
	 * 
	 * @param width
	 *            - Width of the image.
	 * @param height
	 *            - Height of the image.
	 */
	public Image(int width, int height) {
		super(width, height);
	}
	
	
	public Image clone() {
		Image i = new Image(width(), height());
		i.setSection(0, 0, this);
		return i;
	}

	/**
	 * Sets the entire red channel to the given color.
	 * 
	 * @param color
	 *            - Int to set all the red channel to.
	 */
	public void setRedChannel(int color) {
		for (int i = 0; i < width(); i++) {
			for (int j = 0; j < height(); j++) {
				setPixel(i, j, getPixel(i, j).setRed(color));
			}
		}
	}

	/**
	 * Uses a lambda expression to set the chanel. Use the form setRedChannel(x
	 * -> x);
	 * 
	 * @param operator
	 *            - The UnaryOperator to set the entire channel.
	 */
	public void setRedChannel(UnaryOperator<Integer> operator) {
		for (int i = 0; i < width(); i++) {
			for (int j = 0; j < height(); j++) {
				setPixel(i, j, getPixel(i, j).setRed(operator));
			}
		}
	}

	/**
	 * Sets the entire green channel to the given color.
	 * 
	 * @param color
	 *            - Int to set all the green channel to.
	 */
	public void setGreenChannel(int color) {
		for (int i = 0; i < width(); i++) {
			for (int j = 0; j < height(); j++) {
				setPixel(i, j, getPixel(i, j).setGreen(color));
			}
		}
	}

	/**
	 * Uses a lambda expression to set the chanel. Use the form
	 * setGreenChannel(x -> x);
	 * 
	 * @param operator
	 *            - The UnaryOperator to set the entire channel.
	 */
	public void setGreenChannel(UnaryOperator<Integer> operator) {
		for (int i = 0; i < width(); i++) {
			for (int j = 0; j < height(); j++) {
				setPixel(i, j, getPixel(i, j).setGreen(operator));
			}
		}
	}

	/**
	 * Sets the entire blue channel to the given color.
	 * 
	 * @param color
	 *            - Int to set all the blue channel to.
	 */
	public void setBlueChannel(int color) {
		for (int i = 0; i < width(); i++) {
			for (int j = 0; j < height(); j++) {
				setPixel(i, j, getPixel(i, j).setBlue(color));
			}
		}
	}

	/**
	 * Uses a lambda expression to set the chanel. Use the form setBlueChannel(x
	 * -> x);
	 * 
	 * @param operator
	 *            - The UnaryOperator to set the entire channel.
	 */
	public void setBlueChannel(UnaryOperator<Integer> operator) {
		for (int i = 0; i < width(); i++) {
			for (int j = 0; j < height(); j++) {
				setPixel(i, j, getPixel(i, j).setBlue(operator));
			}
		}
	}

	/**
	 * Uses a lambda expression to set all channels.
	 * 
	 * @param operator
	 *            - The UnaryOperator to set all of the channels.
	 */
	public void setChannels(UnaryOperator<Color> operator) {
		for (int i = 0; i < width(); i++) {
			for (int j = 0; j < height(); j++) {
				setPixel(i, j, getPixel(i, j).setColor(operator));
			}
		}
	}

	/**
	 * Uses a BinaryOperator to set the color of each pixel based on the color
	 * and location of that pixel.
	 * 
	 * @param operator
	 *            - The BinaryOperator used to set the color at each pixel of
	 *            the image.
	 */
	public void setChannelsAtPixel(UnaryOperator<int[]> operator) {
		for (int i = 0; i < width(); i++) {
			for (int j = 0; j < height(); j++) {
				final int x = i;
				final int y = j;
				setPixel(i, j, new Color(operator.apply(new int[] { x, y })));
			}
		}
	}

	/**
	 * Uses a lambda expression to set all channels.
	 * 
	 * @param operator
	 *            - The UnaryOperator to set all of the channels.
	 */
	public void setAllChannels(UnaryOperator<Integer> operator) {
		for (int i = 0; i < width(); i++) {
			for (int j = 0; j < height(); j++) {
				setPixel(i, j, getPixel(i, j).setAllColor(operator));
			}
		}
	}

	/**
	 * Scales Image based on a new Width and Height.
	 * 
	 * @param width
	 *            - Width of the new Image.
	 * @param height
	 *            - Height of the new Image.
	 * @param hint
	 *            - Hint as to what type of scaling to use.
	 */
	public void scale(int width, int height, int hint) {
		switch (hint) {
		case (0):
			nearest(width, height);
			break;
		case (1):
			bilinear(width, height);
			break;
		}
	}

	/**
	 * Scales Image based on the scale ratio.
	 * 
	 * @param scale
	 *            - Scale of the new Image.
	 * @param hint
	 *            - Hint as to what type of scaling to use.
	 */
	public void scale(double scale, int hint) {
		scale((int) (width() * scale), (int) (height() * scale), hint);
	}

	/**
	 * Scales Image based on new width and height. Uses nearest neighbor
	 * scaling.
	 * 
	 * @param width
	 *            - Width of the new Image.
	 * @param height
	 *            - Height of the new Image.
	 */
	public void scale(int width, int height) {
		nearest(width, height);
	}

	/**
	 * Scales Image based on scale ratio. Uses nearest neighbor scaling.
	 * 
	 * @param scale
	 *            - Scale of the new Image.
	 */
	public void scale(double scale) {
		scale((int) (width() * scale), (int) (height() * scale));
	}

	/**
	 * Nearest neighbor scaling for Images.
	 * 
	 * @param width
	 *            - Width of the new Image.
	 * @param height
	 *            - Height of the new Image.
	 */
	private void nearest(int width, int height) {
		Image P2 = new Image(width, height);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int[] c = new int[3];
				for (int k = 0; k < c.length; k++) {
					double I = (double) width() * i / width, J = (double) height()
							* j / height;
					c[k] = getPixel((int) Math.min((int)(I), width() - 1),
							(int) Math.min((int)(J), height() - 1))
							.getArray()[k];
				}
				P2.setPixel(i, j, new Color(c));
			}
		}
		setImage(P2);
	}

	/**
	 * BiLinear scaling for Images.
	 * 
	 * @param width
	 *            - Width of the new Image.
	 * @param height
	 *            - Height of the new Image.
	 */
	private void bilinear(int width, int height) {
		Image P2 = new Image(width, height);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int[] c = new int[3];
				for (int k = 0; k < c.length; k++) {
					double I = (double) width() * i / width, J = (double) height()
							* j / height;
					c[k] = (int) (((1 - I % 1) * (1 - J % 1))
							* getPixel((int) I, (int) J).getArray()[k]
							+ ((I % 1) * (J % 1))
							* getPixel(Math.min((int) I + 1, width() - 1),
									Math.min((int) J + 1, height() - 1))
									.getArray()[k]
							+ ((1 - I % 1) * (J % 1))
							* getPixel((int) I,
									Math.min((int) J + 1, height() - 1))
									.getArray()[k] + ((I % 1) * (1 - J % 1))
							* getPixel(Math.min((int) I + 1, width() - 1),
									(int) J).getArray()[k]);
				}
				P2.setPixel(i, j, new Color(c));
			}
		}
		setImage(P2);
	}

	/**
	 * Fills the image with color.
	 * 
	 * @param color
	 *            - Color for the image to be filled with.
	 */
	public void fill(Color color) {
		setChannels(none -> color);
	}
}
