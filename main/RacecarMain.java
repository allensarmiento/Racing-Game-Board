// Resources used
// -------------------------------------------
// https://www.youtube.com/watch?v=CrYbFs0-j5A
// https://www.youtube.com/watch?v=oXmUp4ZTW2Q
// https://stackoverflow.com/questions/17002906/trying-to-load-image-using-imageio-readclass-getresourceurl-but-getresource
// http://teaching.csse.uwa.edu.au/units/CITS1001/colorinfo.html
// -------------------------------------------

// Pictures used
// https://en.wikipedia.org/wiki/Clip_art
// https://www.pptgrounds.com/transportation/4285-race-track-backgrounds

package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RacecarMain extends Canvas implements Runnable {

	public static final int WIDTH = 1300,
							HEIGHT = 866;

	// Game is run through this thread
	private Thread thread;
	private boolean running = false;

	// Java classes
	private TitlePage titlePage;
	private Map map;
	private Handler handler;

	public enum STATE {
		Title,
		Map
	};
	public static STATE state = STATE.Title;

	public static BufferedImage sprite_sheet;
	public static BufferedImage sprite_sheet2;

	public RacecarMain() {
		BufferedImageLoader loader = new BufferedImageLoader();
		//BufferedImageLoader loader2 = new BufferedImageLoader(); // Use this if another sprite sheet is needed

		sprite_sheet = loader.loadImage("clipart.png");
		//sprite_sheet2 = loader2.loadImage("FILENAME"); // Use this if another sprite sheet is needed

		handler = new Handler();
		titlePage = new TitlePage(this, handler);
		map = new Map(this, titlePage, handler);
		this.addKeyListener(new KeyInput(handler, this, map));
		this.addMouseListener(titlePage);

		new Window(WIDTH, HEIGHT, "TITLE GOES HERE", this);
	}

	// Starting the thread
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	// Check for an error
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		// Don't need to click on the screen to have keyboard control input
        this.requestFocus();

		// Game Loop
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}

			if(running)
				render();
			frames++;

			if(System.currentTimeMillis()-timer > 1000) {
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}

	private void tick() {
		if(state == STATE.Title) {
			titlePage.tick();
			handler.tick();
		} else if(state == STATE.Map) {
			map.tick();
			handler.tick();
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3); // This creates 3 buffers
			return;
		}

		Graphics g = bs.getDrawGraphics();

		// Window Display
		g.setColor(Color.blue);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		// Displays what state we are in
		if(state == STATE.Title) {
			titlePage.render(g);
			handler.render(g); 
		} else if(state == STATE.Map) {
			map.render(g);
			handler.render(g);
		}

		g.dispose();
		bs.show();
	}

	public static float clamp(float var, float min, float max) {
		if(var >= max) return var = max;
		else if(var <= min) return var = min;
		else return var;
	}

	public static void main(String args[]) {
		new RacecarMain();
	}

}
