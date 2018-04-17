

public class test {

	public static void main(String[] args) {
		Image[] im = new Image[16];
		for (int i=0; i<16; i++) {
			im[i] = new Image(32,32);
			im[i].fill(new Color(i*4,i*4,i*4));
		}
		Gif.makeGif(im,10,"test.gif");
	}

}
