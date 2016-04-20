package models;

import java.awt.*;
import java.util.Random;

import engine.GameEngine;


public class Apple {
    private Box appleBox;
    private Random randGenerator;

    public Apple() {
        appleBox = createApple(GameEngine.snake);
    }
    private Box createApple(Snake s){
        randGenerator = new Random();

        int x = randGenerator.nextInt(GameEngine.COLS);
        int y = randGenerator.nextInt(GameEngine.ROWS);

        Box applePoint = new Box(x,y);

        if (s.body.contains(applePoint)){
            return createApple(s);
        }
        return applePoint;
    }

    public void drawApple(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillOval(appleBox.x*appleBox.BOX_SIZE, appleBox.y*appleBox.BOX_SIZE, appleBox.BOX_SIZE,appleBox.BOX_SIZE);
    }
}
    public Box getAppleBox(){
        return appleBox;
    }
