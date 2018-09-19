package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Player extends GameObject {

    Handler handler;

    // These hold the images
    private BufferedImage player1_image,
                          player2_image,
                          player3_image;

    // * * * * * * * * * * * * * * * * * * * * *
    // Change these values for the sprite sheet.
    // -----------------------------------------
    // Image coordinates and dimensions
    // First number: X Coordinate
    // Second number: Y Coordinate
    // Third number: Width
    // Fourth number: Height
    private int image1[] = { 0, 0, 300, 300 },
                image2[] = { 600, 0, 300, 300 },
                image3[] = { 600, 300, 300, 300 };
    // * * * * * * * * * * * * * * * * * * * * *

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        SpriteSheet ss = new SpriteSheet(RacecarMain.sprite_sheet);
        //SpriteSheet ss2 = new SpriteSheet(RacecarMain.sprite_sheet2); // Use this if a second sprite sheet is needed
        player1_image = ss.grabImage(image1[0], image1[1], image1[2], image1[3]);
        player2_image = ss.grabImage(image2[0], image2[1], image2[2], image2[3]);
        player3_image = ss.grabImage(image3[0], image3[1], image3[2], image3[3]);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    public void tick() { }

    public void render(Graphics g) {
        
        if(id == ID.Team1) {
            g.drawImage(player1_image, x, y, 100, 100, null);
        }

        if(id == ID.Team2) {
            g.drawImage(player2_image, x, y, 100, 100, null);
        }

        if(id == ID.Team3) {
            g.drawImage(player3_image, x, y, 100, 100, null);
        }

    }
}
