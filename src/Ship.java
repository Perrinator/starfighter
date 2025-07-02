import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Ship extends MovingThing {
    private int speed;
    private Image image;
    private int playerNUM;

    public Ship() {
        this(0, 0, 50, 50, 0, 1);
    }

    public Ship(int x, int y, int player) {
        //add code here
        this(x, y, 50, 50, 0, player);
    }

    public Ship(int x, int y, int s, int player) {
        //add code here
        this(x, y, 50, 50, s, player);
    }

    public Ship(int x, int y, int w, int h, int s, int player) {
        //add code here
        super.setX(x);
        super.setY(y);
        super.setWidth(w);
        super.setHeight(h);
        speed = s;
        if(player!=1)
            try {
                URL url = getClass().getResource("ship1.jpg");
                image = ImageIO.read(url);
            } catch (Exception e) {
                //feel free to do something here or not
                System.out.println("bingus");
            }
        else
            try {
                URL url = getClass().getResource("ship2.jpg");
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


    }

    public void draw(Graphics window) {
        window.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
    }

    public String toString() {
        return super.toString() + " " + getSpeed();
    }
}
