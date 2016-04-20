package interfaces;

import models.Snake;

public interface SnakeEngine {

	Snake getSnake();
	
	boolean isPaused();

	void setPaused(boolean isPaused);
	
	boolean isRunning();
	
	void setIsRunning(boolean isRunning);
}
