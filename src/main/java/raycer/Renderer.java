package raycer;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Renderer {
	
	static int width = 1024;
	static int height = 768;
	static double FOV = Math.PI / 2.0;

	static int BACKGROUND_COLOUR = (255 << 24) | (178 << 16) | (236 << 8) | 255;
	static int SPHERE_COLOUR = (255 << 24) | (230 << 16) | (15 << 8) | 53;
	
	public Renderer() {
		
	}

	public static void render() {
		Vector origin = new Vector(0.0, 0.0, 0.0);
		BufferedImage frameBuffer = createImage(origin);
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

	public static int colourPixel(Vector origin, Vector direction, Sphere sphere) {
		if (sphere.intersectsRay(origin, direction)) {
			return SPHERE_COLOUR;
		}
		return BACKGROUND_COLOUR;
	}


	public static BufferedImage createImage(Vector lightSource) {
		return createImage(lightSource, 1024, 768);
	}

	public static BufferedImage createImage(Vector lightSource, int width, int height) {
		BufferedImage frameBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		Sphere renderedSphere = new Sphere(new Vector(3.0, 0.0, 16.0), 20.0);
		
		for (int i = 0; i < width; i ++) {
			for (int j = 0; j < height; j ++) {
				double x =  (2 * (i + 0.5) / (double) width  - 1.0) * Math.tan(FOV / 2.0) * width / (double) height;
            			double y = -(2 * (j + 0.5) / (double) height - 1.0) * Math.tan(FOV / 2.0);
            			Vector direction = new Vector(x, y, -1.0).normalise();
				int pixel = colourPixel(lightSource, direction, renderedSphere);
				frameBuffer.setRGB(i, j, pixel);
			}
		}

		return frameBuffer;
	}

}

