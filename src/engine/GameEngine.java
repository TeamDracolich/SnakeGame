package engine;

import java.awt.Canvas;
import java.awt.Graphics;


@SuppressWarnings("serial")
public class GameEngine extends Canvas implements Runnable {
	public static Snake snake;
	public static Apple apple; 

	public static final int GAME_SCREEN_WIDTH = 600;
	public static final int GAME_SCREEN_HEIGHT = 600;
	
	private int gameSpeed;
	
	private Thread runThread;
	private Graphics graphics;
	
	public static int score;
	private boolean isRunning;
	
	@Override
	public void paint(Graphics graphics) {
		
		this.graphics = graphics.create();
		if (this.runThread ==  null) {
			
			this.runThread = new Thread(this);
			this.runThread.start();
			this.isRunning = true;
		}
	}

	public GameEngine() {
		snake = new Snake();
		apple = new Apple();
		
		score = 0;
	
	}

	@Override
	public void run() {
		gameSpeed = 150;
		
		while (isRunning) {
			snake.tick();
			render(graphics);

			try {
				
				Thread.sleep(this.gameSpeed);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

	public void render(Graphics graphics){
		graphics.clearRect(0, 0, GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT);
		snake.drawSnake(graphics);
		apple.drawApple(graphics)
		
		this.drawScore(graphics);
	}
	public void drawScore(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 600, 600, 25);
		g.setColor(Color.WHITE);
		g.drawString("Score: " + score, 10, 615);
	}
}

