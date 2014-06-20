package com.pokemon;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.pokemon.graphics.Class10_Clearing_The_Screen_Screen;

public class Class10_Clearing_The_Screen extends Canvas implements Runnable {
	public static int WIDTH = 300;
	public static int HEIGHT = WIDTH / 16 * 9;
	public static int SCALE = 3;

	private JFrame gameFrame;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	private Class10_Clearing_The_Screen_Screen screen;

	private Thread gameThread;

	private boolean running = false;

	public Class10_Clearing_The_Screen() {
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		this.setPreferredSize(size);

		screen = new Class10_Clearing_The_Screen_Screen(WIDTH, HEIGHT);

		gameFrame = new JFrame();
	}

	public synchronized void start() {
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void run() {
		while (running) {
			update();
			render();
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

		screen.clear();
		
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
		Class10_Clearing_The_Screen game = new Class10_Clearing_The_Screen();
		game.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.gameFrame.setResizable(false);
		game.gameFrame.add(game);
		game.gameFrame.pack();
		game.gameFrame.setLocationRelativeTo(null);
		game.gameFrame.setVisible(true);

		game.start();
	}
}
