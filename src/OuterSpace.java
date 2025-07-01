import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.awt.image.BufferedImage;

public class OuterSpace extends Canvas implements KeyListener, Runnable {


    private AlienHorde horde;
    private Bullets shots;
    //private Ship ship;
    //private Alien alienOne;
    //private Alien alienTwo;
    private int ticks;
    private int waveHolder = 1;
    private boolean DEVMODE = false;

    private Ship ship1;
    private Ship ship2;

    private boolean[] keys;
    private BufferedImage back;

    public OuterSpace() {
        setBackground(Color.black);

        keys = new boolean[5];

        ship1 = new Ship( 360, 500, 50, 50, 3, 1);
        ship2 = new Ship(260, 500, 50, 50, 3, 1);

        //instantiate what you need as you need it (from global objects above)
        //alienOne = new Alien(50,25,50,50,0);
        //alienTwo = new Alien(125,25,50,50,0);
        horde = new AlienHorde(20);
        shots = new Bullets();

        this.addKeyListener(this);
        new Thread(this).start();
        ticks = 0;

        setVisible(true);
    }

    public void update(Graphics window) {
        paint(window);
        if (horde.checkGameOver()) {
            gameOver(window);
            try {
                Thread.currentThread().sleep(6000);
            } catch (Exception e) {
                System.out.println("bingus");
            }

        }
    }

    //the top part of the paint method is done for you
    public void paint(Graphics window) {
        //set up the double buffering to make the game animation nice and smooth
        Graphics2D twoDGraph = (Graphics2D) window;

        //take a snap shot of the current screen and save it as an image
        //that is the exact same width and height as the current screen
        if (back == null)
            back = (BufferedImage) (createImage(getWidth(), getHeight()));

        //create a graphics reference to the back ground image
        //we will draw all changes on the background image
        Graphics graphToBack = back.createGraphics();
        //this sets the background for the graphics window
        graphToBack.setColor(Color.BLACK);
        graphToBack.fillRect(0, 0, getWidth(), getHeight());


        //add code to move Ship, Alien, etc.-- Part 1
        if (keys[0] == true && ship1.getX()>0) {
            ship1.move("LEFT");
        } else if (keys[1] == true && ship1.getX()<740) {
            ship1.move("RIGHT");
        }
        if (keys[2] == true && ship2.getX()>0) {
            ship2.move("LEFT");
        } else if (keys[3] == true && ship2.getX()<740) {
            ship2.move("RIGHT");
        }


        //add code to fire a bullet - Part 3


        //add in collision detection to see if Bullets hit the Aliens and if Bullets hit the Ship -- Part 3
        horde.removeDeadOnes(shots.getList());

        //make sure you've drawn all your stuff
        ship1.draw(graphToBack);
        ship2.draw(graphToBack);
        //alienOne.draw(graphToBack);
        //alienTwo.draw(graphToBack);

        horde.drawEmAll(graphToBack);

        shots.drawEmAll(graphToBack);
        shots.moveEmAll();


        twoDGraph.drawImage(back, null, 0, 0);
        back = null;
    }


    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keys[0] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keys[1] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (DEVMODE)
                shots.add(new Ammo(ship1.getX() + 20, ship1.getY(), -5));
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            keys[2] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            keys[3] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            if (DEVMODE)
                shots.add(new Ammo(ship2.getX() + 20, ship1.getY(), -5));
        }
        if (e.getKeyCode() == KeyEvent.VK_X) {
            DEVMODE = !(DEVMODE);
        }
        repaint();
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keys[0] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keys[1] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (ticks > 224) {
                shots.add(new Ammo(ship1.getX() + 20, ship1.getY(), -10));
                ticks = 0;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            keys[2] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            keys[3] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            if (ticks > 224) {
                shots.add(new Ammo(ship2.getX() + 20, ship2.getY(), -10));
                ticks = 0;
            }
        }
        repaint();
    }

    public void keyTyped(KeyEvent e) {
        //no code needed here
        //method needs to be implemented
        //because class implements KeyListner
    }

    public void run() {
        try {
            while(true) {
                Thread.currentThread().sleep(5);
                ticks += 5;
                repaint();
            }
        } catch (Exception e) {
            //feel free to add something here, or not
            System.out.println("bingus");
        }
    }


    public void gameOver(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);

        g.setColor(Color.RED);

        int charSize = 10;
        int startX = 120;
        int startY = 200;

        String[] pattern = {

                " ####  ###  #   #  #####       ####  #   #  #####  ##### ",
                "#      # #  ## ##  #           #  #  #   #  #      #   # ",
                "#      # #  ## ##  #           #  #  #   #  #      #   # ",
                "#  ##  ###  # # #  ####        #  #  #   #  ####   ####  ",
                "#   #  # #  #   #  #           #  #   # #   #      #  # ",
                "#  ##  # #  #   #  #           #  #   # #   #      #   # ",
                " ####  # #  #   #  #####       ####    #    #####  #   # "
        };

        for (int row = 0; row < pattern.length; row++) {
            for (int col = 0; col < pattern[row].length(); col++) {
                if (pattern[row].charAt(col) == '#') {
                    g.fillRect(startX + col * charSize, startY + row * charSize, charSize, charSize);
                }
            }
        }
        try {
            Thread.currentThread().sleep(5000);
            System.exit(0);
        } catch (Exception e) {
            //feel free to add something here, or not
            System.out.println("bingus");
        }
    }
}

