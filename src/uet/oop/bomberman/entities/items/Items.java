package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public abstract class Items extends Entity {

    public Items(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public static void randomItem(int row, int col) {
        Random rd = new Random();
        int n = rd.nextInt(5);
        System.out.println(n);
        switch (n) {
            case 0:
                EntityList.items.add(new SpeedItem(row, col, Sprite.powerup_speed.getFxImage()));
                break;
            case 4:

            default:
                break;
        }
    }

    @Override
    public abstract void update();
}
