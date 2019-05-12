package raycer;

import java.util.ArrayList;

public class BoyRaycer {
	
	static int width = 1024;
	static int height = 768;
	
	public static void main(String[] args) {
		render();
	}

	public static void render() {

		ArrayList<ArrayList<Float>> framebuffer = new ArrayList<ArrayList<Float>>();

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				float red = j/((float) height);
				float green =  i/((float) width);
				ArrayList<Float> currentPixel = new ArrayList<Float>(3);
				
				framebuffer.set(i * j * width, currentPixel);
			}
		}
	}
}

