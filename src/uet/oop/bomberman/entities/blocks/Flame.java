package uet.oop.bomberman.entities.blocks;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends Entity {
    private static int upLimit = 0;
    private static int downLimit = 0;
    private static int leftLimit = 0;
    private static int rightLimit = 0;

    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public static void calcLimit() {
        int x = EntityList.bomberman.getX() / Sprite.SCALED_SIZE;
        int y = EntityList.bomberman.getY() / Sprite.SCALED_SIZE;
        Flame flame = new Flame(x, y - upLimit - 1, Sprite.explosion_vertical2.getFxImage());
        boolean b = flame.checkCollision();
        while(b) {
            upLimit--;
            EntityList.flames.add(flame);
            flame = new Flame(x, y - upLimit - 1, Sprite.explosion_vertical2.getFxImage());
        }

    }

    public void bombExplosion() {

    }

    @Override
    public void update() {

    }
}
