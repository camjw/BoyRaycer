package raycer;

public class Vector {
	
	public float x, y, z;

	public Vector(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector add(Vector other) {
		return new Vector(this.x + other.x, this.y + other.y, this.z + other.z);
	}

	public Vector times(float lambda) {
		return new Vector(lambda * this.x, lambda * this.y, lambda * this.z);
	}

	public Vector subtract(Vector other) {
		return this.add(other.times(-1.0F));
	}

	public void print() {
		System.out.printf("<Vector x: %f, y: %f, z: %f>\n", this.x, this.y, this.z);
	}
}
