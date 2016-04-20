package engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import interfaces.*;
import models.Apple;
import models.Snake;

@SuppressWarnings("serial")
public class GameEngine extends Canvas implements Runnable, SnakeEngine {
	
	public static final int ROWS_MAX_LENGTH = 30;
	public static final int COLS_MAX_LENGTH = 30;
	
	private static final int DEFAULT_BOX_SIZE = 20;
	
	public static final int GAME_SCREEN_WIDTH = ROWS_MAX_LENGTH * DEFAULT_BOX_SIZE;
	public static final int GAME_SCREEN_HEIGHT = COLS_MAX_LENGTH * DEFAULT_BOX_SIZE;
	
	private final int INITIAL_GAME_SPEED = 150;
	
	private Snake snake;
	private Apple apple; 
	
	private Thread runThread;
	private Graphics graphics;
	
	private int gameSpeed;
	private int score;
	private boolean isRunning;
	private boolean isPaused;
	private GameInputHandler inputHandler;
	
	public GameEngine(GameInputHandler inputHandler) {
		
		this.snake = new Snake(DEFAULT_BOX_SIZE);
		this.apple = new Apple(this.snake, DEFAULT_BOX_SIZE);
		this.gameSpeed = INITIAL_GAME_SPEED;
		this.inputHandler = inputHandler;
		this.addKeyListener(inputHandler);
		this.inputHandler.setGameEngine(this);
		this.setPaused(false);
		this.isRunning = false;
	}
	
	public Snake getSnake() {
		
		return this.snake;
	}
	
	public boolean isPaused() {
		
		return this.isPaused;
	}

	public void setPaused(boolean isPaused) {
		
		this.isPaused = isPaused;
	}
	
	public boolean isRunning() {
		
		return this.isRunning;
	}
	
	public void setIsRunning(boolean isRunning) {
		
		this.isRunning = isRunning;
	}

	@Override
	public void paint(Graphics graphics) {
		
		this.graphics = graphics.create();
		if (this.runThread == null) {
			
			this.runThread = new Thread(this);
			this.runThread.start();
		}
	}

	@Override
	public void run() {
		
		if (!this.isRunning) {
			
			this.showStartMenu();
		}
		
		while (this.isRunning) {
			
			if (!this.isPaused()) {
				
				this.snake.updateSnake();
				
				if (this.snake.checkIsDead()) {
					
					this.isRunning = false;
				} else {
					
					detectCollisions();
				}
				
				this.render();
			}
			
			try {
				
				Thread.sleep(this.gameSpeed);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
				
		}
		
		this.drawEndScreen();
	}

	private void showStartMenu() {
		
		while (!this.isRunning) {
			
			this.graphics.setColor(Color.BLUE);
			this.graphics.drawString("Press Enter to start game.", GAME_SCREEN_WIDTH / 2, GAME_SCREEN_HEIGHT / 2);
			this.graphics.drawString(
					"While playing press P to pause/unpause game.", GAME_SCREEN_WIDTH / 2, GAME_SCREEN_HEIGHT / 2 + DEFAULT_BOX_SIZE);
			this.graphics.drawString(
					"Press ESC at any time to exit.", GAME_SCREEN_WIDTH / 2, GAME_SCREEN_HEIGHT / 2 + 2 * DEFAULT_BOX_SIZE);
		}
	}

	private void detectCollisions() {
		
		boolean hitBorder = this.snake.getSnakeHead().getX() >= COLS_MAX_LENGTH ||
				this.snake.getSnakeHead().getY() >= ROWS_MAX_LENGTH ||
						this.snake.getSnakeHead().getY() < 0 ||
						this.snake.getSnakeHead().getX() < 0;
		
		if (hitBorder) {
			
			this.isRunning = false;
		} else if (this.snake.getSnakeHead().equals(this.apple.getAppleBox())) {
			
			this.snake.eatApple(this.apple);
			this.apple = new Apple(this.snake, DEFAULT_BOX_SIZE);
			this.score = this.score + 10;
			
			this.changeSpeed();
		}
	}
	
	private void changeSpeed() {
            
		if (this.score % 30 == 0) { //Increase speed at each three eaten apples.
                
			this.gameSpeed -= 10;
        }
	}

	private void render(){
		
		this.graphics.clearRect(0, 0, GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT);
		
		this.snake.drawSnake(this.graphics);
		
		this.apple.drawApple(this.graphics);
		
		this.drawScore();
	}
	
	private void drawScore() {
		
		this.graphics.setColor(Color.BLACK);
		this.graphics.fillRect(0, GAME_SCREEN_HEIGHT, GAME_SCREEN_WIDTH, 25);
		this.graphics.setColor(Color.WHITE);
		this.graphics.drawString("Score = " + this.score, 10, 10);
	}
	
	private void drawEndScreen() {
		
		while (!this.isRunning) {
			
			this.graphics.setColor(Color.BLACK);
			this.graphics.drawString("Game Over! Final Score = " + this.score, GAME_SCREEN_WIDTH / 2, GAME_SCREEN_HEIGHT / 2);
			this.graphics.drawString(
					"Press Enter to play again.", GAME_SCREEN_WIDTH / 2, GAME_SCREEN_HEIGHT / 2 + DEFAULT_BOX_SIZE);
			this.graphics.drawString(
					"Press ESC to exit.", GAME_SCREEN_WIDTH / 2, GAME_SCREEN_HEIGHT / 2 + 2 * DEFAULT_BOX_SIZE);
			
		}
		
		this.snake = new Snake(DEFAULT_BOX_SIZE);
		this.apple = new Apple(this.snake, DEFAULT_BOX_SIZE);
		this.gameSpeed = INITIAL_GAME_SPEED;
		this.score = 0;
		this.run();
	}
}

