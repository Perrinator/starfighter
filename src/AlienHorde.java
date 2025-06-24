import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class AlienHorde {
    private List<Alien> aliens;
    static int wave;
    private int aliensKilled;

    public AlienHorde(int size) {
        //initalize ArrayList
        //and fill with size amount of aliens (75 pixels apart)
        //if your row is full (out of bounds of screen)
        //move down a row (75 pixels)
        //starting point is 25, 50
        //first add aliens with speed of 0 to make sure you spacing is good
        int x = 25;
        int y = 50;
        int s = 1;
        int w = 30;
        int h = 30;
        aliens = new ArrayList<Alien>();
        for (int i = 0; i < size; i++) {
            if (x > 770) {
                x = 25;
                y += 75;
            }
            aliens.add(new Alien(x, y, w, h, s));
            x += 75;

        }
    }

    public void createaliens(int size) {
        int x = 25;
        int y = 50;
        int s = 1;
        int w = 30;
        int h = 30;
        if (wave == 1 || wave == 2)
            s = 1 * wave;
        else if (wave == 3) {
            s = (int) (Math.random() * 4) + 1;
            w = 20;
            h = 20;
        } else if (wave > 3) {
            s = (int) (wave * 0.5);
            w = 40;
            h = 40;
            if (wave * 3 != 20)
                size = 20;
            else
                size = wave * 3;
        }
        aliens = new ArrayList<Alien>();
        for (int i = 0; i < size; i++) {
            if (x > 770) {
                x = 25;
                y += 75;
            }
            aliens.add(new Alien(x, y, w, h, s));
            x += 75;
        }
    }

    public int getWave() {
        return wave;
    }

    public void add(Alien al) {
        //add an al to the list
        aliens.add(al);
    }

    public void drawEmAll(Graphics g) {
        //make sure you draw all aliens in the list
        for (Alien a : aliens) {
            a.draw(g);
        }
        if (aliensKilled == 20) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 800, 600);

            g.setColor(Color.GREEN);
            int charSize = 10;
            int startX = 120;
            int startY = 200;

            String[] pattern = {
                    "#    #  #####  #   #     #   #  #####  #   #  #####",
                    "##   #  #      #   #     #   #  #   #  #   #  #    ",
                    "# #  #  #      #   #     #   #  #   #  #   #  #    ",
                    "#  # #  #####  # # #     # # #  #####  #   #  #### ",
                    "#   ##  #      ## ##     ## ##  #   #   # #   #    ",
                    "#    #  #      ## ##     ## ##  #   #   # #   #    ",
                    "#    #  #####  #   #     #   #  #   #    #    #####"
            };

            for (int row = 0; row < pattern.length; row++) {
                for (int col = 0; col < pattern[row].length(); col++) {
                    if (pattern[row].charAt(col) == '#') {
                        g.fillRect(startX + col * charSize, startY + row * charSize, charSize, charSize);
                    }
                }
            }

            try {
                //Thread.currentThread().sleep(1000);
            } catch (Exception e) {
                //feel free to add something here, or not
                System.out.println("bingus");
            }
            wave++;
            aliensKilled = 0;
            createaliens(20);

        }


    }

    public void moveEmAll() {
        //make sure you move all aliens in the list
        for (Alien a : aliens)
            a.move("RIGHT");
    }

    public boolean checkGameOver() {
        for (Alien a : aliens)
            if (a.getGameIsOver())
                return true;
        return false;
    }

    public void removeDeadOnes(List<Ammo> shots) {
        //Part 3
        //for every shot in the list
        //check if you've hit any alien in the list
        //(do the coordinates of the shot fall between the boundarises of the alien)
        //if they do then remove the alien and the shot
        //make sure you break out of the loop if this happens
        for (int s = 0; s < shots.size(); s++) {
            Ammo shot = shots.get(s);
            for (int a = 0; a < aliens.size(); a++) {
                Alien alien = aliens.get(a);
                if (((alien.getX() < shot.getX()) && (shot.getX() < (alien.getX() + alien.getWidth())))
                        && ((alien.getY() < shot.getY()) && (shot.getY() < (alien.getY() + alien.getHeight())))) {
                    shots.remove(s);
                    aliens.remove(a);
                    s -= 1;
                    a -= 1;
                    aliensKilled++;
                    break;
                }
            }
        }

    }

    public String toString() {
        return "" + aliens;
    }
}
