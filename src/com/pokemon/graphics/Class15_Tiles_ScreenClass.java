package com.pokemon.graphics;

import java.util.Random;

public class Class15_Tiles_ScreenClass {
	private int width, height;
	public int[] pixels;

	public int[] tiles = new int[64 * 64];

	private Random random = new Random(0xffffff);

	public Class15_Tiles_ScreenClass(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];

		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = random.nextInt();
		}
	}

	public void clearScreen() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render() {
		for (int y = 0; y < height; y++) {
			if (y < 0 || y >= height)
				break;
			for (int x = 0; x < width; x++) {
				if (x < 0 || x >= width)
					break;
				int tilesIndex = (x >> 5) + ((y >> 5) << 6);
				pixels[x + y * width] = tiles[tilesIndex];
			}
		}
	}
}
