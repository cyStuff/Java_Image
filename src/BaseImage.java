package src;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Basic image editing tool.
 * 
 * @author cy
 *
 */
public class BaseImage {
	private BufferedImage im;

	/**
	 * Opens an image from a file source.
	 * 
	 * @param source
	 *            - Source location of the image file.
	 */
	public BaseImage(String source){
		try {
			im = ImageIO.read(new File(source));
		}
		catch(IOException e) {
			throw new RuntimeException("Can Not Find Source File: " + source);
		}
	}

	/**
	 * creates a black image with width and height.
	 * 
	 * @param width
	 *            - Width of the image.
	 * @param height
	 *            - Height of the image.
	 */
	public BaseImage(int width, int height) {
		im = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}

	/**
	 * Creates an image from a BufferedImage in the java.awt.image library.
	 * 
	 * @param bufferedImage
	 *            - Raw BufferedImage to create Image from.
	 */
	protected BaseImage(BufferedImage bufferedImage) {
		im = bufferedImage;
	}

	/**
	 * Clones the Image to a new Image object with the same data.
	 */
	public BaseImage clone() {
		BaseImage i = new BaseImage(width(), height());
		i.setSection(0, 0, this);
		return i;
	}

	public boolean equals(BaseImage image) {
		if (image.width() != width() || image.height() != height()) {
			return false;
		}
		for (int i = 0; i < width(); i++) {
			for (int j = 0; j < height(); j++) {
				if (!(getPixel(i, j).equals(image.getPixel(i, j)))) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Returns the internal BufferedImage.
	 * 
	 * @return BufferedImage contained within the Image.
	 */
	protected BufferedImage getBI() {
		return im;
	}

	/**
	 * Returns the color value at the given coordinates as an int[].
	 * 
	 * @param x
	 *            - X value of the pixel.
	 * @param y
	 *            - Y value of the pixel.
	 * @return Color of the pixel.
	 */
	public Color getPixel(int x, int y) {
		return new Color(fromInt(im.getRGB(x, y)));
	}

	/**
	 * Sets the color at position x and y to color.
	 * 
	 * @param x
	 *            - X position of the pixel.
	 * @param y
	 *            - Y position of the pixel.
	 * @param color
	 *            - Color the pixel will be set to.
	 */
	public void setPixel(int x, int y, Color color) {
		im.setRGB(x, y, toInt(color.getArray()));
	}

	/**
	 * Returns a subsection of the image. May throw an out of bounds exception
	 * if the section is not within the image.
	 * 
	 * @param x
	 *            - Starting x position of the sub-image.
	 * @param y
	 *            - Starting y position of the sub-image.
	 * @param width
	 *            - Width of the sub-image.
	 * @param height
	 *            - Height of the sub-image.
	 * @return Image which is a smaller part of the original.
	 */
	public BaseImage subSection(int x, int y, int width, int height) {
		return new BaseImage(im.getSubimage(x, y, width, height));
	}

	/**
	 * Set a specific section to an Image. May throw an out of bounds exception
	 * if the section is not within the image.
	 * 
	 * @param x
	 *            - Left-most x coordinate for the sub-image.
	 * @param y
	 *            - Upper y coordinate for the sub-image.
	 * @param image
	 *            - Image to be set.
	 */
	public void setSection(int x, int y, BaseImage image) {
		BufferedImage bi = image.getBI();
		int[] dat = bi.getRGB(0, 0, image.width(), image.height(), null, 0,
				Math.max(image.height(), image.width()));
		// System.out.println(dat.length);
		im.setRGB(x, y, image.width(), image.height(), dat, 0,
				Math.max(image.height(), image.width()));
	}


	/**
	 * Fills the image with color.
	 * 
	 * @param color
	 *            - Color for the image to be filled with.
	 */
	public void fill(Color color) {
		for(int x=0; x<width(); x++) {
			for (int y=0; y<height(); y++) {
				setPixel(x, y, color);
			}
		}
	}
	
	/**
	 * Sets the current image to a different image.
	 * 
	 * @param image - image to set current image to
	 */
	public void setImage(Image image) {
		im = image.getBI();
	}

	/**
	 * Returns the width of the image.
	 * 
	 * @return int defining the image width
	 */
	public int width() {
		return im.getWidth();
	}

	/**
	 * Returns the height of the image.
	 * 
	 * @return int defining the image height
	 */
	public int height() {
		return im.getHeight();
	}

	/**
	 * Conversion for BufferedImage
	 * 
	 * @param n
	 * @return int[] from the pixel color.
	 */
	private int[] fromInt(int n) {
		n = (n + (256 * 256 * 256)) % (256 * 256 * 256);
		return new int[] { n / (256 * 256), (n / 256) % 256, n % 256 };
	}

	/**
	 * Conversion for BufferedImage
	 * 
	 * @param n
	 * @return int from the int[].
	 */
	private int toInt(int[] n) {
		return n[0] * 256 * 256 + n[1] * 256 + n[2];
	}

	/**
	 * Saves the Image as the type defined in the extension.
	 * 
	 * @param fileName
	 *            - Name of the file to save. Must contain extension.
	 */
	public void save(String fileName){
		try {
		ImageIO.write(im, fileName.substring(fileName.lastIndexOf('.') + 1),
				new File(fileName));
		}
		catch (IOException e) {
			throw new RuntimeException("Can Not Save File: " + fileName);
		}
	}
}
