package engine;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import ui.InputHandler;

@SuppressWarnings("serial")
public class GameApp extends Applet {
	
	private final int APPLET_WINDOW_WIDTH = 800;
	private final int APPLET_WINDOW_HEIGHT = 650;
	
	private GameEngine gameEngine;
	InputHandler iHandler;
	
	public void init() {
		
		this.gameEngine = new GameEngine();
		this.gameEngine.setBackground(Color.lightGray);
		this.gameEngine.setPreferredSize(new Dimension(GameEngine.GAME_SCREEN_WIDTH, GameEngine.GAME_SCREEN_HEIGHT));
		this.gameEngine.setVisible(true);
		this.gameEngine.setFocusable(true);
		this.add(gameEngine);
		this.setVisible(true);
		iHandler = new InputHandler(gameEngine);
	}
	
	public void paint(Graphics graphics){
	 
		this.setSize(new Dimension(APPLET_WINDOW_WIDTH, APPLET_WINDOW_HEIGHT));
	}
}
