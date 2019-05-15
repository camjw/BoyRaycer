package raycer;

public class Vector {
	
	public double x, y, z;

	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public final Vector add(Vector other) {
		return new Vector(this.x + other.x, this.y + other.y, this.z + other.z);
	}

	public final Vector scale(double lambda) {
		return new Vector(lambda * this.x, lambda * this.y, lambda * this.z);
	}

	public final Vector subtract(Vector other) {
		return this.add(other.scale(-1.0));
	}

	public final String toString() {
		return String.format("<Vector x: %f, y: %f, z: %f>", this.x, this.y, this.z);
	}

	public final void print() {
		System.out.println(this.toString());
	}

	public final double dot(Vector other) {
		return this.x * other.x + this.y * other.y + this.z * other.z;
	}

	public final Vector normalise() {
		return new Vector(this.x, this.y, this.z).scale(1.0 / this.magnitude());
	}

	public final double magnitude() {
		return Math.sqrt(this.dot(this));
	}

	public final Vector reflect(Vector normal) {
		return this.subtract(normal.scale(2.0 * this.dot(normal)));
	}
}
