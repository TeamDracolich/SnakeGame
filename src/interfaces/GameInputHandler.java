package interfaces;

import java.awt.event.KeyListener;

public interface GameInputHandler extends KeyListener {
	
	void setGameEngine(SnakeEngine gameEngine);
}
