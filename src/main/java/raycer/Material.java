package raycer;

import java.util.ArrayList;

public class Material {

	RGBColour colour;
	double albedo;
	double specularExponent;
	static RGBColour WHITE = new RGBColour(255, 255, 255);

	public Material(RGBColour colour, double albedo, double specularExponent) {
		this.colour = colour;
		this.albedo = albedo;
		this.specularExponent = specularExponent;
	}

	public Material(RGBColour colour) {
		this.colour = colour;
		this.albedo = 0.7;
		this.specularExponent = 10;
	}

	public RGBColour colourScaled(double diffuseIntensity, double specularIntensity) {
		return this.colour.scale(diffuseIntensity * albedo).add(WHITE.scale(specularIntensity * (1 - albedo)));
	}
}
