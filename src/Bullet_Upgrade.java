import javax.imageio.ImageIO;
import java.awt.*;
import java.net.URL;

public class Bullet_Upgrade extends MovingThing {
    private int speed;
    private Image image;
    private int X;



    @Override
    public void move(String direction) {
        setY(getY()-3);
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
            URL url = getClass().getResource("Bullet Upgrade.JPG");
            image = ImageIO.read(url);
        } catch (Exception e) {
            //feel free to do something here
            System.out.println("bingus");
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
