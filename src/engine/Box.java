package engine;

/**
 * Created by user on 18.04.2016.
 */
public class Box {
    public int x;
    public int y;
    public static final int BOX_SIZE = 20;

    public Box (int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object obj){
        if(obj instanceof Box){
            Box b = (Box) obj;
            return (this.x == b.x && this.y == b.y);
        }else{                  // "else" or without "else" ?
            return false;
        }
    }
}
