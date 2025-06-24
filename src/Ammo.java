import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class Ammo extends MovingThing {
    private int speed;

    public Ammo() {
        this(10, 10, 10, 10, 0);
    }

    public Ammo(int x, int y) {
        //add code
        this(x, y, 10, 10, 0);
    }

    public Ammo(int x, int y, int s) {
        //add code
        this(x, y, 10, 10, 0);
        speed = s;
    }

    public Ammo(int x, int y, int w, int h, int s) {
        //add code here
        super(x, y, w, h);
        speed = s;

        try {
            File soundFile = new File("trimpew.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
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

    public void draw(Graphics window) {
        //add code to draw the ammo
        //ammo will be a yellow square
        window.setColor(Color.YELLOW);
        //if you don't set a color, your ammo will be black and you will not see it
        window.fillRect(getX(), getY(), getWidth(), getHeight());
    }


    public void move(String direction) {
        //add code to move the ammo
        //ship ammo should only move up
        setY(getY() + speed);
    }

    public String toString() {
        return super.toString() + getSpeed();
    }
}
