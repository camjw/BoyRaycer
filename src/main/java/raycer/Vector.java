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

	public final void print() {
		System.out.printf("<Vector x: %f, y: %f, z: %f>\n", this.x, this.y, this.z);
	}
}
