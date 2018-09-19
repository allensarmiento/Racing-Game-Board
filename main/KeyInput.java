package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import main.RacecarMain.STATE;

public class KeyInput extends KeyAdapter {
   
    private Handler handler;
    private RacecarMain racecarMain;
    private Map map;

    private boolean[] keyDown = new boolean[16];

    public KeyInput(Handler handler, RacecarMain racecarMain, Map map) {
        
        this.handler = handler;
        this.racecarMain = racecarMain;
        this.map = map;

        for(int i = 0; i < 12; i++) {
            keyDown[i] = false;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for(int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);

            if(key == KeyEvent.VK_1) { tempObject.changeSpeed(1); keyDown[0] = true; }
            if(key == KeyEvent.VK_2) { tempObject.changeSpeed(2); keyDown[1] = true; }
            if(key == KeyEvent.VK_3) { tempObject.changeSpeed(3); keyDown[2] = true; }
            if(key == KeyEvent.VK_4) { tempObject.changeSpeed(4); keyDown[3] = true; }
            if(key == KeyEvent.VK_5) { tempObject.changeSpeed(5); keyDown[4] = true; }
            if(key == KeyEvent.VK_6) { tempObject.changeSpeed(6); keyDown[5] = true; }
            if(key == KeyEvent.VK_7) { tempObject.changeSpeed(7); keyDown[6] = true; }
            if(key == KeyEvent.VK_8) { tempObject.changeSpeed(8); keyDown[7] = true; }
            if(key == KeyEvent.VK_9) { tempObject.changeSpeed(9); keyDown[8] = true; }
            if(key == KeyEvent.VK_0) { tempObject.changeSpeed(10); keyDown[9] = true; }


            // If object has ID of player then look for key listeners
            if(tempObject.getId() == ID.Team1) {
                // Key events for Team 1
                if(key == KeyEvent.VK_Q) { tempObject.decrementX(); map.team1Position = tempObject.getX(); keyDown[10] = true; }
                if(key == KeyEvent.VK_W) { tempObject.incrementX(); map.team1Position = tempObject.getX(); keyDown[11] = true; }
            }

            if(tempObject.getId() == ID.Team2) {
                // Key events for Team 2
                if(key == KeyEvent.VK_A) { tempObject.decrementX(); map.team2Position = tempObject.getX(); keyDown[12] = true; }
                if(key == KeyEvent.VK_S) { tempObject.incrementX(); map.team2Position = tempObject.getX(); keyDown[13] = true; }
            }

            if(tempObject.getId() == ID.Team3) {
                // Key events for Team 3
                if(key == KeyEvent.VK_Z) { tempObject.decrementX(); map.team3Position = tempObject.getX(); keyDown[14] = true; }
                if(key == KeyEvent.VK_X) { tempObject.incrementX(); map.team3Position = tempObject.getX(); keyDown[15] = true; }
            }
        }
        
        // Key to exit the game
        if(key == KeyEvent.VK_ESCAPE) System.exit(1);
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        for(int i = 0; i < handler.objects.size(); i++) {
            GameObject tempObject = handler.objects.get(i);

            if(key == KeyEvent.VK_1) { keyDown[0] = false; }
            if(key == KeyEvent.VK_2) { keyDown[1] = false; }
            if(key == KeyEvent.VK_3) { keyDown[2] = false; }
            if(key == KeyEvent.VK_4) { keyDown[3] = false; }
            if(key == KeyEvent.VK_5) { keyDown[4] = false; }
            if(key == KeyEvent.VK_6) { keyDown[5] = false; }
            if(key == KeyEvent.VK_7) { keyDown[6] = false; }
            if(key == KeyEvent.VK_8) { keyDown[7] = false; }
            if(key == KeyEvent.VK_9) { keyDown[8] = false; }
            if(key == KeyEvent.VK_0) { keyDown[9] = false; }

            // If object has ID of team then look for key listeners
            if(tempObject.getId() == ID.Team1) {
                // key events 
                if(key == KeyEvent.VK_Q) keyDown[10] = false;
                if(key == KeyEvent.VK_W) keyDown[11] = false;
            }

            if(tempObject.getId() == ID.Team2) {
                if(key == KeyEvent.VK_A) keyDown[12] = false;
                if(key == KeyEvent.VK_S) keyDown[13] = false;
            }

            if(tempObject.getId() == ID.Team3) {
                if(key == KeyEvent.VK_Z) keyDown[14] = false;
                if(key == KeyEvent.VK_X) keyDown[15] = false;
            }
        }

    }
}
