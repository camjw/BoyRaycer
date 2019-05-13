package raycer;

import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class Renderer {
	
	static int width = 1024;
	static int height = 768;

	static RGBColour BACKGROUND_COLOUR = new RGBColour(178, 236, 255);
	static RGBColour RED_SPHERE_COLOUR = new RGBColour(230, 15, 53);
	static RGBColour YELLOW_SPHERE_COLOUR = new RGBColour(30, 200, 200);
	
	public static BufferedImage render() {
		Camera camera = new Camera(new Vector(0.0, 0.0, 0.0), Math.PI / 2.0);
		return createImage(camera);
	}

	public static int traceRay(Vector origin, Vector direction, ArrayList<Sphere> renderedSpheres, ArrayList<Light> lights) {
		double distanceToCamera = 10000.0;
		RGBColour pixelColour = BACKGROUND_COLOUR;

		for (Sphere sphere: renderedSpheres) {
			double distanceToSphere = sphere.distanceAlongRay(origin, direction);
			if (distanceToSphere > 0 && distanceToSphere < distanceToCamera) {
				distanceToCamera = distanceToSphere;
				Vector intersectionPoint = origin.add(direction.scale(distanceToSphere));
				pixelColour = calculateLightIntensity(sphere, lights, intersectionPoint);
			}
		}
		return pixelColour.toInt();
	}

	public static RGBColour calculateLightIntensity(Sphere sphere, ArrayList<Light> lights, Vector intersectionPoint) {
		double totalIntensity = 0.0;
		for (Light light: lights) {
			Vector lightToSphere = light.location.subtract(sphere.centre);
			Vector lightDirection = lightToSphere.normalise();
			totalIntensity += light.intensity * Math.max(0.0, lightDirection.dot(sphere.normalAt(intersectionPoint)));
		}
		return sphere.colour.scale(totalIntensity);
	}



	public static BufferedImage createImage(Camera camera) {
		return createImage(camera, 1024, 768);
	}

	public static BufferedImage createImage(Camera camera, int width, int height) {
		BufferedImage frameBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		ArrayList<Sphere> renderedSpheres = new ArrayList<Sphere>();
		renderedSpheres.add(new Sphere(new Vector(-3.0, 0.0, -16.0), 2.0, RED_SPHERE_COLOUR));	
		renderedSpheres.add(new Sphere(new Vector(-1.0, -1.5, -12.0), 2.0, RED_SPHERE_COLOUR));	
		renderedSpheres.add(new Sphere(new Vector(1.5, -1.5, -18.0), 3.0, YELLOW_SPHERE_COLOUR));	
		renderedSpheres.add(new Sphere(new Vector(7.0, 5.0, -18.0), 4.0, YELLOW_SPHERE_COLOUR));	

		ArrayList<Light> lights = new ArrayList<Light>();
		lights.add(new Light(new Vector(-20.0, 20.0, 20.0), 1.5));

		for (int i = 0; i < width; i ++) {
			for (int j = 0; j < height; j ++) {
				double x =  (2 * (i + 0.5) / (double) width  - 1.0) * Math.tan(camera.FOV / 2.0) * width / (double) height;
            			double y = -(2 * (j + 0.5) / (double) height - 1.0) * Math.tan(camera.FOV / 2.0);
            			Vector direction = new Vector(x, y, -1.0).normalise();
				int pixel = traceRay(camera.location, direction, renderedSpheres, lights);
				frameBuffer.setRGB(i, j, pixel);
			}
		}

		return frameBuffer;
	}

}

