package com.pokemon.graphics;

import java.util.Random;

public class Class16_Map_ScreenClass {
	private int width, height;
	public int[] pixels;

	private static int MAP_SIZE = 64;
	private static int MAP_SIZE_MASK = MAP_SIZE - 1;

	private Random random = new Random(0xffffff);
	private int[] tiles = new int[MAP_SIZE * MAP_SIZE];

	public Class16_Map_ScreenClass(int width, int height) {
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

	public void render(int xOffset, int yOffset) {
		for (int y = 0; y < height; y++) {
			int yy = y + yOffset;
			for (int x = 0; x < width; x++) {
				int xx = x + xOffset;
				int tilesIndex = ((xx >> 5) & MAP_SIZE_MASK) + ((yy >> 5) & MAP_SIZE_MASK) * MAP_SIZE;
				pixels[x + y * width] = tiles[tilesIndex];
			}
		}
	}

}
