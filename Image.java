import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;

public class Image{
  private BufferedImage im;
  public static String JPG = "jpg";
  public static String PNG = "png";
  public static String BMP = "bmp";
  // reads a file at location fn
  public Image(String fn) throws IOException{
    im = ImageIO.read(new File(fn));
  }
  // creates a black image with width and height of w and h
  public Image(int w, int h) {
    im = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
  }
  private Image(BufferedImage i) {
    im = i;
  }
  private BufferedImage getBI() {
    return im;
  }
  // gets the color value at x, y
  public int[] getPixel(int x, int y) {
    return fromInt(im.getRGB(x,y));
  }
  // sets pixel to color c at position x, y
  public void setPixel(int x, int y, int[] c) {
    im.setRGB(x,y,toInt(c));
  }
  public Image GetSection(int x, int y, int w, int h) {
    return new Image(im.getSubimage(x,y,w,h));
  }
  public void setSection(int x, int y, Image i) {
    BufferedImage bi = i.getBI();
    int[] dat = bi.getRGB(0,0,bi.getWidth(),bi.getHeight(),null,0,bi.getHeight());
    //System.out.println(dat.length);
    im.setRGB(x,y,i.width(),i.height(),dat,0,bi.getHeight());
  }
  public int width() {
    return im.getWidth();
  }
  public int height() {
    return im.getHeight();
  }
  //fills an area based on color c
  public void fill(int[] c) {
    for (int i=0; i<im.getWidth(); i++) {
      for (int j=0; j<im.getHeight(); j++) {
        setPixel(i,j,c);
      }
    }
  }
  private int[] fromInt(int n) {
    n = (n+(256*256*256))%(256*256*256);
    return new int[] {n/(256*256), (n/256)%256, n%256};
  }
  private int toInt(int[] n) {
    return n[0]*256*256+n[1]*256+n[2];
  }
  // saves based on file name fn
  public void save(String fn) throws IOException{
    ImageIO.write(im, "png", new File(fn));
  }
  // use string type to define what type of file type.
  public void save(String fn, String type) throws IOException{
    ImageIO.write(im, type, new File(fn));
  }
}