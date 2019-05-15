package raycer;

public class Sphere {

	public Vector centre;
	double radius;
	public Material material;

	public Sphere(Vector centre, double radius, Material material) {
		this.centre = centre;
		this.radius = radius;
		this.material = material;
	}

	public final String toString() {
		return String.format("<Sphere centre: %s, radius: %f>", centre.toString(), radius);
	}

	public final void print() {
		System.out.println(this.toString());
	}

	public double distanceAlongRay(Vector origin, Vector direction) {
		Vector originToCentre = origin.subtract(this.centre);

		double a = direction.magnitude();
		double b = 2.0 * originToCentre.dot(direction);
		double c = originToCentre.dot(originToCentre) - Math.pow(this.radius, 2);

		double discriminant = Math.pow(b, 2) - 4.0 * a * c;

		if (discriminant < 0) {
			return -1.0;
		}

		double numerator = -b - Math.sqrt(discriminant);
		if (numerator > 0.0) {
			return numerator / (2.0 * a);
		}
		
		numerator = -b + Math.sqrt(discriminant);
		if (numerator > 0.0) {
			return numerator / (2.0 * a);
		}
		return -1.0;
	}

	public Vector normalAt(Vector point) {
		return point.subtract(this.centre).normalise();
	}

	public RGBColour colourScaled(double intensity) {
		return this.material.colourScaled(intensity);
	}
}
