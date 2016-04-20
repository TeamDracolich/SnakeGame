package models;

import java.awt.*;
import java.util.Collection;
import java.util.LinkedList;

import interfaces.ISnake;

/**
 * Created by user on 18.04.2016.
 */
public class Snake implements ISnake {
    
	private final int SNAKE_START_X_POSITION = 1;
	private final int SNAKE_START_Y_POSITION = 2;
	private final int SNAKE_INITIAL_BOXES_COUNT = 4;
	
	private LinkedList<Box> snakeBody;
    private int directionX;
    private int directionY;
    private Box snakeHead;
    private int boxSize;
    private boolean isDead;

    public Snake(int boxSize) {
        
    	this.intializeSnake();
        this.snakeHead = this.snakeBody.peekLast();
        this.directionX = 1;
        this.directionY = 0;
        this.boxSize = boxSize;
        this.isDead = false;
    }

    public Collection<Box> getSnakeBody() {
		
		return this.snakeBody;
	}
	
	public Box getSnakeHead() {
		
		return this.snakeHead;
	}
	
	public boolean checkIsDead() {
		
		return this.isDead;
	}
    
    public int getDirectionX() {
		
    	return this.directionX;
	}

	public void setDirectionX(int directionX) {
		
		this.directionX = directionX;
	}

	public int getDirectionY() {
		
		return this.directionY;
	}

	public void setDirectionY(int directionY) {
		
		this.directionY = directionY;
	}

	public void drawSnake(Graphics graphics) {
        
    	for (Box box : this.snakeBody) {
            
    		graphics.setColor(Color.blue);
            graphics.fillRect(box.getX() * this.boxSize, box.getY() * this.boxSize,
            		this.boxSize, this.boxSize);

            graphics.setColor(Color.red);
            graphics.drawRect(box.getX() * this.boxSize, box.getY() * this.boxSize,
            		this.boxSize, this.boxSize);
        }
    }

    public void updateSnake(){
        
    	Box nextPosition = new Box(this.snakeHead.getX() + this.getDirectionX(), 
				this.snakeHead.getY() + this.getDirectionY());
		if (this.snakeBody.contains(nextPosition)) {
			
			this.isDead = true;
			return;
		}
		
		for (int i = 0; i < this.snakeBody.size() - 1; i++) {
			
			this.snakeBody.set(i, this.snakeBody.get(i + 1));
		}
		
		this.snakeBody.set(this.snakeBody.size() - 1, nextPosition);
		this.snakeHead = nextPosition;
    }
    
    public void eatApple(Apple apple) {
		
		this.snakeBody.add(apple.getAppleBox());
		this.snakeHead = this.snakeBody.peekLast();
	}
    
    private void intializeSnake() {
    	
    	this.snakeBody = new LinkedList<Box>();
    	for (int i = 0; i < SNAKE_INITIAL_BOXES_COUNT; i++) {
			
    		Box box = new Box(SNAKE_START_X_POSITION + i, SNAKE_START_Y_POSITION);
    		this.snakeBody.add(box);
		}
    }
}
