package raycer;

public class RGBColour {

	int r;
	int g;
	int b;

	public RGBColour(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public final int toInt() {
		return (255 << 24) | (r << 16) | (g << 8) | b;
	}

	public RGBColour scale(double intensity) {
		return new RGBColour(capComponent(this.r * intensity), capComponent(this.g * intensity), capComponent(this.b * intensity));
	}

	public RGBColour add(RGBColour other) {
		return new RGBColour(capComponent(this.r + other.r), capComponent(this.g + other.g), capComponent(this.b + other.b));
	}

	private int capComponent(double component) {
		return Math.min(255, (int) component);
	}

}
