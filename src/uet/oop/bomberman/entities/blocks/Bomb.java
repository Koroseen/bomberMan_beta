package uet.oop.bomberman.entities.blocks;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.EntityList;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.entities.EntityList.bomberman;

public class Bomb extends Entity {
    private static int count;
    private static boolean exploded;
    private static boolean canPlace = true;
    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public static void setBomb() {
        if(canPlace) {
            Bomb bomb = new Bomb(EntityList.bomberman.getX() / Sprite.SCALED_SIZE, EntityList.bomberman.getY() / Sprite.SCALED_SIZE, Sprite.bomb_1.getFxImage());
            bomberman.bombs.add(bomb);
        }
        exploded = false;
        count = 0;
        canPlace = false;
    }
    public boolean isExploded() {
        return exploded;
    }
//    setImg(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate, 90).getFxImage());
    @Override
    public void update() {
        int timeOut = 200;
        if(count < timeOut) {
            count++;
            setImg(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, count, 90).getFxImage());
        }
        else {
            exploded = true;
            bomberman.bombs.removeIf(Bomb::isExploded);
            canPlace = true;
        }
    }
}
