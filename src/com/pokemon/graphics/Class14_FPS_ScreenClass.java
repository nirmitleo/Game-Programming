package com.pokemon.graphics;

public class Class14_FPS_ScreenClass {
	private int height, width;
	public int[] pixels;

	public Class14_FPS_ScreenClass(int width, int height) {
		this.width = width;
		this.height = height;

		pixels = new int[height * width];
	}

	public void clearScreen() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = 0xaaccbb;
			}
		}

	}
}
