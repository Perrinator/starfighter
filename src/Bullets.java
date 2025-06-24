import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class Bullets {
    private List<Ammo> ammo;

    public Bullets() {
        //initalize ammo
        ammo = new ArrayList<Ammo>();
    }

    public void add(Ammo al) {
        //add al to list
        ammo.add(al);
    }

    public void drawEmAll(Graphics window) {
        //draw each ammo in the list
        for (Ammo a : ammo)
            a.draw(window);
    }

    public void moveEmAll() {
        //move each ammo in the list
        for (Ammo a : ammo)
            a.move("UP");
    }

    public void cleanEmUp() {
        //for every ammo in the list
        //if the ammo is out of bounds
        //remove it
        for (Ammo a : ammo)
            if (a.getY() < 0)
                ammo.remove(a);
    }

    public List<Ammo> getList() {
        //add code
        return ammo;
    }

    public String toString() {
        return "" + ammo;
    }
}
