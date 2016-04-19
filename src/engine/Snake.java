package engine;

import java.awt.*;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 18.04.2016.
 */
public class Snake {
    public static LinkedList<Box> body;
    public Box head;

    public Snake() {
        body = new LinkedList<>();
        Collections.addAll(body,
            new Box(1, 2),
            new Box(2, 2),
            new Box(3, 2),
            new Box(4, 2)
        );
    }
    public void drawSnake (Graphics graphics){
        for (Box box : body) {
            graphics.setColor(Color.blue);
            graphics.fillRect(box.x * box.BOX_SIZE, box.y * box.BOX_SIZE,
                    box.BOX_SIZE, box.BOX_SIZE);

            graphics.setColor(Color.red);
            graphics.drawRect(box.x * box.BOX_SIZE, box.y * box.BOX_SIZE,
                    box.BOX_SIZE, box.BOX_SIZE);
        }
    }

    public void tick(){
        head = body.peekLast();

        Box nextPosition = new Box(head.x + 1, head.y + 0);

        for (int i = 0; i < body.size() - 1; i++) {
            body.set(i, body.get(i+1));
        }
        body.set(body.size() - 1, nextPosition);
    }
}