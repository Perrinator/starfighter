import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.awt.image.BufferedImage;
import java.net.URL;

public class OuterSpace extends Canvas implements KeyListener, Runnable {


    private AlienHorde horde;
    private Bullets shotsONE;
    private Bullets shotsTWO;
    private int ticks1;
    private int ticks2;
    private int UPCollected;
    private boolean DEVMODE = false;

    private Ship ship1;
    private Ship ship2;

    private boolean[] keys;
    private BufferedImage back;

    public OuterSpace() {
        setBackground(Color.black);

        keys = new boolean[5];

        ship1 = new Ship( 360, 500, 50, 50, 3, 1);
        ship2 = new Ship(260, 500, 50, 50, 3, 2);

        //instantiate what you need as you need it (from global objects above)
        horde = new AlienHorde(20);
        shotsONE = new Bullets();
        shotsTWO = new Bullets();

        this.addKeyListener(this);
        new Thread(this).start();
        ticks1 = 0;
        ticks2 = 0;

        setVisible(true);


        UPCollected = 0;
    }

    public void update(Graphics window) {
        paint(window);
        if (horde.checkGameOver()) {
            gameOver(window);
            try {
                Thread.sleep(6000);
            } catch (Exception e) {
                System.out.println("bingus");
            }

        }
    }

    //the top part of the paint method is done for you
    public void paint(Graphics window) {
        //set up the double buffering to make the game animation nice and smooth
        Graphics2D twoDGraph = (Graphics2D) window;

        //take a snapshot of the current screen and save it as an image
        //that is the exact same width and height as the current screen
        if (back == null)
            back = (BufferedImage) (createImage(getWidth(), getHeight()));

        //create a graphics reference to the background image
        //we will draw all changes on the background image
        Graphics graphToBack = back.createGraphics();
        //this sets the background for the graphics window
        graphToBack.setColor(Color.BLACK);
        graphToBack.fillRect(0, 0, getWidth(), getHeight());


        //add code to move Ship, Alien, etc.-- Part 1
        if (keys[0] && ship1.getX()>390) {
            ship1.move("LEFT");
        } else if (keys[1] && ship1.getX()<740) {
            ship1.move("RIGHT");
        }
        if (keys[2] && ship2.getX()>0) {
            ship2.move("LEFT");
        } else if (keys[3] && ship2.getX()<330) {
            ship2.move("RIGHT");
        }


        //if((int)(Math.random()*100)==1) {
        if(DEVMODE) {
            Bullet_Upgrade UPSpawner = new Bullet_Upgrade(100,100,30,30,3);
            UPSpawner.draw(window);
        }
        //UPSpawner.draw(window);


        //add code to fire a bullet - Part 3


        //add in collision detection to see if Bullets hit the Aliens and if Bullets hit the Ship -- Part 3
        horde.removeDeadOnes(shotsONE.getList());
        horde.removeDeadOnes(shotsTWO.getList());

        //make sure you've drawn all your stuff
        ship1.draw(graphToBack);
        ship2.draw(graphToBack);

        horde.drawEmAll(graphToBack);

        shotsONE.drawEmAll(graphToBack);
        shotsONE.moveEmAll();
        shotsTWO.drawEmAll(graphToBack);
        shotsTWO.moveEmAll();


        twoDGraph.drawImage(back, null, 0, 0);
        back = null;
        try {
            Thread.sleep(1);
        } catch (Exception e) {
            //feel free to add something here, or not
            System.out.println("bingus");
        }
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
                shotsONE.add(new Ammo(ship1.getX() + 20, ship1.getY(), -5));
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            keys[2] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            keys[3] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            if (DEVMODE)
                shotsTWO.add(new Ammo(ship2.getX() + 20, ship2.getY(), -5));
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
            if (ticks1 > 224) {
                shotsONE.add(new Ammo(ship1.getX() + 20, ship1.getY(), -10));
                ticks1 = 0;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            keys[2] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            keys[3] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            if (ticks2 > 224) {
                shotsTWO.add(new Ammo(ship2.getX() + 20, ship2.getY(), -10));
                ticks2 = 0;
            }
        }
        repaint();
    }

    public void keyTyped(KeyEvent e) {
        //no code needed here
        //method needs to be implemented
        //because class implements KeyListener
    }

    public void run() {
        try {
            while(true) {
                Thread.sleep(5);
                ticks1 += 5;
                ticks2 += 5;
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
            Thread.sleep(5000);
            System.exit(0);
        } catch (Exception e) {
            //feel free to add something here, or not
            System.out.println("bingus");
        }
    }
}

