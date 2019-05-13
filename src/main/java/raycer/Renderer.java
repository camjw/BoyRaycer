package raycer;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Renderer {
	
	static int width = 1024;
	static int height = 768;

	static RGBColour BACKGROUND_COLOUR = new RGBColour(178, 236, 255);
	static RGBColour RED_SPHERE_COLOUR = new RGBColour(230, 15, 53);
	static RGBColour YELLOW_SPHERE_COLOUR = new RGBColour(30, 200, 200);
	
	public Renderer() {
		
	}

	public static void render() {
		Camera camera = new Camera(new Vector(0.0, 0.0, 0.0), Math.PI / 2.0);
		BufferedImage frameBuffer = createImage(camera);
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

	public static int traceRay(Vector origin, Vector direction, ArrayList<Sphere> renderedSpheres) {
		double distanceToCamera = 10000.0;
		RGBColour pixelColour = BACKGROUND_COLOUR;

		for (Sphere sphere: renderedSpheres) {
			double distanceToSphere = sphere.distanceAlongRay(origin, direction);
			if (distanceToSphere > 0 && distanceToSphere < distanceToCamera) {
				distanceToCamera = distanceToSphere;
				pixelColour = sphere.colour;
			}
		}
		return pixelColour.toInt();
	}


	public static BufferedImage createImage(Camera camera) {
		return createImage(camera, 1024, 768);
	}

	public static BufferedImage createImage(Camera camera, int width, int height) {
		BufferedImage frameBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		ArrayList<Sphere> renderedSpheres = new ArrayList<Sphere>(10);
		renderedSpheres.add(new Sphere(new Vector(0.0, 0.0, -10.0), 2.0, RED_SPHERE_COLOUR));	
		renderedSpheres.add(new Sphere(new Vector(3.0, 5.0, -10.0), 2.0, RED_SPHERE_COLOUR));	
		renderedSpheres.add(new Sphere(new Vector(-9.0, 9.0, -50.0), 9.0, YELLOW_SPHERE_COLOUR));	
		renderedSpheres.add(new Sphere(new Vector(-3.0, -3.0, -8.0), 2.0, YELLOW_SPHERE_COLOUR));	

		for (int i = 0; i < width; i ++) {
			for (int j = 0; j < height; j ++) {
				double x =  (2 * (i + 0.5) / (double) width  - 1.0) * Math.tan(camera.FOV / 2.0) * width / (double) height;
            			double y = -(2 * (j + 0.5) / (double) height - 1.0) * Math.tan(camera.FOV / 2.0);
            			Vector direction = new Vector(x, y, -1.0).normalise();
				int pixel = traceRay(camera.location, direction, renderedSpheres);
				frameBuffer.setRGB(i, j, pixel);
			}
		}

		return frameBuffer;
	}

}

