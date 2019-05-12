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

	public final Vector times(double lambda) {
		return new Vector(lambda * this.x, lambda * this.y, lambda * this.z);
	}

	public final Vector subtract(Vector other) {
		return this.add(other.times(-1.0F));
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
}
