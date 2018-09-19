package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.RacecarMain.STATE;

public class Map extends MouseAdapter {

    private RacecarMain racecarMain;
    private TitlePage titlePage;
    private Handler handler;

    public static final Color DARK_GREEN = new Color(0, 153, 0);
    public static final Color VERY_DARK_GREEN = new Color(0, 102, 0);

    // Images imported
    private BufferedImage team1Icon,
                          team2Icon,
                          team3Icon;

     // * * * * * * * * * * * * * * * * * * * * *
    // Change these values for the sprite sheet.
    // - - - - - - - - - - - - - - - - - - - - - 
    // Image coordinates and dimensions
    // First number: X Coordinate
    // Second number: Y Coordinate
    // Third number: Width
    // Fourth number: Height
    private int icon1[] = { 900, 0, 300, 300 },
                icon2[] = { 300, 600, 300, 300 },
                icon3[] = { 300, 300, 300, 300 };
    // * * * * * * * * * * * * * * * * * * * * *

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    //        Edit these values to change positioning
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // Team names
    public String team1Name = "TEAM 1 NAME",
                  team2Name = "TEAM 2 NAME",
                  team3Name = "TEAM 3 NAME";

    // Team Player Positons - First number (in parentheses) is the points
    public int team1Position = (0) + 10,
               team2Position = (0) + 10,
               team3Position = (0) + 10;
    
    // Team Icon Positions
    private int iconBox1_xCoord = 250;

    // Icon Box Dimensions
    private int iconBoxDimensions = 200;

    // Road Positioning
    private int road_yCoord = 25,
                roadHeight = 325;
    
    // Grass Positioning
    private int grass1_yCoord = 0,
                grassHeight = 25;

    // Bumper Positioning
    private int bumperWidth = 50, 
                bumperHeight = 10; 

    // Track Lines Positioning
    private int trackLine_xCoord = 25, 
                trackLine_yCoord = 100, 
                trackWidth = 10, 
                trackHeight = 5; 

    // Starting Line Positioning
    private int startLineWidth = 10, 
                startLineHeight = 10, 
                startLine_yCoord = 20; 
    
    // Finish Line Positioning
    private int finishLine1_xCoord = 1255;
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    // Player objects created
    private boolean objectsCreated = false;

    // Team Icon Positions
    private int iconBox2_xCoord = iconBox1_xCoord + 250,
                iconBox3_xCoord = iconBox2_xCoord + 250,
                iconBox_yCoord = 450;

    // Team Name Positioning
    private int team1Name_xCoord = iconBox1_xCoord + 20,
                team2Name_xCoord = iconBox2_xCoord + 25,
                team3Name_xCoord = iconBox3_xCoord + 10,
                teamName_yCoord = iconBox_yCoord-25;

    // Team Points Positioning
    private int team1Pts_xCoord = iconBox1_xCoord + 25,
                team2Pts_xCoord = iconBox2_xCoord + 25,
                team3Pts_xCoord = iconBox3_xCoord + 25,
                teamPts_yCoord = iconBox_yCoord + 250;

    // Road Positioning
    private int road_xCoord = 0,
                roadWidth = racecarMain.WIDTH;
    
    // Grass Positioning
    private int grass_xCoord = road_xCoord,
                grass2_yCoord = roadHeight + 10,
                grassWidth = racecarMain.WIDTH;
    
    // Red and white blocks for the road
    private int bumper1_yCoord = road_yCoord,
                bumper2_yCoord = roadHeight;

    // Starting Line Positioning
    private int startLine1_xCoord = trackLine_xCoord,
                startLine2_xCoord = trackLine_xCoord + startLineWidth;

    // Finish Line Positioning
    private int finishLineWidth = startLineWidth,
                finishLineHeight = startLineHeight,
                finishLine2_xCoord = finishLine1_xCoord + 10,
                finishLine_yCoord = startLine_yCoord;

    public Map(RacecarMain racecarMain, TitlePage titlePage, Handler handler) {
        this.racecarMain = racecarMain;
        this.titlePage = titlePage;
        this.handler = handler;

        SpriteSheet ss = new SpriteSheet(RacecarMain.sprite_sheet);
        // SpriteSheet ss2 = new SpriteSheet(RacecarMain.sprite_sheet2); // Use this if a second sprite sheet is needed
        team1Icon = ss.grabImage(icon1[0], icon1[1], icon1[2], icon1[3]);
        team2Icon = ss.grabImage(icon2[0], icon2[1], icon2[2], icon2[3]);
        team3Icon = ss.grabImage(icon3[0], icon3[1], icon3[2], icon3[3]);
    }

    public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

