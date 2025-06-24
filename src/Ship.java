import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Ship extends MovingThing {
    private int speed;
    private Image image;

    public Ship() {
        this(0, 0, 50, 50, 0);
    }

    public Ship(int x, int y) {
        //add code here
        this(x, y, 50, 50, 0);
    }

    public Ship(int x, int y, int s) {
        //add code here
        this(x, y, 50, 50, s);
    }

    public Ship(int x, int y, int w, int h, int s) {
        //add code here
        super.setX(x);
        super.setY(y);
        super.setWidth(w);
        super.setHeight(h);
        speed = s;
        try {
            //this sets ship.jpg as the image for your ship
            //for this to work ship.jpg needs to be in the same folder as this .java file
            URL url = getClass().getResource("ship.jpg");
            image = ImageIO.read(url);
        } catch (Exception e) {
            //feel free to do something here or not
            System.out.println("bingus");
        }
    }


    public void setSpeed(int s) {
        //add more code
        speed = s;
    }

    public int getSpeed() {
        //continue coding
        return speed;
    }

    public void move(String direction) {
        //add code here
        //think about ALL your global variables and how you can use them to "move"
        //keep your parameter in mind as well
        if (direction.equals("LEFT"))
            super.setX(super.getX() - speed);
        else if (direction.equals("RIGHT"))
            super.setX(super.getX() + speed);
        else if (direction.equals("DOWN"))
            super.setY(super.getY() + speed);
        else if (direction.equals("UP"))
            super.setY(super.getY() - speed);
    }

    public void draw(Graphics window) {
        window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
    }

    public String toString() {
        return super.toString() + " " + getSpeed();
    }
}
