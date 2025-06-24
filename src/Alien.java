import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Alien extends MovingThing {
    private int speed;
    private Image image;
    private boolean gameIsOver = false;

    public Alien() {
        this(0, 0, 30, 30, 0);
    }

    public Alien(int x, int y) {
        //add code here
        this(x, y, 30, 30, 0);
    }

    public Alien(int x, int y, int s) {
        //add code here
        this(x, y, 30, 30, s);
    }

    public Alien(int x, int y, int w, int h, int s) {
        //add code here
        setX(x);
        setY(y);
        setWidth(w);
        setHeight(h);
        speed = s;
        try {
            URL url = getClass().getResource("alien.jpg");
            image = ImageIO.read(url);
        } catch (Exception e) {
            //feel free to do something here
        }
    }

    public void setSpeed(int s) {
        //add code
        speed = s;
    }

    public int getSpeed() {
        //add code
        return speed;
    }

    public boolean getGameIsOver() {
        return gameIsOver;
    }

    public void move(String direction) {
        //add code here
        //check that the alien is within the bounds of the screen (see Starfighter.java)
        //if alien is out of bounds change speed direction
        //and move the alien down a row (40 pixels)
        //constantly change the x position of the alien by the speed
        if (getX() > 770) {
            setY(getY() + 40);
            setSpeed(-1 * getSpeed());
        } else if (getX() < 25) {
            setY(getY() + 40);
            setSpeed(-1 * getSpeed());
        }
        if (getY() > 675)
            gameIsOver = true;
        setX(getX() + getSpeed());
    }

    /* The draw method is done for  you.
    This method will move the alien and update it's location on screen by
    constantly redrawing it.
    */
    public void draw(Graphics window) {
        move("DOWN");
        window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
    }

    public String toString() {
        return super.toString() + getSpeed();
    }


}
