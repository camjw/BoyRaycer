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
		return new RGBColour( (int) ((double) this.r * intensity), (int) ((double) this.g * intensity), (int) ((double) this.b * intensity));
	}
}
