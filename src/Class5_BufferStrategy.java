import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Class5_BufferStrategy extends Canvas implements Runnable {
	public static int WIDTH = 300;
	public static int HEIGHT = WIDTH / 16 * 9;
	public static int SCALE = 3;
	private JFrame gameFrame;

	private Thread gameThread;

	private boolean running = false;

	public Class5_BufferStrategy() {
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		this.setPreferredSize(size);

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
			System.out.println("Running!!");
		}
	}

	public void update() {

	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
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
		Class5_BufferStrategy game = new Class5_BufferStrategy();

		game.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.gameFrame.setResizable(false);
		game.gameFrame.setTitle("Pokemon!!");
		game.gameFrame.add(game);
		game.gameFrame.pack();
		game.gameFrame.setLocationRelativeTo(null);
		game.gameFrame.setVisible(true);

		game.start();
	}
}
