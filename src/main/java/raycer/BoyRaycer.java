package raycer;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class BoyRaycer {
	
	public static void main(String[] args) {
		Renderer renderer = new Renderer();
		BufferedImage image = renderer.render();
		saveImage(image);
	}

	public static void saveImage(BufferedImage image) {
		File imageFile = null;

		try {
			imageFile = new File("./test.png");
			ImageIO.write(image, "png", imageFile);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}

