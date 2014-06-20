package com.pokemon.graphics;

public class Class8_Screen_ScreenClass {
	private int width, height;
	public int pixels[];

	public Class8_Screen_ScreenClass(int width, int height) {
		this.height = height;
		this.width = width;
		pixels = new int[width * height];
	}

}
