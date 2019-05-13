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
		return new RGBColour(scaleComponent(this.r, intensity), scaleComponent(this.g, intensity), scaleComponent(this.b, intensity));
	}

	private int scaleComponent(int component, double intensity) {
		return Math.min(255, (int) ((double) component * intensity));
	}

}
