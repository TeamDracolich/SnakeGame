package ui;
/**
 * Created by Jivko on 19-Apr-16.
 */
import java.awt.event.KeyEvent;

import interfaces.GameInputHandler;
import interfaces.SnakeEngine;

public class InputHandler implements GameInputHandler{

    private SnakeEngine gameEngine;
	
	public InputHandler(){

    }

	public void keyPressed(KeyEvent e) {
        
    	int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_ENTER && !this.gameEngine.isRunning()) {
			
			this.gameEngine.setIsRunning(true);
		} else if (keyCode == KeyEvent.VK_UP && this.gameEngine.getSnake().getDirectionY() != 1) {
			
			this.gameEngine.getSnake().setDirectionX(0);
			this.gameEngine.getSnake().setDirectionY(-1);
		} else if (keyCode == KeyEvent.VK_DOWN && this.gameEngine.getSnake().getDirectionY() != -1) {
			
			this.gameEngine.getSnake().setDirectionX(0);
			this.gameEngine.getSnake().setDirectionY(1);
		} else if (keyCode == KeyEvent.VK_RIGHT && this.gameEngine.getSnake().getDirectionX() != -1) {
			
			this.gameEngine.getSnake().setDirectionX(1);
			this.gameEngine.getSnake().setDirectionY(0);
		} else if (keyCode == KeyEvent.VK_LEFT && this.gameEngine.getSnake().getDirectionX() != 1) {
			
			this.gameEngine.getSnake().setDirectionX(-1);
			this.gameEngine.getSnake().setDirectionY(0);
		} else if (keyCode == KeyEvent.VK_ESCAPE) {
			
			System.exit(0);
		} else if (keyCode == KeyEvent.VK_P) {
			
			this.gameEngine.setPaused(!this.gameEngine.isPaused());
		} 
    }


    public void keyReleased(KeyEvent e) {
    	
    }


    public void keyTyped(KeyEvent e) {

    }
    
    public void setGameEngine(SnakeEngine gameEngine) {
    	
    	this.gameEngine = gameEngine;
    }
}
