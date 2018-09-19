package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.RacecarMain.STATE;

public class TitlePage extends MouseAdapter {

    private RacecarMain racecarMain;
    private Handler handler;

    // Images imported
    private BufferedImage background;

    // Background Positioning
    private int background_xCoord = 0,
                background_yCoord = 0,
                backgroundWidth = racecarMain.WIDTH,
                backgroundHeight = racecarMain.HEIGHT;

    // Logo Positioning
    private int logo_xCoord = 0,
                logo_yCoord = 0,
                logoDimensions = 150;

    // Title positioning
    private int title_xCoord = (racecarMain.WIDTH/4) + 200,
                title_yCoord = (racecarMain.HEIGHT/4);

    // Start Button Positioning
    private int startButton_xCoord = (racecarMain.WIDTH/4) + 200,
                startButton_yCoord = (racecarMain.HEIGHT/4) * 3 + 50,
                startButtonWidth = 200,
                startButtonHeight = 64;
    
    // Start Button Text Positioning
    private int startButtonText_xCoord = startButton_xCoord + 63,
                startButtonText_yCoord = startButton_yCoord + 40;

    public TitlePage(RacecarMain racecarMain, Handler handler) {
        this.racecarMain = racecarMain;
        this.handler = handler;

        //SpriteSheet ss = new SpriteSheet(RacecarMain.sprite_sheet); // Use this if you need a sprite sheet
    }

    public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

        if(racecarMain.state == STATE.Title) {
            if(mouseOver(mx, my, startButton_xCoord, startButton_yCoord, startButtonWidth, startButtonHeight)) {
                racecarMain.state = STATE.Map;
                return;
            }
        }
        
    }

    public void mouseReleased(MouseEvent e) {

	}

    private boolean mouseOver(int mx, int my, int x, int y,
							  int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			} else return false;
		} else return false;
	}

    public void tick() {

    }

    public void render(Graphics g) {
        
        if(racecarMain.state == STATE.Title) {
            Font font = new Font("arial", 1, 50);
		    Font font2 = new Font("arial", 1, 30);

            try {
                background = ImageIO.read(new File("raceBackground.jpg"));
                g.drawImage(background, background_xCoord, background_yCoord, backgroundWidth, backgroundHeight, null);
            } catch(IOException e) {
                g.setColor(Color.blue);
                g.fillRect(background_xCoord, background_yCoord, backgroundWidth, backgroundHeight);
            }

            // Title
            g.setFont(font);
            g.setColor(Color.black);
            g.drawString("TITLE GOES HERE", title_xCoord, title_yCoord);

            // Start button
            g.setFont(font2);
			g.setColor(Color.red);
			g.fillRect(startButton_xCoord, startButton_yCoord, startButtonWidth, startButtonHeight);
            g.setColor(Color.white);
			g.drawString("Start", startButtonText_xCoord, startButtonText_yCoord);
            
        } 

    }

}