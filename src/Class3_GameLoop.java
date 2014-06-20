
public class Class3_GameLoop implements Runnable{
	public static int WIDTH = 300;
	public static int HEIGHT = WIDTH / 16 * 9;
	public static int SCALE = 3;
	
	private Thread gameThread;
	private boolean running = false;
	
	public synchronized void start(){
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}	
	public synchronized void stop(){
		running = false;
		try{
			gameThread.join();
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	public void run(){
		while(running){
			
		}
		
		
	}
	

}