        if(racecarMain.state == STATE.Map) {
            if(mouseOver(mx, my, 100, 100, 1200, 800)) {
                racecarMain.state = STATE.Title;
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
        
        if(racecarMain.state == STATE.Map) {
            Font font = new Font("arial", 1, 50);
		    Font font2 = new Font("arial", 1, 30);

            // Road
            g.setColor(Color.black);
            g.fillRect(road_xCoord, road_yCoord, roadWidth, roadHeight);

            // Grass
            g.setColor(DARK_GREEN);
            g.fillRect(grass_xCoord, grass1_yCoord, grassWidth, grassHeight);
            g.fillRect(grass_xCoord, grass2_yCoord, grassWidth, grassHeight);

            // Red and white blocks for the road
            boolean redColor = true;
            for(int i = 0; i < racecarMain.WIDTH; i += bumperWidth) {
                if(redColor) {
                    g.setColor(Color.red);
                    g.fillRect(i, bumper1_yCoord, bumperWidth, bumperHeight);
                    g.fillRect(i, bumper2_yCoord, bumperWidth, bumperHeight);
                    redColor = false;
                } else {
                    g.setColor(Color.white);
                    g.fillRect(i, bumper1_yCoord, bumperWidth, bumperHeight);
                    g.fillRect(i, bumper2_yCoord, bumperWidth, bumperHeight);
                    redColor = true;
                }
            }

            // Draw the lines for the track
            g.setColor(Color.white);
            for(int i = 1; i <= 50; i++) {
                for(int j = 1; j < 3; j++) {
                    g.fillRect(trackLine_xCoord*i, (trackLine_yCoord*j) + 25, trackWidth, trackHeight);
                }
            }

            // Starting and Finish Lines
            g.setColor(Color.white);
            for(int i = 2; i < 16; i++) {
                g.fillRect(startLine1_xCoord, startLine_yCoord*i, startLineWidth, startLineHeight);
                g.fillRect(startLine2_xCoord, (startLine_yCoord*i)+startLineHeight, startLineWidth, startLineHeight);

                g.fillRect(finishLine1_xCoord, finishLine_yCoord*i, finishLineWidth, finishLineHeight);
                g.fillRect(finishLine2_xCoord, (finishLine_yCoord*i)+finishLineHeight, finishLineWidth, finishLineHeight);
            }

            // Team 1 Icon Box
            g.setFont(font2);
            g.setColor(Color.green);
            g.drawString(team1Name, team1Name_xCoord, teamName_yCoord);
            g.setColor(Color.white);
            g.fillRect(iconBox1_xCoord, iconBox_yCoord, iconBoxDimensions, iconBoxDimensions);
            g.setColor(Color.green);
            g.drawRect(iconBox1_xCoord, iconBox_yCoord, iconBoxDimensions, iconBoxDimensions);
            g.drawString("Points: " + (team1Position-10), team1Pts_xCoord, teamPts_yCoord);

            // Team 2 Icon Box
            g.setFont(font2);
            g.setColor(Color.orange);
            g.drawString(team2Name, team2Name_xCoord, teamName_yCoord);
            g.setColor(Color.white);
            g.fillRect(iconBox2_xCoord, iconBox_yCoord, iconBoxDimensions, iconBoxDimensions);
            g.setColor(Color.orange);
            g.drawRect(iconBox2_xCoord, iconBox_yCoord, iconBoxDimensions, iconBoxDimensions);
            g.drawString("Points: " + (team2Position-10), team2Pts_xCoord, teamPts_yCoord);

            // Team 3 Icon Box
            g.setFont(font2);
            g.setColor(Color.red);
            g.drawString(team3Name, team3Name_xCoord, teamName_yCoord);
            g.setColor(Color.white);
            g.fillRect(iconBox3_xCoord, iconBox_yCoord, iconBoxDimensions, iconBoxDimensions);
            g.setColor(Color.red);
            g.drawRect(iconBox3_xCoord, iconBox_yCoord, iconBoxDimensions, iconBoxDimensions);
            g.drawString("Points: " + (team3Position-10), team3Pts_xCoord, teamPts_yCoord);

            // Team 1 Icon
            g.drawImage(team1Icon, iconBox1_xCoord, iconBox_yCoord, iconBoxDimensions, iconBoxDimensions, null);

            // Team 2 Icon
            g.drawImage(team2Icon, iconBox2_xCoord, iconBox_yCoord, iconBoxDimensions, iconBoxDimensions, null);

            // Team 3 Icon
            g.drawImage(team3Icon, iconBox3_xCoord, iconBox_yCoord, iconBoxDimensions, iconBoxDimensions, null);

            // Draw the players
            if(!objectsCreated) {
                handler.addObject(new Player(team1Position, 30, ID.Team1, handler));
                handler.addObject(new Player(team2Position, 130, ID.Team2, handler));
                handler.addObject(new Player(team3Position, 225, ID.Team3, handler));
                objectsCreated = true;
            }

        }

    }

}