package main;

import java.awt.Graphics;
import java.util.LinkedList;

// Used to maintain and update all objects
public class Handler {

	LinkedList<GameObject> objects = new LinkedList<GameObject>();

	public int speed = 5;

	public void tick() {
		// Looping through every game object
		for(int i = 0; i < objects.size(); i++) {
			GameObject temp = objects.get(i);
			temp.tick();
		}
	}

	public void render(Graphics g) {
		for(int i = 0; i < objects.size(); i++) {
			GameObject temp = objects.get(i);
			temp.render(g);
		}
	}

	public void addObject(GameObject objects) {
		this.objects.add(objects);
	}

	public void removeObject(GameObject objects) {
		this.objects.remove(objects);
	}
}