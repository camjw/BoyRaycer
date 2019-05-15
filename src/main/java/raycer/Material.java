package raycer;

import java.util.ArrayList;

public class Material {

	RGBColour colour;
	double albedo;
	double specularExponent;

	public Material(RGBColour colour, double albedo, double specularExponent) {
		this.colour = colour;
		this.albedo = albedo;
		this.specularExponent = specularExponent;
	}

	public Material(RGBColour colour) {
		this.colour = colour;
		this.albedo = 1;
		this.specularExponent = 1;
	}

	public RGBColour colourScaled(double intensity) {
		return this.colour.scale(intensity);
	}
}
