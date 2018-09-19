package main;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

// This class creates the window for the game
public class Window extends Canvas {

	public Window(int width, int height, String title, RacecarMain racecarMain) {
		JFrame frame = new JFrame(title);

		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(racecarMain);
		frame.setVisible(true);
		racecarMain.start();
	}
}