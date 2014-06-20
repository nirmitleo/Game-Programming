package com.pokemon.visualization;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

public class MapVisualization1 extends Canvas implements Runnable {
	private static int WIDTH = 300;
	private static int HEIGHT = WIDTH / 16 * 9;
	private static int SCALE = 3;
	private int x = 0;
	private int y = 0;
	private static int RECT_WIDTH = 20;
	private static int RECT_HEIGHT = 20;
	private Random random = new Random(0xffffff);

	private BufferedImage image = new BufferedImage(WIDTH * SCALE, HEIGHT * SCALE, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	private int[] tiles = new int[64 * 64];

	private JFrame frame;

	private boolean running = false;
	private Thread gameThread;

	public MapVisualization1() {
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		this.setPreferredSize(size);

		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = random.nextInt();
		}

		frame = new JFrame();
	}

	public synchronized void start() {
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void run() {
		for (y = 0; y < this.getHeight(); y++) {
			int yy = y;
			for (x = 0; x < this.getWidth(); x++) {
				int xx = x;
				int tilesIndex = (xx >> 5) + (yy >> 5) * 64;
				pixels[x + y * this.getWidth()] = tiles[tilesIndex];
				this.repaint();
				try {
					gameThread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
	}

	public synchronized void stop() {
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		MapVisualization1 visualization = new MapVisualization1();
		visualization.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		visualization.frame.setResizable(false);
		visualization.frame.add(visualization);
		visualization.frame.pack();
		visualization.frame.setLocationRelativeTo(null);
		visualization.frame.setVisible(true);

		visualization.start();
	}

}
