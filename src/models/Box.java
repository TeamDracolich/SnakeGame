package models;

import interfaces.IBox;

/**
 * Created by user on 18.04.2016.
 */
public class Box implements IBox {
    
	private int x;
    private int y;
    
    public Box (int x, int y){
    	
        this.setX(x);
        this.setY(y);
    }
    
    public int getX() {
		
    	return this.x;
	}

	public void setX(int x) {
		
		this.x = x;
	}

	public int getY() {
		
		return this.y;
	}

	public void setY(int y) {
		
		this.y = y;
	}

	@Override
	public boolean equals(Object obj){
    	
        if(obj instanceof Box){
            Box otherBox = (Box) obj;
            return (this.getX() == otherBox.getX() && this.getY() == otherBox.getY());
        }else{
            return false;
        }
    }
}
