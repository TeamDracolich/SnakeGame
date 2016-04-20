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
	private GameInputHandler inputHandler;
	
	public GameEngine(GameInputHandler inputHandler) {
		
		this.snake = new Snake(DEFAULT_BOX_SIZE);
		this.apple = new Apple(this.snake, DEFAULT_BOX_SIZE);
		this.gameSpeed = INITIAL_GAME_SPEED;
		this.inputHandler = inputHandler;
		this.addKeyListener(inputHandler);
		this.inputHandler.setGameEngine(this);
	}
	
	public Snake getSnake() {
		
		return this.snake;
	}
	
	@Override
	public void paint(Graphics graphics) {
		
		this.graphics = graphics.create();
		if (this.runThread == null) {
			
			this.runThread = new Thread(this);
			this.runThread.start();
			this.isRunning = true;
		}
	}

	@Override
	public void run() {
		
		while (this.isRunning) {
			
			this.snake.updateSnake();
			
			if (this.snake.checkIsDead()) {
				
				this.isRunning = false;
			} else {
				
				detectCollisions();
			}
			
			this.render();
			try {
				
				Thread.sleep(this.gameSpeed);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		
		this.drawEndScreen();
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
		
		this.graphics.setColor(Color.BLACK);
		this.graphics.drawString("Game Over! Final Score = " + this.score, GAME_SCREEN_WIDTH / 2, GAME_SCREEN_HEIGHT / 2);
	}
}

