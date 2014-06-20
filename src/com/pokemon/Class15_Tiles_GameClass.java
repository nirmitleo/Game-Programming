package com.pokemon;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.pokemon.graphics.Class15_Tiles_ScreenClass;

public class Class15_Tiles_GameClass extends Canvas implements Runnable {
	private static int WIDTH = 300;
	private static int HEIGHT = WIDTH / 16 * 9;
	private static int SCALE = 3;

	private BufferedImage image = new BufferedImage(WIDTH * SCALE, HEIGHT * SCALE, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	private Class15_Tiles_ScreenClass screen;

	private JFrame gameFrame;
	private boolean running = false;
	private Thread gameThread;

	public Class15_Tiles_GameClass() {
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		this.setPreferredSize(size);

		screen = new Class15_Tiles_ScreenClass(WIDTH * SCALE, HEIGHT * SCALE);

		gameFrame = new JFrame();
	}

	public synchronized void start() {
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void run() {
		long firstTime = System.nanoTime();
		double delta = 0;
		final double ns = 1000000000 / 60.0;

		long SecondsTimer = System.currentTimeMillis();
		int UPS = 0, FPS = 0;

		while (running) {
			long secondTime = System.nanoTime();
			delta += (secondTime - firstTime) / ns;
			firstTime = secondTime;
			if (delta >= 1) {
				update();
				UPS++;
				delta--;
			}
			render();
			FPS++;
			if (System.currentTimeMillis() - SecondsTimer > 1000) {
				SecondsTimer += 1000;
				gameFrame.setTitle("UPS = " + UPS + " || FPS = " + FPS);
				UPS = 0;
				FPS = 0;
			}

		}
	}

	public void update() {

	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		screen.clearScreen();
		screen.render();

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
		g.dispose();
		bs.show();

	}

	public synchronized void stop() {
		running = false;
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		Class15_Tiles_GameClass game = new Class15_Tiles_GameClass();
		game.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.gameFrame.setResizable(false);
		game.gameFrame.add(game);
		game.gameFrame.pack();
		game.gameFrame.setLocationRelativeTo(null);
		game.gameFrame.setVisible(true);

		game.start();
	}
}
