package interfaces;

import java.awt.Graphics;
import java.util.Collection;

import models.Apple;
import models.Box;

public interface ISnake {
	
	Collection<Box> getSnakeBody();
	
	Box getSnakeHead();
	
	boolean checkIsDead();
    
    int getDirectionX();

	void setDirectionX(int directionX);

	int getDirectionY();

	void setDirectionY(int directionY);

	void drawSnake(Graphics graphics);

    void updateSnake();
    
    void eatApple(Apple apple);
}
