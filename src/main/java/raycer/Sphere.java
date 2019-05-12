package raycer;

public class Sphere {

	Vector centre;
	double radius;

	public Sphere(Vector centre, double radius) {
		this.centre = centre;
		this.radius = radius;
	}

	public final String toString() {
		return String.format("<Sphere centre: %s, radius: %f>", centre.toString(), radius);
	}

	public final void print() {
		System.out.println(this.toString());
	}

	public boolean intersectsRay(Vector origin, Vector direction) {
		Vector originToCentre = this.centre.subtract(origin);

		double a = direction.dot(direction);
		double b = 2.0 * originToCentre.dot(direction);
		double c = originToCentre.dot(originToCentre) - Math.pow(this.radius, 2);

		double discriminant = Math.pow(b, 2) - 4 * a * c;

		if (discriminant < 0) {
			return false;
		} else if (-b + Math.sqrt(discriminant) < 0) {
			return false;
		}
		return true;
	}
}
