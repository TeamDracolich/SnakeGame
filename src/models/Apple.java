package models;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import engine.GameEngine;
import interfaces.IApple;


public class Apple implements IApple {
    
	private static final Random randGenerator = new Random();
	
	private Box appleBox;
	private int boxSize;

    public Apple(Snake snake, int boxSize) {
        
    	this.boxSize = boxSize;
    	this.appleBox = createApple(snake);
    }
    
    public Box getAppleBox() {
		
		return this.appleBox;
	}
	
	public void drawApple(Graphics graphics){
		
		graphics.setColor(Color.YELLOW);
		graphics.fillOval(this.appleBox.getX() * this.boxSize, this.appleBox.getY() * this.boxSize, 
				this.boxSize, this.boxSize);
	}
    
    private Box createApple(Snake snake){
        
    	int x = randGenerator.nextInt(GameEngine.COLS_MAX_LENGTH);
        int y = randGenerator.nextInt(GameEngine.ROWS_MAX_LENGTH);

        Box applePoint = new Box(x, y);

        if (snake.getSnakeBody().contains(applePoint)) {
			
			return this.createApple(snake);
		}

        return applePoint;
    }
}
