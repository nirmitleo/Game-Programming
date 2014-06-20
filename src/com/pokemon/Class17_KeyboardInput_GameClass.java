package com.pokemon;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.pokemon.graphics.Class17_KeyboardInput_ScreenClass;
import com.pokemon.input.Class17_Keyboard_KeyboardClass;

public class Class17_KeyboardInput_GameClass extends Canvas implements Runnable {
	public static int WIDTH = 300;
	public static int HEIGHT = WIDTH / 16 * 9;
	public static int SCALE = 3;

	private int x = 0;
	private int y = 0;
	private Class17_KeyboardInput_ScreenClass screen;
	private BufferedImage image = new BufferedImage(WIDTH * SCALE, HEIGHT * SCALE, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private JFrame gameFrame;
	private Class17_Keyboard_KeyboardClass key;
	private boolean running = false;
	private Thread gameThread;

	public Class17_KeyboardInput_GameClass() {
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		this.setPreferredSize(size);

		screen = new Class17_KeyboardInput_ScreenClass(WIDTH * SCALE, HEIGHT * SCALE);
		gameFrame = new JFrame();
		key = new Class17_Keyboard_KeyboardClass();
		this.addKeyListener(key);
	}

	public synchronized void start() {
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void run() {
		long firstTime = System.nanoTime();
		final double NANOSECONDS_TO_SECONDS = 1000000000 / 60.0;
		double delta = 0;
		long secondsTimer = System.currentTimeMillis();
		int UPS = 0, FPS = 0;
		while (running) {
			long secondTime = System.nanoTime();
			delta += (secondTime - firstTime) / NANOSECONDS_TO_SECONDS;
			firstTime = secondTime;
			while (delta >= 1) {
				update();
				UPS++;
				delta--;
			}
			render();
			FPS++;

			if (System.currentTimeMillis() - secondsTimer >= 1000) {
				secondsTimer += 1000;
				gameFrame.setTitle("UPS = " + UPS + " || FPS = " + FPS);
				UPS = 0;
				FPS = 0;
			}
		}
	}

	public void update() {
		key.update();
		if (key.up) {
			y--;
		}
		if (key.down) {
			y++;
		}
		if (key.right) {
			x++;
		}
		if (key.left) {
			x--;
		}

	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		screen.clearScreen();
		screen.render(x, y);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
		g.dispose();
		bs.show();

	}

	public synchronized void stop() {
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String arg[]) {
		Class17_KeyboardInput_GameClass game = new Class17_KeyboardInput_GameClass();
		game.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.gameFrame.setResizable(false);
		game.gameFrame.add(game);
		game.gameFrame.pack();
		game.gameFrame.setLocationRelativeTo(null);
		game.gameFrame.setVisible(true);

		game.start();
	}
}
