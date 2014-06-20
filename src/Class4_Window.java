import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Class4_Window extends Canvas implements Runnable {
	public static int WIDTH = 300;
	public static int HEIGHT = WIDTH / 16 * 9;
	public static int SCALE = 3;
	private JFrame gameFrame;

	private Thread gameThread;

	private boolean running = false;

	public Class4_Window() {
		Dimension size = new Dimension(WIDTH * SCALE,HEIGHT * SCALE);
		setPreferredSize(size);

		gameFrame = new JFrame();
	}

	public synchronized void start() {
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (running) {
			System.out.println("Running!!");
		}
	}

	public static void main(String args[]) {
		Class4_Window game = new Class4_Window();

		game.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.gameFrame.setResizable(false);
		game.gameFrame.setTitle("Pokemon");
		game.gameFrame.add(game);
		game.gameFrame.pack();
		game.gameFrame.setLocationRelativeTo(null);
		game.gameFrame.setVisible(true);

		game.start();
	}

}
