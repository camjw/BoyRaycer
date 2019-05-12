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

		BufferedImage framebuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		File imageFile = null;

		for (int i = 0; i < width; i ++) {
			for (int j = 0; j < height; j ++) {
				int a = 255;
				int r = (256 * i) / width;
				int g = (256 * j) / height;
				int b = 0;
				int pixel = (a << 24) | (r << 16) | (g << 8) | b;
				framebuffer.setRGB(i, j, pixel);
			}
		}

		try {
			imageFile = new File("./test.png");
			ImageIO.write(framebuffer, "png", imageFile);
		} catch (IOException e) {
			System.out.println(e);
		}

	}
}

