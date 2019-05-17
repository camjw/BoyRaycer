package raycer;

import java.util.ArrayList;

public class Material {

	RGBColour colour;
	double albedoDiffuse;
	double albedoSpecular;
	double specularExponent;
	static RGBColour WHITE = new RGBColour(255, 255, 255);

	public Material(RGBColour colour, double albedoDiffuse, double albedoSpecular, double specularExponent) {
		this.colour = colour;
		this.albedoDiffuse = albedoDiffuse;
		this.albedoSpecular = albedoSpecular;
		this.specularExponent = specularExponent;
	}

	public Material(RGBColour colour) {
		this.colour = colour;
		this.albedoDiffuse = 0.7;
		this.albedoSpecular = 0.3;
		this.specularExponent = 10;
	}

	public RGBColour colourScaled(double diffuseIntensity, double specularIntensity) {
		return this.colour.scale(diffuseIntensity * albedoDiffuse);
	}
}
