package com.pokemon.graphics;

import java.util.Random;

public class Class17_KeyboardInput_ScreenClass {
	public int pixels[];

	private int width, height;
	private Random random = new Random(0xffffff);

	private static int MAP_SIZE = 64;
	private static int MAP_SIZE_MASK = MAP_SIZE - 1;
	private int[] tiles = new int[MAP_SIZE * MAP_SIZE];

	public Class17_KeyboardInput_ScreenClass(int width, int height) {
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
				int tilesIndex = ((xx >> 4) & MAP_SIZE_MASK) + ((yy >> 4) & MAP_SIZE_MASK) * MAP_SIZE;
				pixels[x + y * width] = tiles[tilesIndex];
			}
		}
	}

}
