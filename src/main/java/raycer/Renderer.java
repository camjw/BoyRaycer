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

	static RGBColour BACKGROUND_COLOUR = new RGBColour(178, 236, 255);
	static RGBColour SPHERE_COLOUR = new RGBColour(230, 15, 53);
	
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
			return SPHERE_COLOUR.toPixel();
		}
		return BACKGROUND_COLOUR.toPixel();
	}


	public static BufferedImage createImage(Vector lightSource) {
		return createImage(lightSource, 1024, 768);
	}

	public static BufferedImage createImage(Vector lightSource, int width, int height) {
		BufferedImage frameBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		Sphere renderedSphere = new Sphere(new Vector(0.0, 0.0, -10.0), 2.0);
		
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

