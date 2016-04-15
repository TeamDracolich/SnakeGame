package engine;

import java.awt.Canvas;
import java.awt.Graphics;


@SuppressWarnings("serial")
public class GameEngine extends Canvas implements Runnable {

	public static final int GAME_SCREEN_WIDTH = 600;
	public static final int GAME_SCREEN_HEIGHT = 600;
	
	private int gameSpeed;
	
	private Thread runThread;
	private Graphics graphics;
	private boolean isRunning;
	
	public GameEngine() {

	}
		
	@Override
	public void paint(Graphics graphics) {
		
		this.graphics = graphics.create();
		if (this.runThread ==  null) {
			
			this.runThread = new Thread(this);
			this.runThread.start();
			this.isRunning = true;
		}
	}

	@Override
	public void run() {
		
		while (isRunning) {

			try {
				
				Thread.sleep(this.gameSpeed);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}
}

