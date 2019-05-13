package raycer;

public class Sphere {

	Vector centre;
	double radius;
	public RGBColour colour;

	public Sphere(Vector centre, double radius, RGBColour colour) {
		this.centre = centre;
		this.radius = radius;
		this.colour = colour;
	}

	public final String toString() {
		return String.format("<Sphere centre: %s, radius: %f>", centre.toString(), radius);
	}

	public final void print() {
		System.out.println(this.toString());
	}

	public double distanceAlongRay(Vector origin, Vector direction) {
		Vector originToCentre = this.centre.subtract(origin);

		double a = direction.magnitude();
		double b = 2.0 * originToCentre.dot(direction);
		double c = originToCentre.dot(originToCentre) - Math.pow(this.radius, 2);

		double discriminant = Math.pow(b, 2) - 4.0 * a * c;

		if (discriminant < 0) {
			return -1.0;
		}
		if (b + Math.sqrt(discriminant) < 0) {
			return -1.0;
		}
		return b - Math.sqrt(discriminant);
	}
}
