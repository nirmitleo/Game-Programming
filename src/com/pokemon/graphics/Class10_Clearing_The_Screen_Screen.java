package com.pokemon.graphics;

import java.awt.Color;

public class Class10_Clearing_The_Screen_Screen {
	private int width, height;

	public int[] pixels;

	public int time = 0;
	public int counter = 0;

	public Class10_Clearing_The_Screen_Screen(int width, int height) {
		this.width = width;
		this.height = height;

		pixels = new int[height * width];
	}
	
	public void clear(){
		for(int i=0;i<pixels.length;i++){
			pixels[i] = 0;
		}
	}

	public void render() {
		counter++;
		if (counter % 10 == 0) {
			time++;
		}

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[time + time * width] = Color.RED.getAlpha();
			}
		}
	}

}
