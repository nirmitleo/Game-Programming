package com.pokemon.graphics;

public class Class13_Timer_ScreenClass {
	private int width, height;

	public int pixels[];

	public Class13_Timer_ScreenClass(int width, int height) {
		this.width = width;
		this.height = height;

		pixels = new int[width * height];
	}

	public void clearScreen(){
		for(int i = 0 ; i < pixels.length ; i++){
			pixels[i] = 0; 
		}
	}

	public void render() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = 0xaabbcc;
			}
		}
	}
}
