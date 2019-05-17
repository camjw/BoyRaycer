package raycer;

import java.util.ArrayList;
import java.util.Random;
import java.awt.image.BufferedImage;

public class Renderer {
	
	static int width = 8 * (int) Math.pow(2, 10);
	static int height = 6 * (int) Math.pow(2, 10);

	static RGBColour BACKGROUND_COLOUR = new RGBColour(240, 240, 255);

	static Material RED_SPHERE_MATERIAL = new Material(new RGBColour(230, 15, 53), 0.4, 0.3, 10);
	static Material YELLOW_SPHERE_MATERIAL = new Material(new RGBColour(255, 255, 102), 0.5, 0.1, 50);

	static Camera camera = new Camera(new Vector(0.0, 0.0, 0.0), Math.PI / 2.0);
	
	public BufferedImage render() {
		return createImage(camera);
	}

	public int traceRay(Vector origin, Vector direction, ArrayList<Sphere> renderedSpheres, ArrayList<Light> lights) {
		double distanceToCamera = 10000.0;
		RGBColour pixelColour = BACKGROUND_COLOUR;

		for (Sphere sphere: renderedSpheres) {
			double distanceToSphere = sphere.distanceAlongRay(origin, direction);
			if (distanceToSphere > 0 && distanceToSphere < distanceToCamera) {
				distanceToCamera = distanceToSphere;
				Vector intersectionPoint = origin.add(direction.scale(distanceToSphere));
				pixelColour = calculateLightIntensity(sphere, direction, lights, intersectionPoint, renderedSpheres);
			}
		}
		return pixelColour.toInt();
	}

	public boolean notInShadow(Vector lightOrigin, Vector lightDirection, Sphere targetSphere, ArrayList<Sphere> renderedSpheres) {
		double distanceToTarget = targetSphere.distanceAlongRay(lightOrigin, lightDirection);

		for (Sphere sphere: renderedSpheres) {
			double distanceToLight = sphere.distanceAlongRay(lightOrigin, lightDirection);
			if (distanceToLight < distanceToTarget && distanceToLight > 0) {
				return false;
			}
		}
		return true;
	}

	public double calculateDiffuseIntensity(Light light, Vector lightDirection, Vector sphereNormal) {
		return light.intensity * Math.max(0.0, -lightDirection.dot(sphereNormal));
	}

	public double calculateSpecularIntensity(Light light, Vector lightDirection, Vector sphereNormal, Sphere sphere, Vector direction) {
		double specularPower = -lightDirection.reflect(sphereNormal).dot(direction);
		return Math.pow(Math.max(0.0, specularPower), sphere.material.specularExponent) * light.intensity;
	}

	public RGBColour calculateLightIntensity(Sphere sphere, Vector direction, ArrayList<Light> lights, Vector intersectionPoint, ArrayList<Sphere> renderedSpheres) {
		double diffuseIntensity = 0.0;
		double specularIntensity = 0.0;
		for (Light light: lights) {
			Vector lightToSphere = sphere.centre.subtract(light.location);
			Vector lightDirection = lightToSphere.normalise();
			Vector sphereNormal = sphere.normalAt(intersectionPoint);

			boolean isVisible = notInShadow(light.location, lightDirection, sphere, renderedSpheres);

			if (isVisible) {
				diffuseIntensity += calculateDiffuseIntensity(light, lightDirection, sphereNormal);
				specularIntensity += calculateSpecularIntensity(light, lightDirection, sphereNormal, sphere, direction);
			} 
		}
		return sphere.colourScaled(diffuseIntensity, specularIntensity);
	}



	public BufferedImage createImage(Camera camera) {
		return createImage(camera, width, height);
	}

	public Vector calculateCameraDirection(int i, int j) {
		double x =  (2 * (i + 0.5) / (double) width  - 1.0) * Math.tan(camera.FOV / 2.0) * width / (double) height;
            	double y = -(2 * (j + 0.5) / (double) height - 1.0) * Math.tan(camera.FOV / 2.0);
            	return new Vector(x, y, -1.0).normalise();
	}

	public static ArrayList<Sphere> generateSpheres(int numSpheres) {
		ArrayList<Sphere> renderedSpheres = new ArrayList<Sphere>();
		for (int i = 0; i < numSpheres; i ++) {
				Random randomGenerator = new Random();
				int x  = randomGenerator.nextInt(50) - 25;
				int y  = randomGenerator.nextInt(50) - 25;
				int z  = - randomGenerator.nextInt(25) - 25;
				int r  = randomGenerator.nextInt(3) + 1;
				int m  = randomGenerator.nextInt(2);
				renderedSpheres.add(new Sphere(new Vector(x, y, z), r, m == 1 ? RED_SPHERE_MATERIAL : YELLOW_SPHERE_MATERIAL));	
		}
		return renderedSpheres;
	}

	public BufferedImage createImage(Camera camera, int width, int height) {
		BufferedImage frameBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		ArrayList<Sphere> renderedSpheres = generateSpheres(100);

		ArrayList<Light> lights = new ArrayList<Light>();
		lights.add(new Light(new Vector(-20.0, 20.0, 20.0), 1.5));
		lights.add(new Light(new Vector(30.0, 50.0, -25.0), 1.8));
		lights.add(new Light(new Vector(30.0, 20.0,  30.0), 1.7));

		for (int i = 0; i < width; i ++) {
			for (int j = 0; j < height; j ++) {
            			Vector direction = calculateCameraDirection(i, j);
				int pixel = traceRay(camera.location, direction, renderedSpheres, lights);
				frameBuffer.setRGB(i, j, pixel);
			}
		}

		return frameBuffer;
	}

}

