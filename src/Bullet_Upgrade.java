import javax.imageio.ImageIO;
import java.awt.*;
import java.net.URL;

public class Bullet_Upgrade extends MovingThing {
    private int speed;
    private Image image;
    private int X;

    public Bullet_Upgrade() {
        this(0, 0, 30, 30, 0);
    }

    public Bullet_Upgrade(int x, int y) {
        //add code here
        this(x, y, 30, 30, 0);
    }

    @Override
    public void move(String direction) {

    }

    public Bullet_Upgrade(int x, int y, int s) {
        //add code here
        this(x, y, 30, 30, s);
    }

    public Bullet_Upgrade(int x, int y, int w, int h, int s) {
        //add code here
        setX(x);
        setY(y);
        setWidth(w);
        setHeight(h);
        X = x;
        speed = s;
        try {
            URL url = getClass().getResource("Bullet Upgrade.jpg");
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

    public boolean touching(int shipX, int shipW, int shipH)
    {
        if(X == shipX) {
            return true;
        }
        return false;
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
