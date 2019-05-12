package raycer;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class BoyRaycer {
	
	static int width = 1024;
	static int height = 768;
	
	public static void main(String[] args) {
		render();
	}

	public static void render() {

		BufferedImage frameBuffer = createImage();
		saveImage(frameBuffer);
	}

	public static void saveImage(BufferedImage frameBuffer) {

		File imageFile = null;

		try {
			imageFile = new File("./test.png");
			ImageIO.write(frameBuffer, "png", imageFile);
		} catch (IOException e) {
			System.out.println(e);
		}
	}


	public static BufferedImage createImage() {
		return createImage(1024, 768);
	}

	public static BufferedImage createImage(int width, int height) {

		BufferedImage frameBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		for (int i = 0; i < width; i ++) {
			for (int j = 0; j < height; j ++) {
				int a = 255;
				int r = (256 * i) / width;
				int g = (256 * j) / height;
				int b = 0;
				int pixel = (a << 24) | (r << 16) | (g << 8) | b;
				frameBuffer.setRGB(i, j, pixel);
			}
		}

		return frameBuffer;
	}

}

