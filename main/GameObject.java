package main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected int x, 
				  y, 
				  speed = 1;
	protected ID id;

	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();

	public int getX() { return x; }
	public void incrementX() { x += speed; }
	public void decrementX() { x -= speed; }
	public void changeSpeed(int num) { speed = num; }

	public void setId(ID id) { this.id = id; }
    public ID getId() { return id; }
}