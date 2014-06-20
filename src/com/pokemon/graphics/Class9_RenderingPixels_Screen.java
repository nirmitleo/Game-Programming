package com.pokemon.graphics;

import java.awt.Color;

public class Class9_RenderingPixels_Screen {
	private int width, height;
	public int[] pixels;

	public Class9_RenderingPixels_Screen(int width, int height) {
		System.out.println(width + " " + height);
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}

	public void render() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x+y*width] = 0xfabcde;
			}
		}
	}
}
